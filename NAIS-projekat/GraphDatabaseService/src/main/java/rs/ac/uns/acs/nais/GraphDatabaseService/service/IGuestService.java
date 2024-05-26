package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Guest;

import java.util.List;

public interface IGuestService {

    List<Guest> findAll();

    Guest create(Guest guest);

    boolean delete(String id);

    boolean update(Guest guestForUpdate);

}
