package com.osipation.multicard_test.service;

import com.osipation.multicard_test.entity.PurchaseInfo;
import com.osipation.multicard_test.entity.User;
import com.osipation.multicard_test.repository.PurchaseInfoRepository;
import com.osipation.multicard_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final PurchaseInfoRepository purchaseInfoRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportService(PurchaseInfoRepository purchaseInfoRepository, UserRepository userRepository) {
        this.purchaseInfoRepository = purchaseInfoRepository;
        this.userRepository = userRepository;
    }

    public List<PurchaseInfo> getPurchasesForLastWeek() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User currentUser = userRepository.findByUsername(currentPrincipalName);


        return purchaseInfoRepository.findAllByUserId(currentUser.getId());
    }

}
