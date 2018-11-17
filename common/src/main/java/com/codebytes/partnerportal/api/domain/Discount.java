package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class Discount
{
    private int discountValue;

    @Embedded
    private Credit requiredCredits;
}
