package com.codebytes.partnerportal.api.rest.repository;

import com.codebytes.partnerportal.api.domain.ApplicationDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDetailsRepository extends CrudRepository<ApplicationDetails, Long>
{
}
