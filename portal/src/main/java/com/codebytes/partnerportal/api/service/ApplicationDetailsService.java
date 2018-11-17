package com.codebytes.partnerportal.api.service;

import com.codebytes.partnerportal.api.domain.ApplicationDetails;

public interface ApplicationDetailsService {
	void encryptKey(ApplicationDetails application, String username);
	void saveApplication(ApplicationDetails application);
}
