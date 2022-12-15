package com.osipation.multicard_test.service;

import com.osipation.multicard_test.entity.PurchaseItem;
import com.osipation.multicard_test.entity.PurchaseInfo;
import com.osipation.multicard_test.entity.User;
import com.osipation.multicard_test.repository.PurchaseInfoRepository;
import com.osipation.multicard_test.repository.PurchaseItemRepository;
import com.osipation.multicard_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final PurchaseInfoRepository purchaseInfoRepository;
    private final UserRepository userRepository;
    private final PurchaseItemRepository purchaseItemRepository;


    @Autowired
    public ReportService(PurchaseInfoRepository purchaseInfoRepository, UserRepository userRepository,
                         PurchaseItemRepository purchaseItemRepository) {
        this.purchaseInfoRepository = purchaseInfoRepository;
        this.userRepository = userRepository;
        this.purchaseItemRepository = purchaseItemRepository;
    }

    public List<PurchaseInfo> getPurchasesForLastWeek() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User currentUser = userRepository.findByUsername(currentPrincipalName);
        LocalDate dateWeekAgo = LocalDate.now().minusDays(7);

        return purchaseInfoRepository.findAllByUserId(currentUser).stream()
                .filter(p -> p.getPurchaseDate().isAfter(dateWeekAgo))
                .collect(Collectors.toList());
    }

    public Optional<PurchaseItem> getMostPopularPurchaseItemForLastMonth() {
        Integer itemId = purchaseInfoRepository.findMostPopularItemIdForMonth();
        return purchaseItemRepository.findById(itemId);
    }

    public String getUserWithMostPurchasesForHalfYear() {
        Integer userId = purchaseInfoRepository.findMostPopularUserIdForHalfYear();
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> user.getName() + " " + user.getLastname())
                .orElse("Такого пользователя нет");
    }

    public Optional<PurchaseItem> getMostPopularItemAmong18YearsOld()  {
        return purchaseItemRepository.findById(purchaseInfoRepository.findMostPopularItemAmong18YearsOld());
    }
}
