package com.clustered.data.validation;

import com.clustered.data.exception.DealValidationException;
import com.clustered.data.model.DealRequest;

import java.math.BigDecimal;

public class DealValidator {
    public static void validateDealRequest(DealRequest request) throws DealValidationException {
        if (request == null || request.getFromCurrencyISOCode() == null
                || request.getToCurrencyISOCode() == null
                || request.getDealAmount() == null) {
            throw new DealValidationException("Invalid deal request: Missing fields");
        }

        if (!isValidISOCode(request.getFromCurrencyISOCode())) {
            throw new DealValidationException("Invalid deal request: Invalid form for 'From Currency ISO Code'");
        }

        if (!isValidISOCode(request.getToCurrencyISOCode())) {
            throw new DealValidationException("Invalid deal request: Invalid form for 'To Currency ISO Code'");
        }

        if(request.getDealAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new DealValidationException("Invalid deal request: Deal amount must be greater than 0");
        }
    }
    private static boolean isValidISOCode(String isoCode) {
        return isoCode != null && isoCode.matches("[A-Z]{3}");
    }
}
