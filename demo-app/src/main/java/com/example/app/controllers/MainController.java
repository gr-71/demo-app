package com.example.app.controllers;

import com.example.app.entities.Client;
import com.example.app.services.ClientsService;
import com.example.app.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    ClientsService clientsService;

    @Autowired
    GoodsService goodsService;

    @GetMapping("/")
    public String home(Principal principal) {
        if (principal != null) {
            Optional<Client> client = clientsService.findByPhone(principal.getName());
            System.out.println(client.get());
        }
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "Hello, ADMIN!";
    }

}
