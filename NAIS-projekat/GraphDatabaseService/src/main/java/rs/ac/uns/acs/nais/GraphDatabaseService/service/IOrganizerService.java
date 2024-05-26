package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import java.util.List;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Organizer;

public interface IOrganizerService {

    List<Organizer> findAll();
    Organizer create(Organizer tour);
    boolean delete(String id);
    boolean update(Organizer tour);
    
} 
