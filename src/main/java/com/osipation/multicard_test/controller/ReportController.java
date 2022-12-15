package com.osipation.multicard_test.controller;

import com.osipation.multicard_test.entity.PurchaseItem;
import com.osipation.multicard_test.entity.PurchaseInfo;
import com.osipation.multicard_test.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/list/week")
    public ResponseEntity<List<PurchaseInfo>> getPurchasesForLastWeek() {
        List<PurchaseInfo> purchaseInfos = reportService.getPurchasesForLastWeek();
        return new ResponseEntity<>(purchaseInfos, HttpStatus.OK);
    }

    @GetMapping("/popular/item/month")
    public ResponseEntity<PurchaseItem> getMostPopularPurchaseItemForLastMonth() {

        Optional<PurchaseItem> itemOptional = reportService.getMostPopularPurchaseItemForLastMonth();
        return itemOptional.map(purchaseItem -> new ResponseEntity<>(purchaseItem, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/popular/user/half-year")
    public ResponseEntity<String> getUserWithMostPurchasesForHalfYear() {
        String response = reportService.getUserWithMostPurchasesForHalfYear();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/popular/item/user/age/18")
    public ResponseEntity<PurchaseItem> getMostPopularItemAmong18YearsOld() {
        Optional<PurchaseItem> itemOptional = reportService.getMostPopularItemAmong18YearsOld();
        return itemOptional.map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}