package com.clustered.data;

import com.clustered.data.controller.DealController;
import com.clustered.data.model.DealRequest;
import com.clustered.data.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DealControllerTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateDeal_Success() {
        DealRequest request = new DealRequest( "USD", "EUR",  BigDecimal.TEN);
        when(dealService.saveDeal(request)).thenReturn(true);

        ResponseEntity<String> response = dealController.createDeal(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deal saved successfully", response.getBody());
    }
}