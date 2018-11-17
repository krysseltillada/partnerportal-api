package com.codebytes.partnerportal.api.service;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import org.springframework.stereotype.Service;

@Service
public interface ApiConsumerService
{
    ApiConsumer getApiConsumerByUsername(String username);
    void registerApiConsumer(ApiConsumer apiConsumer);
}
