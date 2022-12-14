package com.osipation.multicard_test.repository;

import com.osipation.multicard_test.entity.PurchaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfo, String> {
}
