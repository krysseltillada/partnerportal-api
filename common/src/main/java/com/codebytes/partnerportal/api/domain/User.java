package com.codebytes.partnerportal.api.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.ElementCollection;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Log
@MappedSuperclass
public abstract class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @NotEmpty
    @Email
    private String username;

    @NotEmpty
    private String password;

    @Embedded
    @Valid
    private ContactDetails contactDetails;

    private LocalDateTime dateTimeRegistered;
    private LocalDateTime lastDateTimeLogin;
    private LocalDateTime lastDateTimeLogout;

    private String role;

    private boolean enabled;

}
