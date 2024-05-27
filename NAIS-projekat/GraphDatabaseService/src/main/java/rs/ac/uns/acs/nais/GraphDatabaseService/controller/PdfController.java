package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPdfService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/pdfs.json")
public class PdfController {

    private final IPdfService pdfService;

    public PdfController(IPdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/in-range-pdf/{id}")
    public ResponseEntity<InputStreamResource> generateToursInPriceRangePdf(@PathVariable String id) throws DocumentException, IOException {
        ByteArrayInputStream bis = pdfService.generateToursInPriceRangePdf(Integer.valueOf(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cleansed-items.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping("/category-pdf/{id}")
    public ResponseEntity<InputStreamResource> generateTourByMostFrequentCategoryPdf(@PathVariable String id) throws DocumentException, IOException {
        ByteArrayInputStream bis = pdfService.generateToursByMostFrequentCategoryPdf(Integer.valueOf(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cleansed-items.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping("/others-category-pdf/{id}")
    public ResponseEntity<InputStreamResource> generateToursByOthersPurchasesAndCategoryPdf(@PathVariable String id) throws DocumentException, IOException {
        ByteArrayInputStream bis = pdfService.generateToursByOthersPurchasesAndCategoryPdf(Integer.valueOf(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cleansed-items.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
