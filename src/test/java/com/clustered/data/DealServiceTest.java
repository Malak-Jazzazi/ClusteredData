package com.clustered.data;

import com.clustered.data.exception.DealExistException;
import com.clustered.data.model.Deal;
import com.clustered.data.model.DealRequest;
import com.clustered.data.repository.DealRepository;
import com.clustered.data.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class DealServiceTest {

    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealService dealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveDeal() {
        DealRequest request = new DealRequest( "USD", "EUR", BigDecimal.TEN);
        Deal deal = new Deal();
        when(dealRepository.save(any(Deal.class))).thenReturn(deal);

        boolean saved = dealService.saveDeal(request);

        assertTrue(saved);
        verify(dealRepository, times(1)).save(any(Deal.class));
    }

    @Test
    void testCheckIfDealExist_DealExists() {
        DealRequest request = new DealRequest("USD", "EUR", BigDecimal.TEN);
        when(dealRepository.findByFromCurrencyISOCodeAndToCurrencyISOCodeAndDealAmount(
                request.getFromCurrencyISOCode(),
                request.getToCurrencyISOCode(),
                request.getDealAmount())).thenReturn(Optional.of(new Deal()));

        assertThrows(DealExistException.class, () -> dealService.checkIfDealExist(request));
    }

    @Test
    void testCheckIfDealExist_DealDoesNotExist() {
        DealRequest request = new DealRequest( "USD", "EUR", BigDecimal.TEN);
        when(dealRepository.findByFromCurrencyISOCodeAndToCurrencyISOCodeAndDealAmount(
                request.getFromCurrencyISOCode(),
                request.getToCurrencyISOCode(),
                request.getDealAmount())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> dealService.checkIfDealExist(request));
    }
}