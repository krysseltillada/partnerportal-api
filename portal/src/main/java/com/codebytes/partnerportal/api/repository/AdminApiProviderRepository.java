package com.codebytes.partnerportal.api.repository;

import com.codebytes.partnerportal.api.domain.AdminApiProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminApiProviderRepository
        extends CrudRepository<AdminApiProvider, Long>
{
    @Query("SELECT a FROM AdminApiProvider a WHERE  a.username = :username")
    AdminApiProvider getAdminApiProviderByUsername(@Param("username") String username);
}
