package com.codebytes.partnerportal.api.rest.repository;

import com.codebytes.partnerportal.api.domain.Service;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long>
{
}
