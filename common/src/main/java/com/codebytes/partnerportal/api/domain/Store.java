package com.codebytes.partnerportal.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Log
@Entity
public class Store
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long storeId;

    @NotEmpty
    private String storeName;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> productList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Service> serviceList;

    private LocalDateTime dateCreated;
}
