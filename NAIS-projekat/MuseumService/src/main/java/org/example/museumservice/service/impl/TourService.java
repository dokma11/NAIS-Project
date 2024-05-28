package org.example.museumservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.example.museumservice.model.Tour;
import org.example.museumservice.repository.TourRepository;
import org.example.museumservice.core.service.impl.CRUDService;
import org.example.museumservice.service.ITourService;

import java.util.List;

@Service
public class TourService extends CRUDService<Tour, Integer> implements ITourService {

    @Autowired
    private final TourRepository tourRepository;

    public TourService(JpaRepository<Tour, Integer> repository, TourRepository tourRepository) {
        super(repository);
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Tour update(Tour updated) {
        return tourRepository.save(updated);
    }

    @Override
    public void delete(Tour tour){
        tourRepository.delete(tour);
    }

}
