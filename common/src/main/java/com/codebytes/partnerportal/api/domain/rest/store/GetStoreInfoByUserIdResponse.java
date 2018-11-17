package com.codebytes.partnerportal.api.domain.rest.store;

import com.codebytes.partnerportal.api.domain.CompanyDetails;
import com.codebytes.partnerportal.api.domain.Store;
import com.codebytes.partnerportal.api.domain.rest.ResponseBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetStoreInfoByUserIdResponse extends ResponseBase
{
    private Store store;
    private CompanyDetails companyDetails;
}
