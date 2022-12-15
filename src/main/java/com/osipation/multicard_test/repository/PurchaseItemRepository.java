package com.osipation.multicard_test.repository;

import com.osipation.multicard_test.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem,Integer> {
}
