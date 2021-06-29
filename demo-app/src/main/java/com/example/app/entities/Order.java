package com.example.app.entities;

import com.example.app.beans.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderLine> lines;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date")
    @CreationTimestamp
    private LocalDateTime localDateTime;

    @Column(name = "address")
    private String address;

    public Order(Client client, Cart cart, String address) {
        this.client = client;
        this.address = address;
        this.lines = new ArrayList<>();
        for (OrderLine ol : cart.getLines()) {
            ol.setOrder(this);
            this.lines.add(ol);
        }
        this.price = new BigDecimal(cart.getPrice().doubleValue());
        cart.clear();
    }

}
