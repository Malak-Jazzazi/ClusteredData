package com.clustered.data.controller;


import com.clustered.data.exception.DealExistException;
import com.clustered.data.exception.DealValidationException;
import com.clustered.data.validation.DealValidator;
import com.clustered.data.model.DealRequest;
import com.clustered.data.service.DealService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
public class DealController {
    private static final Logger logger = LoggerFactory.getLogger(DealController.class);
    @Autowired
    private DealService dealService;

    @PostMapping("/create")
    public ResponseEntity<String> createDeal(@RequestBody DealRequest request) {
        logger.info("Received request to create deal , {}", request);
        try {
            DealValidator.validateDealRequest(request);
        } catch (DealValidationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        try {
            dealService.checkIfDealExist(request);
        } catch (DealExistException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        if (dealService.saveDeal(request)) {
            return ResponseEntity.ok("Deal saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save deal");
        }


    }
}
