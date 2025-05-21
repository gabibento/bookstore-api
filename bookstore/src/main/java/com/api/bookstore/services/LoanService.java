package com.api.bookstore.services;

import com.api.bookstore.entities.Book;
import com.api.bookstore.entities.LoanSettings;
import com.api.bookstore.entities.User;
import com.api.bookstore.repositories.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanSettings loanSettings;

    public LoanService(LoanRepository loanRepository, LoanSettings loanSettings) {
        this.loanRepository = loanRepository;
        this.loanSettings = loanSettings;
    }
    public void createLoan(Book book, User user){
    }
//    public boolean verifyLoanedBooks(){
//
//    }

}
