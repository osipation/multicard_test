package com.osipation.multicard_test.service;

import com.osipation.multicard_test.entity.PurchaseInfo;
import com.osipation.multicard_test.entity.PurchaseItem;
import com.osipation.multicard_test.entity.User;
import com.osipation.multicard_test.repository.PurchaseInfoRepository;
import com.osipation.multicard_test.repository.PurchaseItemRepository;
import com.osipation.multicard_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseInfoRepository purchaseInfoRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public PurchaseService(PurchaseInfoRepository purchaseInfoRepository,
                           PurchaseItemRepository purchaseItemRepository,
                           UserRepository userRepository) {
        this.purchaseInfoRepository = purchaseInfoRepository;
        this.purchaseItemRepository = purchaseItemRepository;
        this.userRepository = userRepository;
    }

    public PurchaseItem buyPurchaseItem(String purchaseItemId, String count) {
        Optional<PurchaseItem> itemOptional = purchaseItemRepository.findById(Integer.parseInt(purchaseItemId));
        if(itemOptional.isPresent()) {
            PurchaseItem item = itemOptional.get();
            int countOfItem = Integer.parseInt(count);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();

            User user = userRepository.findByUsername(currentPrincipalName);

            PurchaseInfo purchase = PurchaseInfo.builder()
                    .purchaseItem(item)
                    .count(countOfItem)
                    .amount(item.getCost().multiply(BigDecimal.valueOf(countOfItem)))
                    .purchaseDate(LocalDate.now())
                    .userId(user)
                    .build();
            purchaseInfoRepository.save(purchase);
            return item;
        } else {
            throw new EntityNotFoundException("Такого товара нет в базе данных");
        }
    }
}
