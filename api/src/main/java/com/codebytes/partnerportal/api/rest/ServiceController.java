package com.codebytes.partnerportal.api.rest;

import com.codebytes.partnerportal.api.domain.Service;
import com.codebytes.partnerportal.api.domain.rest.ResponseStatus;
import com.codebytes.partnerportal.api.domain.rest.product.CreateProductResponse;
import com.codebytes.partnerportal.api.domain.rest.product.GetProductByIdResponse;
import com.codebytes.partnerportal.api.domain.rest.service.CreateServiceRequest;
import com.codebytes.partnerportal.api.domain.rest.service.CreateServiceResponse;
import com.codebytes.partnerportal.api.domain.rest.service.DeleteServiceByIdRequest;
import com.codebytes.partnerportal.api.domain.rest.service.DeleteServiceByIdResponse;
import com.codebytes.partnerportal.api.domain.rest.service.GetAllServiceRequest;
import com.codebytes.partnerportal.api.domain.rest.service.GetAllServiceResponse;
import com.codebytes.partnerportal.api.domain.rest.service.GetServiceByIdRequest;
import com.codebytes.partnerportal.api.domain.rest.service.GetServiceByIdResponse;
import com.codebytes.partnerportal.api.domain.rest.service.UpdateServiceByIdRequest;
import com.codebytes.partnerportal.api.domain.rest.service.UpdateServiceByIdResponse;
import com.codebytes.partnerportal.api.rest.repository.ApiConsumerRepository;
import com.codebytes.partnerportal.api.rest.repository.ServiceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Api(tags = {"Service API" }, description="Operations pertaining to services in PartnerPortal Open API", basePath = "/service")
public class ServiceController
{
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AuthenticationComponent authenticationComponent;

