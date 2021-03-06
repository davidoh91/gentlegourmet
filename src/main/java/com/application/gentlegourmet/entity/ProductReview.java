package com.application.gentlegourmet.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_review")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review")
    private String review;

    @Column(name = "created")
    @CreationTimestamp
    private Date created;

    @Transient
    private String createdBy;

    @Transient
    private long purchaseId;


    ///////////////////////////////////////////////////////////////////////////


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    ///////////////////////////////////////////////////////////////////////////


    public ProductReview(int rating, String review, String createdBy, long purchaseId) {
        this.rating = rating;
        this.review = review;
        this.createdBy = createdBy;
        this.purchaseId = purchaseId;
    }

}
