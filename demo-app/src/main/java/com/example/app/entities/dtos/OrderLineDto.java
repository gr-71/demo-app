package com.example.app.entities.dtos;

import com.example.app.entities.OrderLine;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderLineDto {
    private Long id;
    private String goodsName;
    private int count;
    private BigDecimal price;

    public OrderLineDto(OrderLine orderLine) {
        this.id = orderLine.getId();
        this.goodsName = orderLine.getGoods().getName();
        this.count = orderLine.getCount();
        this.price = orderLine.getPrice();
    }
}
