package com.example.app.controllers;

import com.example.app.entities.Goods;
import com.example.app.entities.dtos.GoodsDto;
import com.example.app.services.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/goods")
@AllArgsConstructor
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public String showAll(Model model){
        List<GoodsDto> goods = goodsService.findAllDtos();
        model.addAttribute("goods", goods);
        return "all_goods";
    }

    @GetMapping("/add")
    public String showAddForm(){
        return "add_goods_form";
    }

    @PostMapping("/add")
    public String saveNewGoods(@ModelAttribute Goods goods){
        goodsService.saveOrUpdate(goods);
        return "redirect:/all_goods/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("goods", goodsService.findById(id));
        return "edit_goods_form";
    }

    @PostMapping("/edit")
    public String editGoogs(@ModelAttribute Goods goods){
        goodsService.saveOrUpdate(goods);
        return "redirect:/all_goods/";
    }
}