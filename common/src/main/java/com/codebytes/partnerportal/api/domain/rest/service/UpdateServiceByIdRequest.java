package com.codebytes.partnerportal.api.domain.rest.service;

import com.codebytes.partnerportal.api.domain.ContactDetails;
import com.codebytes.partnerportal.api.domain.Discount;
import com.codebytes.partnerportal.api.domain.DiscountType;
import com.codebytes.partnerportal.api.domain.rest.RequestBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UpdateServiceByIdRequest extends RequestBase
{
    private long serviceId;

    private String serviceName;
    private String description;
    private String location;

    private double price;

    private DiscountType discountType;

    private Discount discount;
    private ContactDetails contactDetails;

    private String mainImage;
    private List<String> subImages;
}
