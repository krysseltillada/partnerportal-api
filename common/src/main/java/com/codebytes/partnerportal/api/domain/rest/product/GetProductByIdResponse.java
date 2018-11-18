package com.codebytes.partnerportal.api.domain.rest.product;

import com.codebytes.partnerportal.api.domain.Product;
import com.codebytes.partnerportal.api.domain.rest.ResponseBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetProductByIdResponse extends ResponseBase
{
    private Product product;
}
