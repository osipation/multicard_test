package com.osipation.multicard_test.controller;

import com.osipation.multicard_test.entity.PurchaseItem;
import com.osipation.multicard_test.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/buy")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/item/{itemId}/{count}")
    public ResponseEntity<String> buyPurchaseItem(@PathVariable String itemId, @PathVariable String count) {
        try {
            PurchaseItem item = purchaseService.buyPurchaseItem(itemId,count);
            return new ResponseEntity<>("Поздравляем! вы приобрели "+item.getName() + "в кол-ве " + count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Покупка не удалась по причине" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
