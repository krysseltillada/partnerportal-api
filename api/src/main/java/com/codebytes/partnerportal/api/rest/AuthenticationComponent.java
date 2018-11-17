package com.codebytes.partnerportal.api.rest;

import com.codebytes.partnerportal.api.rest.repository.ApiConsumerRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationComponent
{
    @Autowired
    private ApiConsumerRepository apiConsumerRepository;

    public boolean validateApiKey(String apiKey, long userId) {
        try
        {
            String username = apiConsumerRepository.findById(userId).get().getUsername();
            return BCrypt.checkpw(username, apiKey);
        } catch(RuntimeException e) {
            return false;
        }
    }
}
