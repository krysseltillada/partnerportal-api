package com.codebytes.partnerportal.api.domain.rest.product;

import com.codebytes.partnerportal.api.domain.Product;
import com.codebytes.partnerportal.api.domain.rest.ResponseBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GetAllProductResponse extends ResponseBase
{
    private long productCount;
    private List<Product> productList;
}
