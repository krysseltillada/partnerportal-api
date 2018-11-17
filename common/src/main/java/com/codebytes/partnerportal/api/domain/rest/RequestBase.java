package com.codebytes.partnerportal.api.domain.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RequestBase
{
    private long appId;
    private long userId;
    private String apiKey;
    private LocalDateTime requestDateTime;
}
