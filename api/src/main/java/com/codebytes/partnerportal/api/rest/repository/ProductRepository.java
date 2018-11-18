package com.codebytes.partnerportal.api.rest.repository;

import com.codebytes.partnerportal.api.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>
{
}
