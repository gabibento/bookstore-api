package com.api.bookstore.dtos;

import com.api.bookstore.entities.Loan;

import java.time.LocalDate;

public class LoanDTO {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public LoanDTO(Loan loan){
     id = loan.getId();
     bookId = loan.getBook().getId();
     userId = loan.getUser().getId();
     loanDate = loan.getLoanDate();
     dueDate = loan.getDueDate();
     returnDate = loan.getReturnDate();
     isReturned = loan.isReturned();
    }

    public LoanDTO(Long id, Long bookId, Long userId, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, boolean isReturned) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public LoanDTO(Long bookId, Long userId, LocalDate loanDate, LocalDate dueDate, boolean isReturned) {
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.isReturned = isReturned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
