package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import java.util.List;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Exhibition;

public interface IExhibitionService {
    List<Exhibition> findAll();
    Exhibition create(Exhibition tour);
    boolean delete(String id);
    boolean update(Exhibition tour);
}
