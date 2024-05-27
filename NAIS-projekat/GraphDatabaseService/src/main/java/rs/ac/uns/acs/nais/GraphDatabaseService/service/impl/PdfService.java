package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import com.itextpdf.text.Document;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPdfService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ITourService;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfService implements IPdfService {

    @Autowired
    private final ITourService tourService;

    public PdfService(TourService tourService){
        this.tourService = tourService;
    }

    @Override
    public ByteArrayInputStream generateToursInPriceRangePdf(Integer requestedById) throws DocumentException, IOException{
        List<Tour> tours = tourService.findByPriceRange("200", "500"); // treba proveriti
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //PdfWriter.getInstance(document, out); // neki error nmp

        document.open();
        addContentToDocument(document, requestedById, tours);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addContentToDocument(Document document, Integer requestedById, List<Tour> tours) throws DocumentException {
        // TODO
    }

}
