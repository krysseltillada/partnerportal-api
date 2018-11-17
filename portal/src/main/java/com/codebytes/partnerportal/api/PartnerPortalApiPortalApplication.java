package com.codebytes.partnerportal.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EntityScan(basePackages="com.codebytes.partnerportal.api.domain")
public class PartnerPortalApiPortalApplication
{
	public static void main(String[] args) {
		SpringApplication.run(PartnerPortalApiPortalApplication.class, args);
	}
}
