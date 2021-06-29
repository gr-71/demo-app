package com.example.app.services;

import com.example.app.entities.OrderLine;
import com.example.app.entities.dtos.OrderLineDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {
    public List<OrderLineDto> mapEntityListToDtoList(List<OrderLine> orderItemList) {
        return orderItemList.stream().map(OrderLineDto::new).collect(Collectors.toList());
    }
}
