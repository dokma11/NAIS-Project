package org.example.museumservice.repository;

import org.example.museumservice.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
    Optional<Guest> findByUsername(String username);
}
