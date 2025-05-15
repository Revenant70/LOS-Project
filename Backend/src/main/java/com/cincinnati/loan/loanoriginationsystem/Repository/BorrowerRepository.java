package com.cincinnati.loan.loanoriginationsystem.Repository;

import com.cincinnati.loan.loanoriginationsystem.Entity.BorrowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<BorrowerEntity, Long> {
    boolean existsByBorrowerEmail(String borrowerEmail);
}
