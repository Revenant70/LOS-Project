package com.cincinnati.loan.loanoriginationsystem.Entity;

import com.cincinnati.loan.loanoriginationsystem.Enums.InterestRateType;
import com.cincinnati.loan.loanoriginationsystem.Enums.LoanTerm;
import com.cincinnati.loan.loanoriginationsystem.Enums.Loantype;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@ToString
@Table(name="loan_applications")
public class LoanApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID loan_application_id;


    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private BorrowerEntity borrowerEntity;

    @OneToOne(mappedBy = "loanApplicationEntity", cascade = CascadeType.ALL)
    private PropertyDetailsEntity propertyDetailEntity;

    @OneToOne(mappedBy = "loanApplicationEntity", cascade = CascadeType.ALL)
    private EmploymentEntity employmentEntity;

    @OneToOne(mappedBy = "loanApplicationEntity", cascade = CascadeType.ALL)
    private FinancialMetricsEntity financialMetricEntities;


}
