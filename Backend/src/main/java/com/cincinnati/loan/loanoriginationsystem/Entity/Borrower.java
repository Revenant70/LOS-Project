package com.cincinnati.loan.loanoriginationsystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@Table(name = "borrowers")
@Entity
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrower_id;

    @Column(name = "borrower_name", nullable = false)
    private String borrowerName;

    @Column(name = "borrower_dob", nullable = false)
    private String borrowerDob;

    @Column(name = "borrower_ssn", nullable = false)
    private String borrowerSsn;

    @Column(name = "borrower_phone_number")
    private String borrowerPhoneNumber;

    @Column(name = "borrower_email", nullable = false, unique = true)
    private String borrowerEmail;

    @Column(name = "borrower_password", nullable = false)
    private String borrowerPassword;


    @OneToMany(mappedBy = "borrower")
    private Set<LoanApplication> loanApplications;

    @OneToMany(mappedBy = "borrower")
    private Set<Employment> employments;

    @OneToMany(mappedBy = "borrower")
    private Set<FinancialMetrics> financialMetrics;

}
