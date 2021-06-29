package com.example.app;

import com.example.app.controllers.GoodsController;
import com.example.app.entities.Goods;
import com.example.app.repositories.GoodsRepository;
import com.example.app.services.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {GoodsController.class})
@AutoConfigureMockMvc
public class DisabledGoodsTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GoodsService goodsService;

    @MockBean
    private GoodsRepository goodsRepository;

    // https://support.smartbear.com/alertsite/docs/monitors/api/endpoint/jsonpath.html

    @Test
    public void getAllProductsTest() throws Exception {
        List<Goods> allGoods = Arrays.asList(
                new Goods(1L, "Goods ABC", new BigDecimal(700.0)),
                new Goods(2L, "Goods DEF", new BigDecimal(184.0)),
                new Goods(3L, "Goods GHI", new BigDecimal(37.0)),
                new Goods(4L, "Goods JKL", new BigDecimal(500.0)),
                new Goods(5L, "Goods MNO", new BigDecimal(48.0))
        );

        given(goodsService.findAll()).willReturn(allGoods);

        mvc.perform(get("/api/v1/goods")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.goods").exists())
                .andExpect(jsonPath("$.goods").isArray())
                .andExpect(jsonPath("$[0].name", is(allGoods.get(0).getName())));
    }
}
