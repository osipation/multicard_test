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

    @Query(value = "SELECT T.ITEM " +
            "FROM (SELECT PURCHASE_ITEM AS ITEM, SUM(COUNT) " +
            "FROM PURCHASE_INFO PI " +
            "WHERE PI.PURCHASE_DATE > (CURRENT_DATE - 30) " +
            "GROUP BY PURCHASE_ITEM " +
                         "ORDER BY SUM DESC LIMIT 1) T", nativeQuery = true)
    Integer findMostPopularItemIdForMonth();

    @Query(value = "SELECT T.USER_ID " +
            "FROM (SELECT PI.USER_ID, COUNT(PI.USER_ID) " +
            "FROM PURCHASE_INFO PI " +
            "WHERE PI.PURCHASE_DATE > (CURRENT_DATE - 182)" +
            "GROUP BY PI.USER_ID " +
            "ORDER BY COUNT DESC " +
            "LIMIT 1)T", nativeQuery = true)
    Integer findMostPopularUserIdForHalfYear();

    @Query(value = "SELECT TT.PURCHASE_ITEM " +
            "FROM " +
            "(SELECT T.PURCHASE_ITEM, " +
            "SUM(T.COUNT) " +
            "FROM " +
            "(SELECT * " +
            "FROM PURCHASE_INFO PI " +
            "WHERE PI.USER_ID in " +
            "(SELECT U.ID " +
            "FROM USERS U " +
            "WHERE U.AGE = 18))T " +
            "GROUP BY T.PURCHASE_ITEM " +
            "ORDER BY SUM DESC " +
            "LIMIT 1)TT ", nativeQuery = true)
    Integer findMostPopularItemAmong18YearsOld();


}
