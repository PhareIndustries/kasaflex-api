package com.kasaflex.api.Repositories.client;

import com.kasaflex.api.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}