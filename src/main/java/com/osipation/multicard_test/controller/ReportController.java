package com.osipation.multicard_test.controller;

import com.osipation.multicard_test.entity.PurchaseInfo;
import com.osipation.multicard_test.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/purchasesForWeek")
    public List<PurchaseInfo> getPurchasesForLastWeek() {
        return reportService.getPurchasesForLastWeek();
    }


}
