package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Exhibition;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IExhibitionService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.ExhibitionService;

@RestController
@RequestMapping("/exhibition.json")
public class ExhibitionController {

    private final IExhibitionService exhibitionService;

    public ExhibitionController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @GetMapping
    public ResponseEntity<?> findAllExhibitions() {
        List<Exhibition> exhibitions = exhibitionService.findAll();
        return new ResponseEntity<>(exhibitions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Exhibition exhibition){    // Mozda DTO treba
        Exhibition createdExhibition= exhibitionService.create(exhibition);
        return new ResponseEntity<>(createdExhibition, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") String id){
        if(exhibitionService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Exhibition exhibition){    // Mozda DTO dodati
        if(exhibitionService.update(exhibition)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
