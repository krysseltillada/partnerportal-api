package com.codebytes.partnerportal.api.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Log
@MappedSuperclass
public abstract class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String username;
    private String password;

    @Embedded
    private ContactDetails contactDetails;

    private LocalDateTime dateTimeRegistered;
    private LocalDateTime lastDateTimeLogin;
    private LocalDateTime lastDateTimeLogout;

	@ElementCollection
    private Set<String> roles;

    public User()
    {
    }
}
