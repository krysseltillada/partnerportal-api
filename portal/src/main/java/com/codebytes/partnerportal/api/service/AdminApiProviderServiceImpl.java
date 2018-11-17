package com.codebytes.partnerportal.api.service;

import com.codebytes.partnerportal.api.domain.AdminApiProvider;
import com.codebytes.partnerportal.api.repository.AdminApiProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminApiProviderServiceImpl implements AdminApiProviderService
{

    @Autowired
    private AdminApiProviderRepository adminApiProviderRepository;

    @Override
    public AdminApiProvider getAdminApiProviderByUsername(String username)
    {
        return adminApiProviderRepository.getAdminApiProviderByUsername(username);
    }
}
