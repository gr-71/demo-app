package com.example.app.controllers;

import com.example.app.beans.Cart;
import com.example.app.entities.Order;
import com.example.app.entities.Client;
import com.example.app.services.OrdersService;
import com.example.app.services.ClientsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
    private ClientsService clientsService;
    private OrdersService ordersService;
    private Cart cart;

    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmOrder(Principal principal, @RequestParam String address) {
        Client client = clientsService.findByPhone(principal.getName()).get();
        Order order = new Order(client, cart, address);
        order = ordersService.saveOrder(order);
    }
}
