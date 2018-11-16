package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Log
@Entity
public class AdminApiProvider extends User
{
}
