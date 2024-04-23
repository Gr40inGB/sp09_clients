package org.gr40in.clients.controller;

import lombok.RequiredArgsConstructor;
import org.gr40in.clients.dto.ClientDto;
import org.gr40in.clients.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client")
public class ClientApiController {
    private final ClientService clientService;

    @RequestMapping
    public ResponseEntity<List<ClientDto>> findAllClients() {
        return ResponseEntity.ok().body(clientService.findAllClients());
    }

    @PostMapping("{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable long id) {
        try {
            return ResponseEntity.ok().body(clientService.findClientById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        try {
            return ResponseEntity.ok().body(clientService.updateClient(id, clientDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        return ResponseEntity.ok().body(clientService.createClient(clientDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ClientDto> deleteClientById(@PathVariable Long id) {
        return clientService.deleteClient(id) ?
                ResponseEntity.accepted().build() : ResponseEntity.badRequest().build();
    }
}
