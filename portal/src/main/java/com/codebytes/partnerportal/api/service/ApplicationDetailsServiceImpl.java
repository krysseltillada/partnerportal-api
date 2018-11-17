package com.codebytes.partnerportal.api.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import com.codebytes.partnerportal.api.domain.ApplicationDetails;
import com.codebytes.partnerportal.api.repository.ApiConsumerRepository;
import com.codebytes.partnerportal.api.repository.ApplicationDetailsRepository;

@Service
public class ApplicationDetailsServiceImpl implements ApplicationDetailsService{
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private ApplicationDetailsRepository applicationDetailsRepository;
	@Autowired
	private ApiConsumerRepository apiConsumerRepository;

	@Override
	public void encryptKey(ApplicationDetails application, String username) {
		application.setApplicationKey(BCrypt.hashpw(username, BCrypt.gensalt(12)));
	}

	@Override
	public void saveApplication(ApplicationDetails application) {
		applicationDetailsRepository.save(application);
	}

	@Override
	public List<ApplicationDetails> findAllApplicationByUsername(String username){
		return apiConsumerRepository.getApiConsumerByUsername(username).getApplicationDetailsList();
	}

	@Override
	public void deleteApplication(long id) {
		applicationDetailsRepository.deleteById(id);
	}

}
