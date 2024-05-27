package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.stereotype.Service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TourRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ITourService;

import java.util.List;

@Service
public class TourService implements ITourService {

    public final TourRepository repository;
    private final TourRepository tourRepository;

    public TourService(TourRepository repository, TourRepository tourRepository) {
        this.repository = repository;
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> findAll() {
        return repository.findAll();
    }

    @Override
    public Tour create(Tour tour) {
        return repository.save(tour);
    }

    @Override
    public boolean delete(String id) {
        var tour = repository.findById(Long.parseLong(id));
        if(tour.isPresent()){
            repository.delete(tour.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Tour tourForUpdate) {
        var tour = repository.findById(tourForUpdate.getId());
        if(tour.isPresent()){
            repository.save(tour.get());
            return true;
        }
        return false;
    }

    public List<Tour> findByPriceRange(String minPrice, String maxPrice){
        return tourRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Tour> findByMostFrequentCategory(){
        return tourRepository.findByMostFrequentCategory();
    }

}
