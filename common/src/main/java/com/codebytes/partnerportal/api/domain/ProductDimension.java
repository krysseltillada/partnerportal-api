package com.codebytes.partnerportal.api.domain;

import javax.persistence.Embeddable;

@Embeddable
public class ProductDimension
{
    private double length;
    private double height;
    private double weight;
    private double width;
}
