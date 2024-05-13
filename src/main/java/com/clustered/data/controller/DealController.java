package com.clustered.data.controller;


import com.clustered.data.model.DealRequest;
import com.clustered.data.service.DealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
public class DealController {

    @Autowired
    private DealService dealService;

    @PostMapping("/create")
    public ResponseEntity<String> createDeal(@RequestBody @Valid DealRequest request ) {

        boolean saved = dealService.saveDeal(request);
        if (true) {
            return ResponseEntity.ok("Deal saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save deal");
        }
    }
}
