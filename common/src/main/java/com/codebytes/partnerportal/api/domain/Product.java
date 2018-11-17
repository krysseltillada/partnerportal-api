package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Log
@Entity
@TypeDef(name = "MoneyUserType", typeClass = MoneyUserType.class)
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    private long storeId;

    private String sku;
    private String name;
    private String brand;
    private String description;
    private String model;

    private int quantity;

    private double price;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Embedded
    private Discount discount;

    private String mainImage;

    @ElementCollection
    private List<String> subImages;

    private LocalDateTime dateCreated;

}
