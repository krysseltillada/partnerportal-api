package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Embeddable;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Log
@Embeddable
public class ContactDetails
{
    private String phoneNumber;
    private String telephoneNumber;
    private String emailAddress;
}
