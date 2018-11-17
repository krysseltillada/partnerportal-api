package com.codebytes.partnerportal.api.domain.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ResponseBase
{
    private long appId;
    private long userId;
    private String apiKey;

    private LocalDateTime responseDateTime;

    private ResponseStatus mResponseStatus;
}
