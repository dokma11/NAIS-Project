package org.example.museumservice.repository;

import org.example.museumservice.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
    Optional<Organizer> findByUsername(String username);
}
