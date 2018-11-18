package com.codebytes.partnerportal.api.domain.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseStatus
{
    private String status;
    private String message;
}
