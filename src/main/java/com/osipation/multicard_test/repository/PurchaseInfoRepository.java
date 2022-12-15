package com.osipation.multicard_test.repository;

import com.osipation.multicard_test.entity.PurchaseInfo;
import com.osipation.multicard_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfo, Integer> {

    List<PurchaseInfo> findAllByUserId(User userId);

    @Query(value = "SELECT T.ITEM" +
                     "FROM (SELECT PURCHASE_ITEM AS ITEM, SUM(COUNT) " +
                             "FROM PURCHASE_INFO PI " +
                            "WHERE PI.PURCHASE_DATE > (CURRENT_DATE - 7)" +
                         "GROUP BY PURCHASE_ITEM " +
                         "ORDER BY SUM DESC LIMIT 1) T", nativeQuery = true)
    Integer findMostPopularItemIdForMonth();


}
