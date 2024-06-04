package org.example.museumservice.service.impl;

import org.example.museumservice.dto.TourRelationalDatabaseDto;
import org.example.museumservice.enums.TourCategory;
import org.example.museumservice.events.tourGraphDatabase.TourGraphEvent;
import org.example.museumservice.events.tourRelationalDatabase.TourRelationalEvent;
import org.example.museumservice.events.tourRelationalDatabase.TourRelationalStatus;
import org.example.museumservice.model.Exhibition;
import org.example.museumservice.model.Organizer;
import org.example.museumservice.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.example.museumservice.model.Tour;
import org.example.museumservice.repository.TourRepository;
import org.example.museumservice.core.service.impl.CRUDService;
import org.example.museumservice.service.ITourService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourService extends CRUDService<Tour, Integer> implements ITourService {

    @Autowired
    private final TourRepository tourRepository;

    @Autowired
    private final OrganizerRepository organizerRepository;

    public TourService(JpaRepository<Tour, Integer> repository, TourRepository tourRepository, OrganizerRepository organizerRepository) {
        super(repository);
        this.tourRepository = tourRepository;
        this.organizerRepository = organizerRepository;
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

    @Transactional
    public TourRelationalEvent newCreateTourEvent(TourGraphEvent tourEvent){
        var tour = tourEvent.getTour();
        List<Exhibition> exhibitions = new ArrayList<>();
        Organizer organizer = organizerRepository.findById(tour.getOrganizerId()).get();
        var dto = TourRelationalDatabaseDto.of(tour.getId(), tour.getName(), tour.getDescription(), tour.getDuration(), tour.getOccurrenceDateTime(),
                tour.getAdultTicketPrice(), tour.getMinorTicketPrice(), tour.getOrganizerId(), tour.getCapacity(), tour.getPicturePath());
        return this.tourRepository.findById(Math.toIntExact(tour.getId()))
                .map(ub -> {
                    this.tourRepository.save(Tour.of(Math.toIntExact(tour.getId()), tour.getName(), tour.getDescription(), exhibitions, tour.getDuration(), tour.getOccurrenceDateTime(),
                            tour.getAdultTicketPrice(), tour.getMinorTicketPrice(), organizer, tour.getPicturePath(), tour.getCapacity(), TourCategory.AFRICAN_CULTURE));
                    return new TourRelationalEvent(dto, TourRelationalStatus.TOUR_CREATED);
                })
                .orElse(new TourRelationalEvent(dto, TourRelationalStatus.TOUR_CANCELLED));
    }

    @Transactional
    public void cancelCreateTourEvent(TourGraphEvent tourEvent){
        this.tourRepository.findById(Math.toIntExact(tourEvent.getTour().getId()))
                .ifPresent(ut -> {
                    this.tourRepository.delete(ut);
                    });
    }

}
