package com.codebytes.partnerportal.api.rest;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import com.codebytes.partnerportal.api.domain.rest.ResponseStatus;
import com.codebytes.partnerportal.api.domain.rest.service.UpdateServiceByIdResponse;
import com.codebytes.partnerportal.api.domain.rest.store.GetStoreInfoByUserIdRequest;
import com.codebytes.partnerportal.api.domain.rest.store.GetStoreInfoByUserIdResponse;
import com.codebytes.partnerportal.api.rest.repository.ApiConsumerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@Api(tags = {"Store API" }, description="Operations pertaining to store in PartnerPortal Open API", basePath = "/store")
public class ShopController
{

    @Autowired
    private ApiConsumerRepository apiConsumerRepository;

    @Autowired
    private AuthenticationComponent authenticationComponent;

    @ApiOperation(value = "Gets store info by user id",
                  response = GetStoreInfoByUserIdResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PostMapping("/store/getStoreInfo")
    @ResponseBody
    GetStoreInfoByUserIdResponse getStoreInfoByUserId (@RequestBody GetStoreInfoByUserIdRequest getStoreInfoByUserIdRequest) {
        GetStoreInfoByUserIdResponse getStoreInfoByUserIdResponse = new GetStoreInfoByUserIdResponse();

        if(!authenticationComponent.validateApiKey(getStoreInfoByUserIdRequest.getApiKey(), getStoreInfoByUserIdRequest.getUserId())) {
            getStoreInfoByUserIdResponse.setApiKey(getStoreInfoByUserIdRequest.getApiKey());
            getStoreInfoByUserIdResponse.setAppId(getStoreInfoByUserIdRequest.getAppId());
            getStoreInfoByUserIdResponse.setUserId(getStoreInfoByUserIdRequest.getUserId());
            getStoreInfoByUserIdResponse.setResponseDateTime(LocalDateTime.now());

            ResponseStatus responseStatus = new ResponseStatus();

            responseStatus.setStatus("ERROR");
            responseStatus.setMessage("Invalid API Key");

            getStoreInfoByUserIdResponse.setMResponseStatus(responseStatus);

            return getStoreInfoByUserIdResponse;
        }

        getStoreInfoByUserIdResponse.setAppId((getStoreInfoByUserIdRequest.getAppId()));
        getStoreInfoByUserIdResponse.setApiKey(getStoreInfoByUserIdRequest.getApiKey());
        getStoreInfoByUserIdResponse.setUserId(getStoreInfoByUserIdRequest.getUserId());

        ApiConsumer apiConsumer = apiConsumerRepository.findById(getStoreInfoByUserIdRequest.getUserId()).get();

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setStatus("SUCCESS");

        getStoreInfoByUserIdResponse.setResponseDateTime(LocalDateTime.now());
        getStoreInfoByUserIdResponse.setMResponseStatus(responseStatus);

        getStoreInfoByUserIdResponse.setStore(apiConsumer.getStore());
        getStoreInfoByUserIdResponse.setCompanyDetails(apiConsumer.getCompanyDetails());

        return getStoreInfoByUserIdResponse;
    }

}
