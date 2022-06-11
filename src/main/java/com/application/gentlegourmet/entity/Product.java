package com.application.gentlegourmet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedEntityGraph(
    name = "product-graph.name-price-category",
    attributeNodes = {
        @NamedAttributeNode("name"),
        @NamedAttributeNode("price"),
        @NamedAttributeNode("category"),
    }
)
@NamedEntityGraph(
    name = "product-graph.all-fields",
    attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("name"),
        @NamedAttributeNode("description"),
        @NamedAttributeNode("price"),
        @NamedAttributeNode("category"),
        @NamedAttributeNode("brand"),
        @NamedAttributeNode("productReviews"),
        @NamedAttributeNode("productImages"),
    }
)
@NamedEntityGraph(
    name = "product-graph.id-name",
    attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("name"),
    }
)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    //no column needed here (filled as needed in BrandService and ProductService)
    @Transient
    private String productThumbnailPath;

    //no column needed here (filled as needed in BrandService and ProductService)
    @Transient
    private List<String> productDescriptions;

    //no column needed here (filled as needed in BrandService and ProductService)
    @Transient
    private Map<String, Integer> ratingMap;

    ///////////////////////////////////////////////////////////////////////////

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "brand_id", nullable = true)
    private Brand brand;

    @OneToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    private PurchaseDetail purchaseDetail;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductReview> productReviews;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductTag> productTags;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    ///////////////////////////////////////////////////////////////////////////

    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
