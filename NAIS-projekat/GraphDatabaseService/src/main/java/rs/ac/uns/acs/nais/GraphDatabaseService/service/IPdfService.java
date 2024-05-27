package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import com.lowagie.text.DocumentException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface IPdfService {
    ByteArrayInputStream generateToursInPriceRangePdf(Integer requestedById) throws DocumentException, IOException;
    ByteArrayInputStream generateToursByMostFrequentCategoryPdf(Integer requestedById) throws DocumentException, IOException;

}
