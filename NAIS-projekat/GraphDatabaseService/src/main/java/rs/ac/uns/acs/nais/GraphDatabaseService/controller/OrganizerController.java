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

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Organizer;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IOrganizerService;


@RestController
@RequestMapping("/organizer.json")
public class OrganizerController {
    
    private final IOrganizerService organizerService;

    public OrganizerController(IOrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public ResponseEntity<?> findAllOrganizers() {
        List<Organizer> organizers = organizerService.findAll();
        return new ResponseEntity<>(organizers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Organizer organizer){    // Mozda DTO treba
        Organizer createdOrganizer = organizerService.create(organizer);
        return new ResponseEntity<>(createdOrganizer, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") String id){
        if(organizerService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Organizer organizer){
        if(organizerService.update(organizer)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateEditTime")
    public ResponseEntity<?> updateEditTime(@RequestParam("organizerId") String organizerId, @RequestParam("tourId") String tourId,
                                                          @RequestParam("adultTicketPriceNumber") String newTime){
        organizerService.updateEditTime(organizerId, tourId, newTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
