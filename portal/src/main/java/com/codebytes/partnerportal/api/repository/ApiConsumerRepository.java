package com.codebytes.partnerportal.api.repository;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiConsumerRepository
        extends CrudRepository<ApiConsumer, Long>
{
    @Query("SELECT a FROM ApiConsumer a WHERE a.username = :username")
    ApiConsumer getApiConsumerByUsername(@Param("username") String username);
}
