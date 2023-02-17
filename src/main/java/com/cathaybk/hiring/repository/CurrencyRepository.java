package com.cathaybk.hiring.repository;

import com.cathaybk.hiring.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
