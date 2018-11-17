package com.codebytes.partnerportal.api.domain.rest.store;

import com.codebytes.partnerportal.api.domain.rest.RequestBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetStoreInfoByUserIdRequest extends RequestBase
{
    private long userId;
}
