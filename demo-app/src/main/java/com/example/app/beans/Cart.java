package com.example.app.beans;

import com.example.app.entities.Goods;
import com.example.app.entities.OrderLine;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {
    private List<OrderLine> lines;
    private BigDecimal price;

    @PostConstruct
    public void init() {
        lines = new ArrayList<>();
    }

    public void clear() {
        lines.clear();
        recalculate();
    }

    public void add(Goods goods) {
        for (OrderLine ol : lines) {
            if (ol.getGoods().getId().equals(goods.getId())) {
                ol.increment();
                recalculate();
                return;
            }
        }
        lines.add(new OrderLine(goods));
        recalculate();
    }

    public void decrement(Goods goods) {
        for (OrderLine ol : lines) {
            if (ol.getGoods().getId().equals(goods.getId())) {
                ol.decrement();
                if (ol.isEmpty()) {
                    lines.remove(ol);
                }
                recalculate();
                return;
            }
        }
    }

    public void removeByGoodsId(Long goodsId) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).getGoods().getId().equals(goodsId)) {
                lines.remove(i);
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = new BigDecimal(0.0);
        for (OrderLine ol : lines) {
            price = price.add(ol.getPrice().multiply(BigDecimal.valueOf(ol.getCount())));
        }
    }
}
