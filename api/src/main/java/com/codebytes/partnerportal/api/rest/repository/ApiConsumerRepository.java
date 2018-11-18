package com.codebytes.partnerportal.api.rest.repository;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiConsumerRepository extends CrudRepository<ApiConsumer, Long>
{
}
