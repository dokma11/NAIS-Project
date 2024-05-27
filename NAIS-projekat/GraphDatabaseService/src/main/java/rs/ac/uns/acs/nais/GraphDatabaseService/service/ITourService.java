package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import java.util.List;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;

public interface ITourService {
    List<Tour> findAll();
    Tour create(Tour tour);
    boolean delete(String id);
    boolean update(Tour tour);
    List<Tour> findByPriceRange(String minPrice, String maxPrice);
}
