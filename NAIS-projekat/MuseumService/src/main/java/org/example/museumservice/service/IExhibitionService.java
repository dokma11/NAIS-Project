package org.example.museumservice.service;

import java.util.List;

import org.example.museumservice.core.service.ICRUDService;
import org.example.museumservice.model.Exhibition;

public interface IExhibitionService extends ICRUDService<Exhibition, Integer> {
    List<Exhibition> findAll();
} 
