package com.codebytes.partnerportal.api.domain.rest.product;

import com.codebytes.partnerportal.api.domain.rest.RequestBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetAllProductRequest extends RequestBase
{
    private int pageNo;
    private int pageCount;
}
