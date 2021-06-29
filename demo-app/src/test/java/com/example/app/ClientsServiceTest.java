package com.example.app;

import com.example.app.entities.Client;
import com.example.app.repositories.ClientsRepository;
import com.example.app.services.ClientsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientsServiceTest {
    @Autowired
    private ClientsService clientsService;

    @MockBean
    private ClientsRepository clientsRepository;

    @Test
    public void findOneClientTest() {
        Client clientFromDB = new Client();
        clientFromDB.setName("Feofanov");
        clientFromDB.setPhone("+71112223334");

        Mockito.doReturn(clientFromDB)
                .when(clientsRepository)
                .findOneByPhone("+71112223334");

        Optional<Client> clientF = clientsService.findByPhone("+71112223334");
        Assert.assertNotNull(clientF);
        Mockito.verify(clientsRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.eq("+71112223334"));
    }
}
