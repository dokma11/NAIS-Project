package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Guest;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.GuestRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IGuestService;

import java.util.List;

@Service
public class GuestService implements IGuestService {

    public final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Guest> findAll() {
        return repository.findAll();
    }

    @Override
    public Guest create(Guest guest) {
        return repository.save(guest);
    }

    @Override
    public boolean delete(String id) {
        var guest = repository.findById(Long.parseLong(id));
        if(guest.isPresent()){
            repository.delete(guest.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Guest guestForUpdate) {
        var guest = repository.findById(guestForUpdate.getId());
        if(guest.isPresent()){
            repository.save(guest.get());
            return true;
        }
        return false;
    }

}
