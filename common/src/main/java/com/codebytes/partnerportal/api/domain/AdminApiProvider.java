package com.codebytes.partnerportal.api.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode
@ToString
@Log
@Entity
public class AdminApiProvider extends User
{
}
