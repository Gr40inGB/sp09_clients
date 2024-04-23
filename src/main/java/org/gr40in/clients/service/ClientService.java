package org.gr40in.clients.service;


import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.gr40in.clients.dao.Client;
import org.gr40in.clients.dto.ClientDto;
import org.gr40in.clients.dto.ClientMapper;
import org.gr40in.clients.repository.ClientRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor

public class ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    public List<ClientDto> findAllClients() {
        return clientMapper.listToDto(clientRepository.findAll());
    }

    public ClientDto findClientById(long id) {
        var client = clientRepository.findById(id);
        if (client.isPresent()) return clientMapper.toDto(client.get());
        else throw new NoSuchElementException("cant find client with id " + id);
    }

    public ClientDto createClient(ClientDto clientDto) {
        clientDto.setId(null);
        var client = clientRepository.save(clientMapper.toEntity(clientDto));
        return clientMapper.toDto(client);
    }

    public ClientDto updateClient(Long id, ClientDto clientDto) {
        var clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            var newData = clientMapper.toEntity(clientDto);
            var client = clientOptional.get();
            client.setName(newData.getName());
            return clientMapper.toDto(client);
        } else throw new NoSuchElementException("cant find client with id " + clientDto.getId());
    }

    public boolean deleteClient(Long id) {
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @EventListener(ContextRefreshedEvent.class)
    private void fillRandomClients() {
        Faker faker = new Faker();
        if (clientRepository.count() == 0) {
            List<Client> randomClients = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                randomClients.add(Client.builder()
                        .name(faker.name().fullName())
                        .build());
            }
            clientRepository.saveAll(randomClients);
        }
    }
}
