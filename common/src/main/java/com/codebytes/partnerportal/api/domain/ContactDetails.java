package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode
@ToString
@Log
@Embeddable
public class ContactDetails
{
    @NotEmpty
    private String contactNo;
    private String emailAddress;
}
