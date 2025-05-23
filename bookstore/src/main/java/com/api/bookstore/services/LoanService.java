package com.api.bookstore.services;

import com.api.bookstore.dtos.LoanDTO;
import com.api.bookstore.dtos.LoanRequestDTO;
import com.api.bookstore.entities.Book;
import com.api.bookstore.entities.Loan;
import com.api.bookstore.entities.LoanSettings;
import com.api.bookstore.entities.User;
import com.api.bookstore.repositories.BookRepository;
import com.api.bookstore.repositories.LoanRepository;
import com.api.bookstore.repositories.LoanSettingsRepository;
import com.api.bookstore.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanSettingsRepository loanSettingsRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, LoanSettingsRepository loanSettingsRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.loanSettingsRepository = loanSettingsRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<LoanDTO> createLoan(LoanRequestDTO loanRequestDTO){
        if(!canUserLoan(loanRequestDTO.getUserId())) {
            return ResponseEntity.badRequest().build();
        }

        Book book = bookRepository.findById(loanRequestDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(loanRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Loan loan = new Loan(book, user, LocalDate.now(), calculateDueDate(), false);
        loanRepository.save(loan);

        return ResponseEntity.created(URI.create("/loans/" + loan.getId()))
                .body(new LoanDTO(loan));
    }
    private LocalDate calculateDueDate(){
        return LocalDate.now().plusDays(getLoanSettings().getLoanDurationDays());
    }
    private LoanSettings getLoanSettings(){
        return loanSettingsRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("No loan settings setted"));
    }
    public boolean canUserLoan(Long userId){
        return loanRepository.countByUserIdAndIsReturnedFalse(userId) < getLoanSettings().getMaxBooksPerUser()
                && verifyLoanExpirated(userId);
    }
    public boolean verifyLoanExpirated(Long userId){
        return loanRepository.findByUserIdAndIsReturnedFalseAndDueDateBefore(userId, LocalDate.now()).isEmpty();
    }
}
