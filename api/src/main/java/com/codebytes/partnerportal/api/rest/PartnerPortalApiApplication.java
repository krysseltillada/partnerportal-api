package com.codebytes.partnerportal.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan("com.codebytes.partnerportal.api.domain")
@EnableSwagger2
public class PartnerPortalApiApplication
{
	public static void main(String[] args) {
		SpringApplication.run(PartnerPortalApiApplication.class, args);
	}
}
