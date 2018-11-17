package com.codebytes.partnerportal.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import com.codebytes.partnerportal.api.domain.ApplicationDetails;
import com.codebytes.partnerportal.api.service.ApiConsumerService;
import com.codebytes.partnerportal.api.service.ApplicationDetailsService;

import java.security.Principal;
import java.util.Iterator;
import java.util.function.Consumer;

import javax.validation.Valid;

@Controller
public class ConsoleController
{
	
	@Autowired
	private ApplicationDetailsService applicationDetailsService;
	@Autowired
	private ApiConsumerService apiConsumerService;
	
    @GetMapping("/console")
    public String console(Principal principal, Model model) {
    	String username = principal.getName();
        model.addAttribute("application", new ApplicationDetails());
        model.addAttribute("userId", apiConsumerService.getApiConsumerByUsername(username).getUserId());
        model.addAttribute("applications", applicationDetailsService.findAllApplicationByUsername(username));
        return "console";
    }
    
    @PostMapping("/delete-application/{id}")
    public String deleteApp(@PathVariable long id, Principal principal) {
    	ApiConsumer consumer = apiConsumerService.getApiConsumerByUsername(principal.getName());
    	Iterator iterator = consumer.getApplicationDetailsList().iterator();
    	while(iterator.hasNext()) {
    		 ApplicationDetails element = (ApplicationDetails) iterator.next();
             if(element.getApplicationId() == id) {
            	 consumer.getApplicationDetailsList().remove(element);
            	 break;
             }
    	}
    	
    	applicationDetailsService.deleteApplication(id);
    	apiConsumerService.save(consumer);
    	return "redirect:/console";
    }
    
    @PostMapping("/console")
    public String console(@ModelAttribute("application") @Valid ApplicationDetails application, BindingResult bindingResult, Model model, Principal principal) {
    	if(bindingResult.hasErrors()) {
    		return "console";
    	}
    	
    	applicationDetailsService.encryptKey(application, principal.getName());
    	System.out.println(principal.getName());
    	System.out.println(application.getApplicationKey());
    	
    	ApiConsumer consumer = apiConsumerService.getApiConsumerByUsername(principal.getName());
    	consumer.getApplicationDetailsList().add(application);
    	
    	apiConsumerService.save(consumer);
    	applicationDetailsService.saveApplication(application);
    	
        return "redirect:/console";
    }

}
