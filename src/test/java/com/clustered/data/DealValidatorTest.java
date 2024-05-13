package com.clustered.data;

import com.clustered.data.exception.DealValidationException;
import com.clustered.data.model.DealRequest;
import com.clustered.data.validation.DealValidator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DealValidatorTest {

    @Test
    void testValidDealRequest() {
        DealRequest validRequest = new DealRequest( "USD", "EUR", BigDecimal.TEN);
        assertDoesNotThrow(() -> DealValidator.validateDealRequest(validRequest));
    }

    @Test
    void testInvalidDealRequestMissingFields() {
        DealRequest invalidRequest = new DealRequest(null, "EUR", BigDecimal.TEN);

        DealValidationException exception = assertThrows(DealValidationException.class,
                () -> DealValidator.validateDealRequest(invalidRequest));
        assertEquals("Invalid deal request: Missing required fields", exception.getMessage());
    }

    @Test
    void testInvalidDealRequestInvalidISOCode() {
        DealRequest invalidRequest = new DealRequest("usd", "EUR",BigDecimal.TEN);

        DealValidationException exception = assertThrows(DealValidationException.class,
                () -> DealValidator.validateDealRequest(invalidRequest));
        assertEquals("Invalid deal request: Invalid form for 'From Currency ISO Code'", exception.getMessage());
    }

    @Test
    void testInvalidDealRequestInvalidAmount(){
        DealRequest invalidRequest = new DealRequest("USD" , "JOR" , BigDecimal.ZERO);

        DealValidationException exception = assertThrows(DealValidationException.class ,
                () -> DealValidator.validateDealRequest(invalidRequest));
        assertEquals("Invalid deal request: Deal amount must be greater than 0" , exception.getMessage());
    }
}