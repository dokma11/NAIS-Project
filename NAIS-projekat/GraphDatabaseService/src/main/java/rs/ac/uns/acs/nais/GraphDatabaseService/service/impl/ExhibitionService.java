package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Exhibition;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.ExhibitionRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IExhibitionService;

@Service
public class ExhibitionService  implements IExhibitionService{
    public final ExhibitionRepository repository;

    public ExhibitionService(ExhibitionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Exhibition> findAll() {
        return repository.findAll();
    }

    @Override
    public Exhibition create(Exhibition exhibition) {
        return repository.save(exhibition);
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
    public boolean update(Exhibition exhibitionForUpdate) {
        var exhibition = repository.findById(exhibitionForUpdate.getId());
        if(exhibition.isPresent()){
            repository.save(exhibition.get());
            return true;
        }
        return false;
    }
}
