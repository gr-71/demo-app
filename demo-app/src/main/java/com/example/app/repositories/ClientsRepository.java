package com.example.app.repositories;

import com.example.app.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
    Client findByName(String name);
    Optional<Client> findOneByPhone(String phone);
    boolean existsByPhone(String phone);
}
