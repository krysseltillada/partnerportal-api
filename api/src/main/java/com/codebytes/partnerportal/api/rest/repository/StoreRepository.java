package com.codebytes.partnerportal.api.rest.repository;

import com.codebytes.partnerportal.api.domain.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long>
{
}
