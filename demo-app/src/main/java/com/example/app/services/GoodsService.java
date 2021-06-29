package com.example.app.services;

import com.example.app.entities.Goods;
import com.example.app.entities.dtos.GoodsDto;
import com.example.app.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {
    private GoodsRepository goodsRepository;

    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public Goods saveOrUpdate(Goods goods) {
        return goodsRepository.save(goods);
    }

    public Optional<Goods> findById(Long id) {
        return goodsRepository.findById(id);
    }

    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public void deleteAll() {
        goodsRepository.deleteAll();
    }

    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return goodsRepository.existsById(id);
    }

    public List<GoodsDto> findAllDtos() {
        return goodsRepository.findAllBy();
    }
}