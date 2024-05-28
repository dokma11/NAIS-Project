package org.example.museumservice.repository;

import org.example.museumservice.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Integer> {

}
