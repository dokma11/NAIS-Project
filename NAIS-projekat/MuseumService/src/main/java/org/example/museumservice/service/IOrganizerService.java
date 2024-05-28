package org.example.museumservice.service;

import org.example.museumservice.core.service.ICRUDService;
import org.example.museumservice.model.Organizer;

public interface IOrganizerService extends ICRUDService<Organizer, Integer> {
    Organizer update(Organizer updated);
    Organizer findByUsername(String username);
}
