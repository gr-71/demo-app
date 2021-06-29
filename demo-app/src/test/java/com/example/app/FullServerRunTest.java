package com.example.app;

import com.example.app.entities.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
//        List<Goods> goods = this.restTemplate.getForObject("/api/v1/goods", List.class);
        Goods goods = restTemplate.getForObject("/api/v1/goods", Goods.class);
        System.out.println(goods);
        assertThat(goods).isNotNull();
    }
}
