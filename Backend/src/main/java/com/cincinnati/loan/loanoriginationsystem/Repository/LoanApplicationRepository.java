package com.cincinnati.loan.loanoriginationsystem.Repository;

import com.cincinnati.loan.loanoriginationsystem.Entity.LoanApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplicationEntity, Long> {
}
