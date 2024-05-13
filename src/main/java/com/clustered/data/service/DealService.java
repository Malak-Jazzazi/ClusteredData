package com.clustered.data.service;


import com.clustered.data.exception.DealExistException;
import com.clustered.data.exception.DealValidationException;
import com.clustered.data.model.Deal;
import com.clustered.data.model.DealRequest;
import com.clustered.data.repository.DealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DealService {

    private static final Logger logger = LoggerFactory.getLogger(DealService.class);
    @Autowired
    private DealRepository dealRepository;

    public boolean saveDeal(DealRequest request) {
         Deal deal = fromDTOToEntity(request);
            try {
                dealRepository.save(deal);
                logger.info("The Deal was saved successfully in the Database , {}" , deal);
                return true;
            } catch (Exception e) {
                logger.info("The Deal was not saved successfully in the Database, {}" , request);
                return false;
            }
    }

    private Deal fromDTOToEntity(DealRequest request) {
        Deal deal = new Deal();
        deal.setFromCurrencyISOCode(request.getFromCurrencyISOCode());
        deal.setToCurrencyISOCode(request.getToCurrencyISOCode());
        deal.setDealAmount(request.getDealAmount());
        deal.setDealTimestamp(LocalDateTime.now());
        return deal;
    }

    public void checkIfDealExist(DealRequest request) throws DealExistException {
        logger.info("Check if the deal exist in the Database ");
        Optional<Deal> deal = dealRepository.findByFromCurrencyISOCodeAndToCurrencyISOCodeAndDealAmount(
                request.getFromCurrencyISOCode(),
                request.getToCurrencyISOCode(),
                request.getDealAmount());
        if(deal.isPresent()){
            throw new DealExistException("The Deal already exist in the Database");
        };
    }
}
