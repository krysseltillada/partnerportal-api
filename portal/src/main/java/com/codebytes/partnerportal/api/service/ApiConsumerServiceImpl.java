package com.codebytes.partnerportal.api.service;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import com.codebytes.partnerportal.api.repository.ApiConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApiConsumerServiceImpl implements ApiConsumerService
{
    @Autowired
    private ApiConsumerRepository apiConsumerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ApiConsumer getApiConsumerByUsername(String username)
    {
        return apiConsumerRepository.getApiConsumerByUsername(username);
    }

    @Override
    public void registerApiConsumer(ApiConsumer apiConsumer)
    {
        ApiConsumer existingApiConsumer =
                apiConsumerRepository.getApiConsumerByUsername(apiConsumer.getContactDetails().getEmailAddress());

        if(existingApiConsumer != null) {
            throw new RuntimeException("existing user");
        }

        apiConsumer.getContactDetails().setEmailAddress(apiConsumer.getUsername());
        apiConsumer.setPassword(passwordEncoder.encode(apiConsumer.getPassword()));
        apiConsumer.setEnabled(true);
        apiConsumer.setRole("CONSUMER");

        apiConsumerRepository.save(apiConsumer);

    }
}
