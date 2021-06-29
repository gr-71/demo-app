package com.example.app;

import com.example.app.entities.Goods;
import com.example.app.repositories.GoodsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoriesTest {
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void goodsRepositoryTest() {
        Goods goods = new Goods(null, "Goods FGHFHGHJKJ", new BigDecimal(789.0f));
        Goods out = entityManager.persist(goods);
        entityManager.flush();

        List<Goods> goodsList = (List<Goods>)goodsRepository.findAll();
        System.out.println(goodsList);

        Assert.assertEquals(1, goodsList.size());
    }
}
