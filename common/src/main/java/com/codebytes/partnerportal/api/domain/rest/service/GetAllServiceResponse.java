package com.codebytes.partnerportal.api.domain.rest.service;

import com.codebytes.partnerportal.api.domain.Service;
import com.codebytes.partnerportal.api.domain.rest.ResponseBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GetAllServiceResponse
        extends ResponseBase
{
    private long productCount;
    private List<Service> serviceList;
}
