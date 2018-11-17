package com.codebytes.partnerportal.api.service;

import com.codebytes.partnerportal.api.domain.AdminApiProvider;
import org.springframework.stereotype.Service;

@Service
public interface AdminApiProviderService
{
     AdminApiProvider getAdminApiProviderByUsername(String username);
}
