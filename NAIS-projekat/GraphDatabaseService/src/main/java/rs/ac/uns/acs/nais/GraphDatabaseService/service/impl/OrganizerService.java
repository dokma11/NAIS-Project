package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Organizer;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.OrganizerRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IOrganizerService;

@Service 
public class OrganizerService implements IOrganizerService {
    public final OrganizerRepository repository;

    public OrganizerService(OrganizerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Organizer> findAll() {
        return repository.findAll();
    }

    @Override
    public Organizer create(Organizer organizer) {
        return repository.save(organizer);
    }

    @Override
    public boolean delete(String id) {
        var organizer = repository.findById(Long.parseLong(id));
        if(organizer.isPresent()){
            repository.delete(organizer.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Organizer organizerForUpdate) {
        var organizer = repository.findById(organizerForUpdate.getId());
        if(organizer.isPresent()){
            repository.save(organizer.get());
            return true;
        }
        return false;
    }

    @Override
    public Organizer findById(String id) {
        var organizer = repository.findById(Long.valueOf(id));
        return organizer.orElse(null);
    }

    public void updateEditTime(String organizerId, String tourId, String newTime){
        repository.updateEditTime(organizerId, tourId, newTime);
    }

}
