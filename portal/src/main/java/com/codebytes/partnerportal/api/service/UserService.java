package com.codebytes.partnerportal.api.service;

import com.codebytes.partnerportal.api.domain.AdminApiProvider;
import com.codebytes.partnerportal.api.domain.ApiConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class UserService implements UserDetailsService
{

    @Autowired
    private AdminApiProviderService adminApiProviderService;

    @Autowired
    private ApiConsumerService apiConsumerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        AdminApiProvider adminApiProvider = adminApiProviderService.getAdminApiProviderByUsername(username);
        ApiConsumer apiConsumer = apiConsumerService.getApiConsumerByUsername(username);

        if(adminApiProvider != null)
        {
            return new org.springframework.security.core.userdetails.User(
                    adminApiProvider.getUsername(),
                    adminApiProvider.getPassword(),
                    adminApiProvider.isEnabled(), true, true,
                    true, Arrays.asList(new SimpleGrantedAuthority(adminApiProvider.getRole())));
        }

        if(apiConsumer != null) {
            return new org.springframework.security.core.userdetails.User(
                    apiConsumer.getUsername(),
                    apiConsumer.getPassword(),
                    apiConsumer.isEnabled(), true, true,
                    true, Arrays.asList(new SimpleGrantedAuthority(apiConsumer.getRole())));
        }

        throw new UsernameNotFoundException("Invalid Email or password");

    }
}
