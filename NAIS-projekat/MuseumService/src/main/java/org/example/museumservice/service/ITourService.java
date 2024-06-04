package org.example.museumservice.service;

import org.example.museumservice.core.service.ICRUDService;
import org.example.museumservice.events.tourGraphDatabase.TourGraphEvent;
import org.example.museumservice.events.tourRelationalDatabase.TourRelationalEvent;
import org.example.museumservice.model.Tour;

import java.util.List;

public interface ITourService extends ICRUDService<Tour, Integer> {
    List<Tour> findAll();
    Tour update(Tour updated);
    void delete(Tour updated);
    TourRelationalEvent newCreateTourEvent(TourGraphEvent tourEvent);
    void cancelCreateTourEvent(TourGraphEvent tourEvent);
}
