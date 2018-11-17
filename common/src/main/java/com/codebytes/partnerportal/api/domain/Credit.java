package com.codebytes.partnerportal.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Embeddable;

@Data
@EqualsAndHashCode
@ToString
@Log
@Embeddable
public class Credit
{
    private int creditValue;
}
