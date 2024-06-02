package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import com.itextpdf.text.Document;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IOrganizerService;
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

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfService implements IPdfService {

    @Autowired
    private final ITourService tourService;

    @Autowired
    private final IOrganizerService organizerService;

    public PdfService(TourService tourService, OrganizerService organizerService){
        this.tourService = tourService;
        this.organizerService = organizerService;
    }

    @Override
    public ByteArrayInputStream generateToursInPriceRangePdf(Integer requestedById, Integer minPrice, Integer maxPrice) throws DocumentException, IOException, com.itextpdf.text.DocumentException {
        List<Tour> tours = tourService.findByPriceRange(minPrice, maxPrice);
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
        String desktopPath = System.getProperty("user.home") + "/Desktop/personal_tour_requests_report" + timestamp + ".pdf";

        File desktopDir = new File(System.getProperty("user.home") + "/Desktop");
        if (!desktopDir.exists()) {
            desktopDir.mkdirs();
        }

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, new FileOutputStream(desktopPath));
        PdfWriter.getInstance(document, out);

        document.open();
        addContentToDocument(document, requestedById, tours, false, false);
        document.close();

        out.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream generateToursByMostFrequentCategoryPdf(Integer requestedById) throws DocumentException, IOException, com.itextpdf.text.DocumentException {
        List<Tour> tours = tourService.findByMostFrequentCategory();
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
        String desktopPath = System.getProperty("user.home") + "/Desktop/personal_tour_requests_report" + timestamp + ".pdf";

        File desktopDir = new File(System.getProperty("user.home") + "/Desktop");
        if (!desktopDir.exists()) {
            desktopDir.mkdirs();
        }

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, new FileOutputStream(desktopPath));
        PdfWriter.getInstance(document, out);

        document.open();
        addContentToDocument(document, requestedById, tours, true, false);
        document.close();

        out.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream generateToursByOthersPurchasesAndCategoryPdf(Integer requestedById) throws DocumentException, IOException, com.itextpdf.text.DocumentException {
        List<Tour> tours = tourService.findOtherUsersBoughtAndCategory(requestedById.longValue()); // treba proveriti
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
        String desktopPath = System.getProperty("user.home") + "/Desktop/personal_tour_requests_report" + timestamp + ".pdf";

        File desktopDir = new File(System.getProperty("user.home") + "/Desktop");
        if (!desktopDir.exists()) {
            desktopDir.mkdirs();
        }

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, new FileOutputStream(desktopPath));
        PdfWriter.getInstance(document, out);

        document.open();
        addContentToDocument(document, requestedById, tours, false, true);
        document.close();

        out.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addContentToDocument(Document document, Integer requestedById, List<Tour> tours, boolean category, boolean complex) throws DocumentException, com.itextpdf.text.DocumentException {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph heading;
        if(category){
            heading = new Paragraph("Tours with the most frequent category", font);
        }
        else if(complex){
            heading = new Paragraph("Recommended tours ", font);
        }
        else {
            heading = new Paragraph("Tours in given price range", font);
        }

        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);

        document.add(new Paragraph(" "));

        Font metaFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        document.add(new Paragraph("Generated on: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE), metaFont));

        if(category){
            document.add(new Paragraph("Category: " + tours.get(0).getCategory().toString(), metaFont));
        }

        document.add(new Paragraph(" "));

        PdfPTable table;

        table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 2, 2, 2, 2, 2, 3});

        Font tableHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Name", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Description", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Duration", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Occurrence date and time", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Adult ticket price", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Minor ticket price", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Category", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Organized by", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        Font tableFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        for (Tour tour : tours) {
            var organizer = organizerService.findById(String.valueOf(tour.getOrganizerId()));

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(tour.getName(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getDescription(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getDuration(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getOccurrenceDateTime(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getAdultTicketPrice(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getMinorTicketPrice(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getCategory().toString(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            if(organizer != null){
                cell = new PdfPCell(new Phrase(organizer.getFirstName() + ' ' + organizer.getLastName(), tableFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            else{
                cell = new PdfPCell(new Phrase("None", tableFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

        }

        document.add(table);
    }

    @Override
    public ByteArrayInputStream generatePdf(Integer requestedById, Integer minPrice, Integer maxPrice) throws DocumentException, IOException, com.itextpdf.text.DocumentException {
        List<Tour> toursInPriceRange = tourService.findByPriceRange(minPrice, maxPrice);
        List<Tour> mostFrequentCategoryTours = tourService.findByMostFrequentCategory();

        System.out.println(mostFrequentCategoryTours.get(0).getCategory().toString());
        System.out.println(mostFrequentCategoryTours.get(0).getCategory().toString());
        System.out.println(mostFrequentCategoryTours.get(0).getCategory().toString());
        System.out.println(mostFrequentCategoryTours.get(0).getCategory().toString());

        List<Tour> complexSectionTours = tourService.findForComplexPdf(requestedById, mostFrequentCategoryTours.get(0).getCategory().toString(), minPrice, maxPrice);

        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
        String desktopPath = System.getProperty("user.home") + "/Desktop/personal_tour_requests_report" + timestamp + ".pdf";

        File desktopDir = new File(System.getProperty("user.home") + "/Desktop");
        if (!desktopDir.exists()) {
            desktopDir.mkdirs();
        }

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, new FileOutputStream(desktopPath));
        PdfWriter.getInstance(document, out);

        document.open();
        addComplexContentToDocument(document, requestedById, toursInPriceRange, mostFrequentCategoryTours, complexSectionTours);
        document.close();

        out.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addComplexContentToDocument(Document document, Integer requestedById, List<Tour> toursInPriceRange, List<Tour> mostFrequentCategoryTours, List<Tour> complexSectionTours) throws DocumentException, com.itextpdf.text.DocumentException {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph heading;

        heading = new Paragraph("Tours in given price range", font);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);

        document.add(new Paragraph(" "));

        Font metaFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        document.add(new Paragraph("Generated on: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE), metaFont));

        document.add(new Paragraph(" "));

        PdfPTable table;

        table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 2, 2, 2, 2, 2, 3});

        Font tableHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Name", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Description", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Duration", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Occurrence date and time", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Adult ticket price", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Minor ticket price", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Category", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Organized by", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        Font tableFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        for (Tour tour : toursInPriceRange) {
            var organizer = organizerService.findById(String.valueOf(tour.getOrganizerId()));

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(tour.getName(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getDescription(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getDuration(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getOccurrenceDateTime(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getAdultTicketPrice(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getMinorTicketPrice(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getCategory().toString(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            if(organizer != null){
                cell = new PdfPCell(new Phrase(organizer.getFirstName() + ' ' + organizer.getLastName(), tableFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            else{
                cell = new PdfPCell(new Phrase("None", tableFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

        }

        document.add(table);

        document.add(new Paragraph(" "));

        heading = new Paragraph("Tours with the most frequent category", font);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Generated on: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE), metaFont));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Category: " + mostFrequentCategoryTours.get(0).getCategory().toString(), metaFont));

        document.add(new Paragraph(" "));

        table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 2, 2, 2, 2, 2, 3});

//        Font tableHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//
//        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Name", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Description", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Duration", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Occurrence date and time", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Adult ticket price", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Minor ticket price", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Category", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Organized by", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        for (Tour tour : mostFrequentCategoryTours) {
            var organizer = organizerService.findById(String.valueOf(tour.getOrganizerId()));

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(tour.getName(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getDescription(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getDuration(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getOccurrenceDateTime(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getAdultTicketPrice(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getMinorTicketPrice(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getCategory().toString(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            if(organizer != null){
                cell = new PdfPCell(new Phrase(organizer.getFirstName() + ' ' + organizer.getLastName(), tableFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            else{
                cell = new PdfPCell(new Phrase("None", tableFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

        }

        document.add(table);

        document.add(new Paragraph(" "));

        heading = new Paragraph("Recommended tours based on what other users bought, price range and most frequent category", font);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Generated on: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE), metaFont));

        document.add(new Paragraph(" "));

        table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 2, 2, 2, 2, 2, 3});

        hcell = new PdfPCell(new Phrase("Name", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Description", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Duration", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Occurrence date and time", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Adult ticket price", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Minor ticket price", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Category", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Organized by", tableHeaderFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        for (Tour tour : complexSectionTours) {
            var organizer = organizerService.findById(String.valueOf(tour.getOrganizerId()));

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(tour.getName(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getDescription(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getDuration(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getOccurrenceDateTime(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getAdultTicketPrice(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getMinorTicketPrice(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(tour.getCategory().toString(), tableFont));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            if(organizer != null){
                cell = new PdfPCell(new Phrase(organizer.getFirstName() + ' ' + organizer.getLastName(), tableFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            else{
                cell = new PdfPCell(new Phrase("None", tableFont));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

        }

        document.add(table);

    }
}
