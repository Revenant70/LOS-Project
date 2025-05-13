package com.cincinnati.loan.loanoriginationsystem.Entity;

import com.cincinnati.loan.loanoriginationsystem.enums.Loantype;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@ToString
@Table(name="loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    @Column(name = "loan_type")
    private Loantype loanType;

    @Column(name = "loan_duration", nullable = false, precision = 2, scale = 0)
    private Integer loanDuration;

    @Column(name = "loan_amount", nullable = false, precision = 19, scale = 2)
    private Double loanAmount;

    @Column(name = "loan_purpose", nullable = false)
    private String loanPurpose;

    @Column(name = "loan_status", nullable = false)
    private String loanStatus;

    @Column(name = "downpayment_amount", nullable = false)
    private Double downpaymentAmount;

    @Column(name = "loan_to_value_ratio", nullable = false)
    private Double loanToValueRatio;


    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private Borrower borrower;

    @OneToMany(mappedBy = "loanApplication")
    private Set<PropertyDetails> propertyDetails;

}
