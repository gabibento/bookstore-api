package com.api.bookstore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_loanSettings")
public class LoanSettings {
    @Id
    private Long id;
    private Integer loanDurationDays;
    private Integer maxBooksPerUser;

    public LoanSettings(Integer loanDurationDays, Integer maxBooksPerUser) {
        this.id = 1L;
        this.loanDurationDays = loanDurationDays;
        this.maxBooksPerUser = maxBooksPerUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoanDurationDays() {
        return loanDurationDays;
    }

    public void setLoanDurationDays(Integer loanDurationDays) {
        this.loanDurationDays = loanDurationDays;
    }

    public Integer getMaxBooksPerUser() {
        return maxBooksPerUser;
    }

    public void setMaxBooksPerUser(Integer maxBooksPerUser) {
        this.maxBooksPerUser = maxBooksPerUser;
    }
}
