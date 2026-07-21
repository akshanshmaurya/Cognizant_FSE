package com.cognizant.microservices.loan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @GetMapping
    public String getLoans() {
        return "List of Loans from Loan Service";
    }
}
