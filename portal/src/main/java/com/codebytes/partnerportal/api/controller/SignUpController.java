package com.codebytes.partnerportal.api.controller;

import com.codebytes.partnerportal.api.domain.ApiConsumer;
import com.codebytes.partnerportal.api.domain.ApplicationDetails;
import com.codebytes.partnerportal.api.domain.CompanyDetails;
import com.codebytes.partnerportal.api.domain.ContactDetails;
import com.codebytes.partnerportal.api.domain.Store;
import com.codebytes.partnerportal.api.domain.User;
import com.codebytes.partnerportal.api.service.ApiConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SignUpController
{

    @Autowired
    private ApiConsumerService apiConsumerService;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {

        ApiConsumer apiConsumer = new ApiConsumer();
        apiConsumer.setUsername("");
        model.addAttribute("apiConsumer", apiConsumer);
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("apiConsumer") @Valid ApiConsumer apiConsumer, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldErrors().get(0));
            System.out.println("error");
            return "signUp";
        }

        System.out.println(apiConsumer);

        try
        {
            apiConsumerService.registerApiConsumer(apiConsumer);
        } catch (Exception ex) {
            model.addAttribute("error", "existing user");
            return "signUp";
        }

        return "signUp";
    }
}
