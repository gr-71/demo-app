package com.example.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_lines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private BigDecimal price;

    public OrderLine(Goods goods) {
        this.goods = goods;
        this.count = 1;
        this.price = new BigDecimal(0).add(goods.getPrice());
    }

    public void increment() {
        this.count++;
        this.price = new BigDecimal(count * goods.getPrice().doubleValue());
    }

    public void decrement() {
        this.count--;
        this.price = new BigDecimal(count * goods.getPrice().doubleValue());
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
