package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode
@ToString
@Log
@Embeddable
public class CompanyDetails
{
    @NotEmpty
    private String companyName;

    private String companyNo;
    private String companyEmail;
    private String companyLink;
}