    @ApiOperation(value = "Creates a service",
                  response = CreateServiceResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PostMapping("/service/create")
    public @ResponseBody
    CreateServiceResponse createService(@RequestBody CreateServiceRequest createServiceRequest) {

        CreateServiceResponse createServiceResponse = new CreateServiceResponse();

        if(!authenticationComponent.validateApiKey(createServiceRequest.getApiKey(), createServiceRequest.getUserId())) {
            createServiceResponse.setApiKey(createServiceRequest.getApiKey());
            createServiceResponse.setAppId(createServiceRequest.getAppId());
            createServiceResponse.setUserId(createServiceRequest.getUserId());
            createServiceResponse.setResponseDateTime(LocalDateTime.now());

            ResponseStatus responseStatus = new ResponseStatus();

            responseStatus.setStatus("ERROR");
            responseStatus.setMessage("Invalid API Key");

            createServiceResponse.setMResponseStatus(responseStatus);

            return createServiceResponse;
        }

        createServiceResponse.setAppId(createServiceRequest.getAppId());
        createServiceResponse.setApiKey(createServiceRequest.getApiKey());
        createServiceResponse.setUserId(createServiceRequest.getUserId());

        Service service = new Service();

        service.setServiceName(createServiceRequest.getServiceName());
        service.setDescription(createServiceRequest.getDescription());
        service.setLocation(createServiceRequest.getLocation());

        service.setPrice(createServiceRequest.getPrice());

        service.setDiscountType(createServiceRequest.getDiscountType());
        service.setDiscount(createServiceRequest.getDiscount());
        service.setContactDetails(createServiceRequest.getContactDetails());

        service.setMainImage(createServiceRequest.getMainImage());
        service.setSubImages(createServiceRequest.getSubImages());

        service.setDateCreated(LocalDateTime.now());

        serviceRepository.save(service);

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setMessage("Service is posted.");
        responseStatus.setStatus("SUCCESS");

        createServiceResponse.setResponseDateTime(LocalDateTime.now());
        createServiceResponse.setMResponseStatus(responseStatus);

        return createServiceResponse;
    }

    @ApiOperation(value = "Deletes a service by id",
                  response = DeleteServiceByIdResponse.class,
                  produces = "application/json", protocols = "http/https")
    @DeleteMapping("/service/delete")
    public @ResponseBody
    DeleteServiceByIdResponse
    deleteServiceById(@RequestBody DeleteServiceByIdRequest deleteServiceByIdRequest) {

        DeleteServiceByIdResponse deleteServiceByIdResponse = new DeleteServiceByIdResponse();

        if(!authenticationComponent.validateApiKey(deleteServiceByIdRequest.getApiKey(), deleteServiceByIdRequest.getUserId())) {
            deleteServiceByIdResponse.setApiKey(deleteServiceByIdRequest.getApiKey());
            deleteServiceByIdResponse.setAppId(deleteServiceByIdRequest.getAppId());
            deleteServiceByIdResponse.setUserId(deleteServiceByIdRequest.getUserId());
            deleteServiceByIdResponse.setResponseDateTime(LocalDateTime.now());

            ResponseStatus responseStatus = new ResponseStatus();

            responseStatus.setStatus("ERROR");
            responseStatus.setMessage("Invalid API Key");

            deleteServiceByIdResponse.setMResponseStatus(responseStatus);

            return deleteServiceByIdResponse;
        }

        deleteServiceByIdResponse.setAppId(deleteServiceByIdRequest.getAppId());
        deleteServiceByIdResponse.setApiKey(deleteServiceByIdRequest.getApiKey());
        deleteServiceByIdResponse.setUserId(deleteServiceByIdRequest.getUserId());

        serviceRepository.deleteById(deleteServiceByIdRequest.getServiceId());

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setStatus("SUCCESS");
        responseStatus.setMessage("Service id " + deleteServiceByIdRequest.getServiceId() + " is deleted.");

        deleteServiceByIdResponse.setResponseDateTime(LocalDateTime.now());
        deleteServiceByIdResponse.setMResponseStatus(responseStatus);

        return deleteServiceByIdResponse;
    }

    @ApiOperation(value = "Gets all service by pageNo and pageCount",
                  response = GetAllServiceResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PostMapping("/service/getAll")
    @ResponseBody
    public GetAllServiceResponse getALLService(@RequestBody GetAllServiceRequest getAllServiceRequest) {

        GetAllServiceResponse getAllServiceResponse = new GetAllServiceResponse();

        if(!authenticationComponent.validateApiKey(getAllServiceRequest.getApiKey(), getAllServiceRequest.getUserId())) {
            getAllServiceResponse.setApiKey(getAllServiceRequest.getApiKey());
            getAllServiceResponse.setAppId(getAllServiceRequest.getAppId());
            getAllServiceResponse.setUserId(getAllServiceRequest.getUserId());
            getAllServiceResponse.setResponseDateTime(LocalDateTime.now());

            ResponseStatus responseStatus = new ResponseStatus();

            responseStatus.setStatus("ERROR");
            responseStatus.setMessage("Invalid API Key");

            getAllServiceResponse.setMResponseStatus(responseStatus);

            return getAllServiceResponse;
        }

        getAllServiceResponse.setAppId(getAllServiceRequest.getAppId());
        getAllServiceResponse.setApiKey(getAllServiceRequest.getApiKey());
        getAllServiceResponse.setUserId(getAllServiceRequest.getUserId());

        List<Service> serviceList = serviceRepository.findAll(new PageRequest(getAllServiceRequest.getPageNo(), getAllServiceRequest.getPageCount())).getContent();

        getAllServiceResponse.setServiceList(serviceList);
        getAllServiceResponse.setProductCount(serviceList.size());

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setStatus("SUCCESS");

        getAllServiceResponse.setResponseDateTime(LocalDateTime.now());
        getAllServiceResponse.setMResponseStatus(responseStatus);

        return  getAllServiceResponse;
    }

    @ApiOperation(value = "Gets service by id",
                  response = GetServiceByIdResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PostMapping("/service/getServiceById")
    @ResponseBody
    GetServiceByIdResponse getServiceById(@RequestBody GetServiceByIdRequest getServiceByIdRequest) {

        GetServiceByIdResponse getServiceByIdResponse = new GetServiceByIdResponse();

        if(!authenticationComponent.validateApiKey(getServiceByIdRequest.getApiKey(), getServiceByIdRequest.getUserId())) {
            getServiceByIdResponse.setApiKey(getServiceByIdRequest.getApiKey());
            getServiceByIdResponse.setAppId(getServiceByIdRequest.getAppId());
            getServiceByIdResponse.setUserId(getServiceByIdRequest.getUserId());
            getServiceByIdResponse.setResponseDateTime(LocalDateTime.now());

            ResponseStatus responseStatus = new ResponseStatus();

            responseStatus.setStatus("ERROR");
            responseStatus.setMessage("Invalid API Key");

            getServiceByIdResponse.setMResponseStatus(responseStatus);

            return getServiceByIdResponse;
        }

        getServiceByIdResponse.setAppId(getServiceByIdRequest.getAppId());
        getServiceByIdResponse.setApiKey(getServiceByIdRequest.getApiKey());
        getServiceByIdResponse.setUserId(getServiceByIdRequest.getUserId());

        Service service = serviceRepository.findById(getServiceByIdRequest.getServiceId()).get();

        getServiceByIdResponse.setService(service);

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setStatus("SUCCESS");

        getServiceByIdResponse.setResponseDateTime(LocalDateTime.now());
        getServiceByIdResponse.setMResponseStatus(responseStatus);

        return getServiceByIdResponse;
    }

    @ApiOperation(value = "Updates service by id",
                  response = UpdateServiceByIdResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PutMapping("/service/update")
    @ResponseBody
    UpdateServiceByIdResponse updateServiceById(@RequestBody UpdateServiceByIdRequest updateServiceByIdRequest) {

        UpdateServiceByIdResponse updateServiceByIdResponse = new UpdateServiceByIdResponse();

        if(!authenticationComponent.validateApiKey(updateServiceByIdRequest.getApiKey(), updateServiceByIdRequest.getUserId())) {
            updateServiceByIdResponse.setApiKey(updateServiceByIdRequest.getApiKey());
            updateServiceByIdResponse.setAppId(updateServiceByIdRequest.getAppId());
            updateServiceByIdResponse.setUserId(updateServiceByIdRequest.getUserId());
            updateServiceByIdResponse.setResponseDateTime(LocalDateTime.now());

            ResponseStatus responseStatus = new ResponseStatus();

            responseStatus.setStatus("ERROR");
            responseStatus.setMessage("Invalid API Key");

            updateServiceByIdResponse.setMResponseStatus(responseStatus);

            return updateServiceByIdResponse;
        }

        updateServiceByIdResponse.setAppId(updateServiceByIdRequest.getAppId());
        updateServiceByIdResponse.setApiKey(updateServiceByIdRequest.getApiKey());
        updateServiceByIdResponse.setUserId(updateServiceByIdRequest.getUserId());

        Service service = serviceRepository.findById(updateServiceByIdRequest.getServiceId()).get();

        service.setServiceName(updateServiceByIdRequest.getServiceName());
        service.setDescription(updateServiceByIdRequest.getDescription());
        service.setLocation(updateServiceByIdRequest.getLocation());
        service.setPrice(updateServiceByIdRequest.getPrice());
        service.setDiscountType(updateServiceByIdRequest.getDiscountType());
        service.setDiscount(updateServiceByIdRequest.getDiscount());
        service.setContactDetails(updateServiceByIdRequest.getContactDetails());
        service.setMainImage(updateServiceByIdRequest.getMainImage());
        service.setSubImages(updateServiceByIdRequest.getSubImages());

        serviceRepository.save(service);

        updateServiceByIdResponse.setServiceId(updateServiceByIdRequest.getServiceId());

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setStatus("SUCCESS");
        responseStatus.setMessage("Service Id " + updateServiceByIdRequest.getServiceId() + " Updated.");

        updateServiceByIdResponse.setResponseDateTime(LocalDateTime.now());
        updateServiceByIdResponse.setMResponseStatus(responseStatus);

        return updateServiceByIdResponse;
    }



}
