package com.cincinnati.loan.loanoriginationsystem.Entity;

import com.cincinnati.loan.loanoriginationsystem.Enums.InterestRateType;
import com.cincinnati.loan.loanoriginationsystem.Enums.LoanTerm;
import com.cincinnati.loan.loanoriginationsystem.Enums.Loantype;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@ToString
@Table(name="loan_applications")
public class LoanApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    @Column(name = "loan_type")
    private Loantype loanType;

    @Column(name = "loan_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = "loan_term", nullable = false)
    private LoanTerm loanTerm;

    @Column(name = "interest_rate_type")
    private InterestRateType interestRateType;

    @Column(name = "downpayment_amount", nullable = false)
    private Double downpaymentAmount;

    @Column(name = "loan_to_value_ratio", nullable = false)
    private Double loanToValueRatio;

    @Column(name = "loan_status", nullable = false)
    private String loanStatus;


    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private BorrowerEntity borrowerEntity;

    @OneToMany(mappedBy = "loanApplicationEntity")
    private Set<PropertyDetailsEntity> propertyDetailEntities;

}
