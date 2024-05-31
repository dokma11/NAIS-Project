package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Exhibition;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TourRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ITourService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TourService implements ITourService {

    public final TourRepository repository;
    private final TourRepository tourRepository;

    public TourService(TourRepository repository, TourRepository tourRepository) {
        this.repository = repository;
        this.tourRepository = tourRepository;
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

    //korisceno za pdf
    public List<Tour> findByPriceRange(String minPrice, String maxPrice){
        return tourRepository.findByPriceRange(minPrice, maxPrice);
    }
    //korisceno za pdf
    public List<Tour> findByMostFrequentCategory(){
        return tourRepository.findByMostFrequentCategory();
    }
    //korisceno za pdf
    public List<Tour> findOtherUsersBoughtAndCategory(Long guestId){
        return tourRepository.findOtherUsersBoughtAndCategory(guestId);
    }
    
    public List<Tour> findOtherUsersBought(Long guestId){
        return tourRepository.findOtherUsersBought(guestId);
    }

    public List<Tour> findSimilarToursViaPurchaseHistory(Long guestId){
        return tourRepository.findSimilarToursViaPurchaseHistory(guestId);
    }

    public List<Tour> findPopularInNearFuture(Long guestId){
        return tourRepository.findPopularInNearFuture(guestId);
    }

    public List<Tour> findBySimilarExhibitions(Long guestId){
        return tourRepository.findBySimilarExhibitions(guestId);
    }

    public List<Tour> findBySimilarExhibitionThemes(Long guestId){
        return tourRepository.findBySimilarExhibitionThemes(guestId);
    }

    public List<Tour> findBySimilarExhibitionThemesAndSimilarCategories(Long guestId){
        return tourRepository.findBySimilarExhibitionThemesAndSimilarCategories(guestId);
    }

    public  List<Tour> findByOrganizer(Long guestId){
        return tourRepository.findByOrganizer(guestId);
    }

    public List<Tour> findByOrganizerAndSimilarCategory(Long guestId){
        return tourRepository.findByOrganizerAndSimilarCategory(guestId);
    }

    public void addExhibition(Long tourId, Long exhibitionId){
        String date = LocalDateTime.now().toString();
        tourRepository.addExhibition(tourId, exhibitionId, date);
    }

    public void makesTour(Long tourId, Long organizerId){
        String date = LocalDateTime.now().toString();
        tourRepository.makesTour(tourId, organizerId, date);
    }

    public void purchaseTour(Long tourId, Long guestId, String adultTicketNumber, String minorTicketNumber, String totalPrice){
        tourRepository.purchaseTour(tourId, guestId, adultTicketNumber, minorTicketNumber,totalPrice);
    }

    public boolean removeExhibition(Long tourId, Long exhibitionId){
        return tourRepository.removeExhibition(tourId, exhibitionId);
    }

    public List<Exhibition> findExhibitionsByTourId(Long tourId){
        return tourRepository.findExhibitionsByTourId(tourId);
    }


    public  boolean cancelPurchasedTour(Long guestId, Long tourId){
        return tourRepository.cancelPurchasedTour(guestId, tourId);
    }

    public boolean deleteConnectionsTour(Long orgainzerId, Long tourId){
        return tourRepository.deleteConnectionsTour(orgainzerId, tourId);
    }

    public List<Tour> findToursByGuestId(Long guestId){
        return tourRepository.findToursByGuestId(guestId);
    }

    public List<Tour> findToursByOrganizerId(Long organizerId){
        return tourRepository.findToursByOrganizerId(organizerId);
    }

}
