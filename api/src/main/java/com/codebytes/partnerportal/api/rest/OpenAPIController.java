package com.codebytes.partnerportal.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = {"Utilities API" }, description="Utilities of PartnerPortal Open API", basePath = "/utilities")
public class OpenAPIController
{
    @ApiOperation(value = "test call for the api",
                  response = String.class,
                  produces = "application/json", protocols = "http/https")
    @GetMapping("/testCall")
    public String testCall() {
        return "test call for open api 0.1v";
    }
}
