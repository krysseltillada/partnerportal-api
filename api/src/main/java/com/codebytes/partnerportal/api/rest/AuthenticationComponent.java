package com.codebytes.partnerportal.api.rest;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import com.codebytes.partnerportal.api.domain.ApplicationDetails;
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
            ApiConsumer apiConsumer = apiConsumerRepository.findById(userId).get();

            for(ApplicationDetails applicationDetails : apiConsumer.getApplicationDetailsList()) {
                if(applicationDetails.getApplicationKey().equals(apiKey)) {
                    String username = apiConsumerRepository.findById(userId).get().getUsername();
                    return BCrypt.checkpw(username, apiKey);
                }
            }
            
            return false;
        } catch(RuntimeException e) {
            return false;
        }
    }
}
