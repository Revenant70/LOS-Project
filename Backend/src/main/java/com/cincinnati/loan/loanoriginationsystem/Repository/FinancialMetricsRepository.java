package com.cincinnati.loan.loanoriginationsystem.Repository;

import com.cincinnati.loan.loanoriginationsystem.Entity.FinancialMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialMetricsRepository extends JpaRepository<FinancialMetricsEntity, Long> {
}
