package com.clustered.data.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DealRequest {
    @NotNull(message = "ISO Code cannot be null")
    @Size(min = 3, max = 3, message = "ISO Code must be 3 characters")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must be a valid ISO country code")
    private String fromCurrencyISOCode;

    @NotNull(message = "ISO Code cannot be null")
    @Size(min = 3, max = 3, message = "ISO Code must be 3 characters")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must be a valid ISO country code")
    private String toCurrencyISOCode;

    @NotNull(message = "Amount Code cannot be null")
    @Digits(integer = 10, fraction = 2, message = "Amount must have up to 10 digits and 2 decimals")
    @DecimalMin(value = "0.00", inclusive = false, message = "Amount must be greater than 0")
    @DecimalMax(value = "10000000000.00", inclusive = true, message = "Amount must be less than or equal to 10,000,000,000.00")
    private BigDecimal dealAmount;
}
