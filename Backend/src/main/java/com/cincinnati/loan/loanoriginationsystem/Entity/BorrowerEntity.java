package com.cincinnati.loan.loanoriginationsystem.Entity;

import com.cincinnati.loan.loanoriginationsystem.Config.SSNEncryptorConfig;
import com.cincinnati.loan.loanoriginationsystem.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@ToString
@Table(name = "borrowers")
@Entity
public class BorrowerEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrower_id;

    @Column(name = "borrower_name", nullable = false)
    private String borrowerName;

    @Column(name = "borrower_dob", nullable = false)
    private LocalDate borrowerDOB;

    @Convert(converter = SSNEncryptorConfig.class)
    @Column(name = "borrower_ssn", nullable = false)
    private String borrowerSSN;

    @Pattern(regexp = "^(\\+1-)?\\d{3}-\\d{3}-\\d{4}$|^\\d{10}$|^\\d{8}$")
    @Column(name = "borrower_phone_number", length = 15)
    private String borrowerPhoneNumber;

    @Column(name = "borrower_email", nullable = false, unique = true)
    private String borrowerEmail;

    @Column(name = "borrower_password", nullable = false)
    private String borrowerPassword;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return borrowerPassword;
    }

    @Override
    public String getUsername() {
        return borrowerEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @OneToMany(mappedBy = "borrowerEntity")
    private Set<LoanApplicationEntity> loanApplicationEntities;

    @OneToMany(mappedBy = "borrowerEntity")
    private Set<EmploymentEntity> employmentEntities;

    @OneToMany(mappedBy = "borrowerEntity")
    private Set<FinancialMetricsEntity> financialMetricEntities;

}
