package com.codebytes.partnerportal.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ConsoleController
{
    @GetMapping("/console")
    public String console(Principal principal, Model model) {
        model.addAttribute("userSession", principal);
        return "console";
    }
}
