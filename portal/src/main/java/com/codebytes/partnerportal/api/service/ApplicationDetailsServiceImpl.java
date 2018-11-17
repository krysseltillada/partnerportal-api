package com.codebytes.partnerportal.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebytes.partnerportal.api.domain.ApplicationDetails;
import com.codebytes.partnerportal.api.repository.ApplicationDetailsRepository;

@Service
public class ApplicationDetailsServiceImpl implements ApplicationDetailsService{
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private ApplicationDetailsRepository applicationDetailsRepository;

	@Override
	public void encryptKey(ApplicationDetails application, String username) {
		application.setApplicationKey(encoder.encode(username));
	}

	@Override
	public void saveApplication(ApplicationDetails application) {
		applicationDetailsRepository.save(application);
	}

}
