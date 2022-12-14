package com.osipation.multicard_test.repository;

import com.osipation.multicard_test.entity.PurchaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfo, Integer> {

    public List<PurchaseInfo> findAllByUserId(Integer userId);
}
