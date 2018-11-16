package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Log
@Entity
public class ApiConsumer extends User
{

    @OneToOne
    private Store store;

    @OneToMany
    List<ApplicationDetails> applicationDetailsList;

}
