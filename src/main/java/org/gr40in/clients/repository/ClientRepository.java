package org.gr40in.clients.repository;

import org.gr40in.clients.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
