package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode
@ToString
@Log
@Entity
public class ApplicationDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long applicationId;
    
    @NotBlank
    private String applicationName;

    @Enumerated(EnumType.STRING)
    private ApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    //private boolean isEnableToUseAPI;
    
    private String applicationKey;
}
