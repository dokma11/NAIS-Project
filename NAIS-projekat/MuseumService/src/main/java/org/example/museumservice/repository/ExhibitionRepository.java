package org.example.museumservice.repository;

import org.example.museumservice.model.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {

    
} 
