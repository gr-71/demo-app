package com.example.app.repositories;

import com.example.app.entities.Goods;
import com.example.app.entities.dtos.GoodsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    List<GoodsDto> findAllBy();
}
