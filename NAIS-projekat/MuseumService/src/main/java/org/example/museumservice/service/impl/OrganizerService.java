package org.example.museumservice.service.impl;

import org.example.museumservice.core.service.impl.CRUDService;
import org.example.museumservice.model.Organizer;
import org.example.museumservice.repository.OrganizerRepository;
import org.example.museumservice.service.IOrganizerService;
import org.example.museumservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService extends CRUDService<Organizer, Integer> implements IOrganizerService {

    private final IUserService userService;

    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerService(OrganizerRepository repository, OrganizerRepository organizerRepository, IUserService userService) {
        super(repository);
        this.organizerRepository = organizerRepository;
        this.userService = userService;
    }

    @Override
    public Organizer save(Organizer organizer) {
        // Izbacio nes za username i email provere
        return organizerRepository.save(organizer);
    }

    @Override
    public Organizer update(Organizer updated) {
        // Izbacio nes za username i email provere
        var oldGuest = findById(updated.getId());
        updated.setPassword(oldGuest.getPassword());
        updated.setRole(oldGuest.getRole());

        return organizerRepository.save(updated);
    }

    @Override
    public Organizer findByUsername(String username) {
        return organizerRepository.findByUsername(username).orElseThrow(null);
    }
}
