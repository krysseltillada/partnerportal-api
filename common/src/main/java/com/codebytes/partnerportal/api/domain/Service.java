package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.javamoney.moneta.Money;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Log
@Entity
@TypeDef(name = "MoneyUserType", typeClass = MoneyUserType.class)
public class Service
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long serviceId;

    private String serviceName;
    private String description;
    private String location;

    @OneToOne
    private Category category;

    @Type(type="MoneyUserType")
    @Column(name = "price")
    private Money price;

    @Embedded
    private Discount discount;

    @Embedded
    private ContactDetails contactDetails;

    private String mainImage;

    @ElementCollection
    private List<String> subImages;

    private LocalDateTime dateCreated;
}
