package com.clustered.data.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DealRequest {
    private String fromCurrencyISOCode;
    private String toCurrencyISOCode;
    private BigDecimal dealAmount;
}
