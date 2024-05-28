package org.example.museumservice.service.impl;

import org.example.museumservice.core.service.impl.CRUDService;
import org.example.museumservice.model.Guest;
import org.example.museumservice.service.IGuestService;
import org.example.museumservice.service.IUserService;
import org.example.museumservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService extends CRUDService<Guest, Integer> implements IGuestService {

    private final IUserService userService;

    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository repository, GuestRepository guestRepository, IUserService userService) {
        super(repository);
        this.guestRepository = guestRepository;
        this.userService = userService;
    }

    @Override
    public Guest save(Guest guest) {
        // Uklonio za username i email
        return guestRepository.save(guest);
    }

    @Override
    public Guest update(Guest updated) {
        // Uklonio za username i email
        var oldGuest = findById(updated.getId());
        updated.setPassword(oldGuest.getPassword());
        updated.setRole(oldGuest.getRole());

        return guestRepository.save(updated);
    }

    @Override
    public Guest findByUsername(String username) {
        return guestRepository.findByUsername(username).orElseThrow();
    }
}
