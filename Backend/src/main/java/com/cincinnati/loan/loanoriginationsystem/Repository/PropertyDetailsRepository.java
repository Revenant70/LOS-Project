package com.cincinnati.loan.loanoriginationsystem.Repository;

import com.cincinnati.loan.loanoriginationsystem.Entity.PropertyDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDetailsRepository extends JpaRepository<PropertyDetailsEntity, Long> {
}
