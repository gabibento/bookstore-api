package com.api.bookstore.repositories;

import com.api.bookstore.entities.LoanSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanSettingsRepository extends JpaRepository<LoanSettings, Long> {
}
