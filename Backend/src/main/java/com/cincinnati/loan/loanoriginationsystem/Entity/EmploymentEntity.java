package com.cincinnati.loan.loanoriginationsystem.Entity;

import com.cincinnati.loan.loanoriginationsystem.enums.EmploymentDuration;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
@Table(name="employment")
public class EmploymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employment_id", nullable = false)
    private Long employment_id;

    @Column(name = "employer_name", nullable = false)
    private String employerName;

    @Column(name = "employer_address" , nullable = false)
    private String employerAddress;

    @Column(name = "job_title", nullable = false)
    private String job_title;

    @Column(name = "employment_duration", nullable = false)
    private EmploymentDuration employmentDuration;


    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private BorrowerEntity borrowerEntity;
}
