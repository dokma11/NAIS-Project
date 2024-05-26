package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.stereotype.Service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TourRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ITourService;

import java.util.List;

@Service
public class TourService implements ITourService {

    public final TourRepository repository;

    public TourService(TourRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tour> findAll() {
        return repository.findAll();
    }

    @Override
    public Tour create(Tour tour) {
        return repository.save(tour);
    }

    @Override
    public boolean delete(String id) {
        var tour = repository.findById(Long.parseLong(id));
        if(tour.isPresent()){
            repository.delete(tour.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Tour tourForUpdate) {
        var tour = repository.findById(tourForUpdate.getId());
        if(tour.isPresent()){
            repository.save(tour.get());
            return true;
        }
        return false;
    }

//    @Override
//    public List<Product> recommendProductsByPurchaseHistory(Long customerId) {
//        return productRepository.recommendProductsByPurchaseHistory(customerId);
//    }
//
//    @Override
//    public List<Product> recommendProductsByReviews(Long customerId) {
//        return productRepository.recommendProductsByReviews(customerId);
//    }
//
//    @Override
//    public byte[] export(List<Product> products) throws IOException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        com.lowagie.text.Document document = new com.lowagie.text.Document();
//
//        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";
//
//        PdfWriter.getInstance(document, byteArrayOutputStream);
//        document.open();
//
//        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);
//
//        Paragraph title = new Paragraph("PRODUCT REPORT", titleFont);
//        title.setAlignment(Element.ALIGN_CENTER);
//        document.add(title);
//
//        PdfPTable reportTable = new PdfPTable(3);
//        reportTable.setWidthPercentage(100);
//
//        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
//        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Product", headerFont));
//        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Availability", headerFont));
//        PdfPCell headerCell3 = new PdfPCell(new Paragraph("ID", headerFont));
//
//        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
//        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
//        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));
//
//        reportTable.addCell(headerCell1);
//        reportTable.addCell(headerCell2);
//        reportTable.addCell(headerCell3);
//
//        for (Product product : products) {
//            reportTable.addCell(product.getName());
//            reportTable.addCell(String.valueOf(product.isAvailable()));
//            reportTable.addCell(product.getId());
//        }
//
//        document.add(reportTable);
//        document.close();
//
//        return byteArrayOutputStream.toByteArray();
//    }
//
}
