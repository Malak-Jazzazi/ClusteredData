package com.clustered.data.repository;

import com.clustered.data.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal , Long> {
    Optional<Deal> findByFromCurrencyISOCodeAndToCurrencyISOCodeAndDealAmount(String fromISO , String toISO , BigDecimal amount);
}
