package com.codebytes.partnerportal.api.domain.rest.service;

import com.codebytes.partnerportal.api.domain.rest.RequestBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetAllServiceRequest
        extends RequestBase
{
    private int pageNo;
    private int pageCount;
}
