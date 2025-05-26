package com.api.bookstore.controllers;

import com.api.bookstore.dtos.LoanDTO;
import com.api.bookstore.dtos.LoanRequestDTO;
import com.api.bookstore.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanRequestDTO loanRequestDTO){
        return loanService.createLoan(loanRequestDTO);
    }
}
