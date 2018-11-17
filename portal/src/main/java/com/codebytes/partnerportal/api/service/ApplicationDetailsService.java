package com.codebytes.partnerportal.api.service;

import java.util.List;

import com.codebytes.partnerportal.api.domain.ApplicationDetails;

public interface ApplicationDetailsService {
	void encryptKey(ApplicationDetails application, String username);
	void saveApplication(ApplicationDetails application);
	List<ApplicationDetails> findAllApplicationByUsername(String username);
	void deleteApplication(long id);
}
