package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Log
@Entity
public class ApiConsumer extends User
{

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private Store store;

    @OneToMany(cascade = CascadeType.ALL)
    List<ApplicationDetails> applicationDetailsList;

    @Embedded
    @Valid
    private CompanyDetails companyDetails;

}
