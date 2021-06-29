package com.example.app.controllers;

import com.example.app.entities.Client;
import com.example.app.entities.dtos.ServiceClient;
import com.example.app.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegistrationController {
    private ClientsService clientsService;

    @Autowired
    public void setClientsService(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String showMyLoginPage(Model model) {
        model.addAttribute("serviceClient", new ServiceClient());
        return "registration-form";
    }

    @PostMapping("/register/process")
    public String processRegistrationForm(@ModelAttribute("systemUser") @Validated ServiceClient serviceClient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        Optional<Client> existing = clientsService.findByPhone(serviceClient.getPhone());
        if (existing.isPresent()) {
            model.addAttribute("registrationError", "Client with phone number: " + serviceClient.getPhone() + " already exists");
            serviceClient.setPhone(null);
            model.addAttribute("serviceClient", serviceClient);
            return "registration-form";
        }
        clientsService.save(serviceClient);
        return "registration-confirmation";
    }
}
