package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Exhibition;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;

import java.util.List;

import rs.ac.uns.acs.nais.GraphDatabaseService.service.ITourService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.TourService;

@RestController
@RequestMapping("/tours.json")
public class TourController {

    private final ITourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public ResponseEntity<?> findAllTours() {
        List<Tour> tours = tourService.findAll();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Tour tour){    // Mozda DTO treba
        Tour createdTour = tourService.create(tour);
        return new ResponseEntity<>(createdTour, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") String id){
        if(tourService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Tour tour){    // Mozda DTO dodati
        if(tourService.update(tour)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findOtherUsersBought")
    public ResponseEntity<?> findOtherUsersBought(@RequestParam("id") Long id) {
        List<Tour> tours = tourService.findOtherUsersBought(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/findSimilarToursViaPurchaseHistory")
    public ResponseEntity<?> findSimilarToursViaPurchaseHistory(@RequestParam("id") Long id) {
        List<Tour> tours = tourService.findSimilarToursViaPurchaseHistory(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/findPopularInNearFuture")     // MORAM PROMENITI
    public ResponseEntity<?> findPopularInNearFuture(@RequestParam("id") Long id) {
        List<Tour> tours = tourService.findPopularInNearFuture(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/findBySimilarExhibitions")
    public ResponseEntity<?> findBySimilarExhibitions(@RequestParam("id") Long id) {
        List<Tour> tours = tourService.findBySimilarExhibitions(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/findBySimilarExhibitionThemes")
    public ResponseEntity<?> findBySimilarExhibitionThemes(@RequestParam("id") Long id) {
        List<Tour> tours = tourService.findBySimilarExhibitionThemes(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/findBySimilarExhibitionThemesAndSimilarCategories")
    public ResponseEntity<?> findBySimilarExhibitionThemesAndSimilarCategories(@RequestParam("id") Long id) {
        List<Tour> tours = tourService.findBySimilarExhibitionThemesAndSimilarCategories(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/findByOrganizer")
    public ResponseEntity<?> findByOrganizer(@RequestParam("id") Long id) {
        List<Tour> tours = tourService.findByOrganizer(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/findByOrganizerAndSimilarCategory")
    public ResponseEntity<?> findByOrganizerAndSimilarCategory(@RequestParam("id") Long id) {
        List<Tour> tours = tourService.findByOrganizerAndSimilarCategory(id);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @PostMapping("/addExhibition")
    public ResponseEntity<?> addExhibition(@RequestParam("tourId") Long tourId, @RequestParam("exhibitionId") Long exhibitionId){
        tourService.addExhibition(tourId, exhibitionId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/makesTour")
    public ResponseEntity<?> makesTour(@RequestParam("tourId") Long tourId, @RequestParam("organizerId") Long organizerId){
        tourService.makesTour(tourId, organizerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/purchaseTour")
    public ResponseEntity<?> purchaseTour(@RequestParam("tourId") Long tourId, @RequestParam("guestId") Long guestId, @RequestParam("adultTicketNumber") String adultTicketNumber, @RequestParam("minorTicketNumber") String minorTicketNumber, @RequestParam("totalPrice") String totalPrice){
        tourService.purchaseTour(tourId, guestId, adultTicketNumber, minorTicketNumber, totalPrice);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @DeleteMapping("/removeExhibition")
    public ResponseEntity<?> removeExhibition(@RequestParam("tourId") Long tourId, @RequestParam("exhibitionId") Long exhibitionId){
        if(tourService.removeExhibition(tourId, exhibitionId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/exhibitions")
    public ResponseEntity<?> findByExhibitionsByTourId(@RequestParam("tourId") Long tourId) {
        List<Exhibition> exhibitions = tourService.findExhibitionsByTourId(tourId);
        return new ResponseEntity<>(exhibitions, HttpStatus.OK);
    }

}

