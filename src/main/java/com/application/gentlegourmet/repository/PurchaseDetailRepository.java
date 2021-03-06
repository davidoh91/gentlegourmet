package com.application.gentlegourmet.repository;

import com.application.gentlegourmet.entity.Product;
import com.application.gentlegourmet.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {

    @Query("SELECT SUM(pd.quantity) FROM PurchaseDetail pd WHERE pd.product = :product")
    int findSaleQuantitySumByProduct(@Param("product") Product product);


    @EntityGraph(
        value = "purchase-detail-graph.id-quantity-product",
        type = EntityGraph.EntityGraphType.LOAD
    )
    @Query("SELECT pd FROM PurchaseDetail pd")
    Set<PurchaseDetail> findAllPurchaseDetails();

}
