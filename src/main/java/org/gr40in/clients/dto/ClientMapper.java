package org.gr40in.clients.dto;

import org.gr40in.clients.dao.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public class ClientMapper {

    public Client toEntity(ClientDto clientDto) {
        return Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .build();
    }

    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        return clientDto;
    }

    public List<Client> listToEntity(List<ClientDto> clientsDto) {
        return clientsDto.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<ClientDto> listToDto(List<Client> clients) {
        return clients.stream()
                .map(this::toDto)
                .toList();
    }

}
