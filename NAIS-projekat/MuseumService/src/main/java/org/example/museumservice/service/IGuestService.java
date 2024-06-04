package org.example.museumservice.service;

import org.example.museumservice.core.service.ICRUDService;
import org.example.museumservice.model.Guest;

public interface IGuestService extends ICRUDService<Guest, Integer> {
    Guest update(Guest updated);
    Guest findByUsername(String username);
}
