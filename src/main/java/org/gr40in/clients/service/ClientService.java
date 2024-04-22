package org.gr40in.clients.service;


import lombok.RequiredArgsConstructor;
import org.gr40in.clients.dao.Client;
import org.gr40in.clients.dto.ClientDto;
import org.gr40in.clients.dto.ClientMapper;
import org.gr40in.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

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
        var client = clientMapper.toEntity(clientDto);
        client.setId(0);
    }


}
