package com.api.bookstore.repositories;

import com.api.bookstore.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByUserIdAndIsReturnedFalse(Long userId);
    List<Loan> findByUserIdAndIsReturnedFalseAndDueDateBefore(Long userId, LocalDate date);

}
