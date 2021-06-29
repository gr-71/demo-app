package com.example.app.services;

import com.example.app.entities.Role;
import com.example.app.entities.Client;
import com.example.app.entities.dtos.ServiceClient;
import com.example.app.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientsService implements UserDetailsService {
    private ClientsRepository clientsRepository;
//    private PasswordEncoder passwordEncoder;
    private RolesService rolesService;

//    @Autowired
//    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Autowired
    public void setClientsRepository(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public Optional<Client> findByPhone(String phone) {
        return clientsRepository.findOneByPhone(phone);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientsRepository.findByName(username);
        return new org.springframework.security.core.userdetails.User(client.getPhone(), client.getPassword(),
                mapRolesToAuthorities(client.getRoles()));
    }

    @Transactional
    public Client save(ServiceClient serviceClient) {
        Client client = new Client();
        findByPhone(serviceClient.getPhone()).ifPresent((u) -> {
            throw new RuntimeException("Client with phone " + serviceClient.getPhone() + " already exists!");
        });
        client.setPhone(serviceClient.getPhone());
//        client.setPassword(passwordEncoder.encode(serviceClient.getPassword()));
        client.setPassword(serviceClient.getPassword());
        client.setName(serviceClient.getName());
        client.setRoles(Arrays.asList(rolesService.findByName("ROLE_BUYER")));
        return clientsRepository.save(client);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}