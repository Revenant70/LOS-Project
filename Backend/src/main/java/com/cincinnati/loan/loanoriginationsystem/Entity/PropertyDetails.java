package com.cincinnati.loan.loanoriginationsystem.Entity;


import com.cincinnati.loan.loanoriginationsystem.enums.PropertyType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name="property_details")
@ToString
public class PropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long property_id;

    @Column(name = "property_address", nullable = false)
    private String propertyAddress;

    @Column(name = "property_type", nullable = false)
    private PropertyType propertyType;

    @Column(name = "home_inspection_uploads", nullable = false)
    private byte[] homeInspectionUploads;

    @Column(name = "property_appraised_value", nullable = false)
    private Double propertyAppraisedValue;


    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private LoanApplication loanApplication;

}
