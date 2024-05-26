package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("recommendByPurchase")
//    public ResponseEntity<List<Product>> recommendProductsByPurchaseHistory(@RequestParam("customerId") Long customerId){
//
//        return new ResponseEntity<>(productService.recommendProductsByPurchaseHistory(customerId),HttpStatus.OK);
//    }
//    @GetMapping("recommendByReview")
//    public ResponseEntity<List<Product>> recommendProductsByReviews(@RequestParam("customerId") Long customerId){
//
//        return new ResponseEntity<>(productService.recommendProductsByReviews(customerId),HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<byte[]> exportPdf() {
//        List<Product> products = productService.findAllProducts();
//        try {
//            byte[] pdfContents = productService.export(products);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_PDF);
//            headers.setContentDispositionFormData("attachment", "products.pdf");
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(pdfContents);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
}

