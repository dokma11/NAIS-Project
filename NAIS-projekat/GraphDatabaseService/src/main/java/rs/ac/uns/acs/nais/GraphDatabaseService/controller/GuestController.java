package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Guest;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IGuestService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.GuestService;

import java.util.List;

@RestController
@RequestMapping("/guests.json")
public class GuestController {

    private final IGuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Guest> guests = guestService.findAll();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Guest guest){    // Mozda DTO treba
        Guest createdGuest = guestService.create(guest);
        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") String id){
        if(guestService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Guest guest){    // Mozda DTO dodati
        if(guestService.update(guest)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateAdultTickerPriceNumber")
    public ResponseEntity<?> updateAdultTicketPriceNumber(@RequestParam("guestId") String guestId, @RequestParam("tourId") String tourId,
                                                          @RequestParam("adultTicketPriceNumber") String newNumber){
        guestService.updateAdultTicketPriceNumber(guestId, tourId, newNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateMinorTickerPriceNumber")
    public ResponseEntity<?> updateMinorTicketPriceNumber(@RequestParam("guestId") String guestId, @RequestParam("tourId") String tourId,
                                                          @RequestParam("adultTicketPriceNumber") String newNumber){
        guestService.updateMinorTicketPriceNumber(guestId, tourId, newNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
