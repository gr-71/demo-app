package com.example.app;

import com.example.app.beans.Cart;
import com.example.app.entities.Goods;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {
    @Autowired
    private Cart cart;

    @Test
    public void cartFillingTest() {
        for (int i = 0; i < 5; i++) {
            Goods goods = new Goods();
            goods.setId(new Long(i + 1));
            goods.setPrice(new BigDecimal(10 + i * 10));
            goods.setName("Goods N " + i);
            cart.add(goods);
        }
        Assert.assertEquals(77, cart.getLines().toString());
    }
}