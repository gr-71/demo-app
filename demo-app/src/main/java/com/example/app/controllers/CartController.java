package com.example.app.controllers;


import com.example.app.beans.Cart;
import com.example.app.entities.Goods;
import com.example.app.entities.dtos.OrderLineDto;
import com.example.app.exceptions.ResourceNotFoundException;
import com.example.app.services.OrderLineService;
import com.example.app.services.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private OrderLineService orderLineService;
    private GoodsService goodsService;
    private Cart cart;

    @GetMapping("/add/{goodsId}")
    public void addGoodsToCartById(@PathVariable Long goodsId) {
        Goods goods = goodsService.findById(goodsId).orElseThrow(() -> new ResourceNotFoundException("Impossible to add goods (id = " + goodsId + " ) to cart. Goods is not found!"));
        cart.add(goods);
    }

    @GetMapping
    public List<OrderLineDto> getCartContent() {
        return orderLineService.mapEntityListToDtoList(cart.getLines());
    }

    @GetMapping("/remove/{productId}")
    public void removeGoodsFromCartById(@PathVariable Long goodstId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cart.removeByGoodsId(goodstId);
        response.sendRedirect(request.getHeader("referer"));
    }
}
