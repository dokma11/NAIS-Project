package org.example.museumservice.service.impl;

import org.example.museumservice.service.IExhibitionService;
import org.springframework.stereotype.Service;

import java.util.List;

import org.example.museumservice.core.service.impl.CRUDService;
import org.example.museumservice.model.Exhibition;
import org.example.museumservice.repository.ExhibitionRepository;

@Service
public class ExhibitionService extends CRUDService<Exhibition, Integer> implements IExhibitionService{

    private final ExhibitionRepository exhibitionRepository;

    public ExhibitionService(ExhibitionRepository repository) {
        super(repository);
        exhibitionRepository = repository;
    }

    @Override
    public List<Exhibition> findAll() {
        return exhibitionRepository.findAll();
    }

}
