package com.cincinnati.loan.loanoriginationsystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Entity
@Table(name="financial_metrics")
public class FinancialMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "financial_metrics_id", nullable = false)
    private Long financial_metrics_id;

    @Column(name = "debt_to_income_ratio", nullable = false)
    private Double debtToIncomeRatio;

    @Column(name = "annual_income", nullable = false)
    private Double annualIncome;

    @Column(name = "other_income", nullable = false)
    private Double otherIncome;


    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private BorrowerEntity borrowerEntity;
}
