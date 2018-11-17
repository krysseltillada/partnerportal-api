package com.codebytes.partnerportal.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import com.codebytes.partnerportal.api.domain.ApplicationDetails;
import com.codebytes.partnerportal.api.service.ApplicationDetailsService;

import java.security.Principal;

import javax.validation.Valid;

@Controller
public class ConsoleController
{
	
	@Autowired
	private ApplicationDetailsService applicationDetailsService;
	
    @GetMapping("/console")
    public String console(Principal principal, Model model) {
        model.addAttribute("application", new ApplicationDetails());
        return "console";
    }
    
    @PostMapping("/console")
    public String console(@ModelAttribute("application") @Valid ApplicationDetails application, BindingResult bindingResult, Model model, Principal principal) {
    	if(bindingResult.hasErrors()) {
    		return "console";
    	}
    	
    	applicationDetailsService.encryptKey(application, principal.getName());
    	System.out.println(principal.getName());
    	System.out.println(application.getApplicationKey());
    	applicationDetailsService.saveApplication(application);
    	
        return "console";
    }

}
