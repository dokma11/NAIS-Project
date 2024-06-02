package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import com.lowagie.text.DocumentException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface IPdfService {
    ByteArrayInputStream generateToursInPriceRangePdf(Integer requestedById, Integer minPrice, Integer maxPrice) throws DocumentException, IOException, com.itextpdf.text.DocumentException;
    ByteArrayInputStream generateToursByMostFrequentCategoryPdf(Integer requestedById) throws DocumentException, IOException, com.itextpdf.text.DocumentException;
    ByteArrayInputStream generateToursByOthersPurchasesAndCategoryPdf(Integer requestedById) throws DocumentException, IOException, com.itextpdf.text.DocumentException;
    ByteArrayInputStream generatePdf(Integer requestedById, Integer minPrice, Integer maxPrice) throws DocumentException, IOException, com.itextpdf.text.DocumentException;
}
