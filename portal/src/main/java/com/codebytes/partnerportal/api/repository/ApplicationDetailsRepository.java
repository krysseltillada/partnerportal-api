package com.codebytes.partnerportal.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codebytes.partnerportal.api.domain.ApplicationDetails;

@Repository
public interface ApplicationDetailsRepository extends CrudRepository<ApplicationDetails, Long>{

}
