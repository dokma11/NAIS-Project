package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.tourGraphDatabase.TourGraphStatus;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Exhibition;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TourRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ITourService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TourService implements ITourService {

    private final TourRepository tourRepository;

    @Autowired
    private TourStatusPublisher publisher;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour create(Tour tour) {
        return tourRepository.save(tour);
    }

    @Transactional
    public Tour createTransactional(Tour tour) {
        Tour tourReturn = tourRepository.save(tour);
        this.publisher.raiseTourGraphEvent(tourReturn, TourGraphStatus.TOUR_CREATED);
        return tourReturn;
    }

    @Override
    public boolean delete(String id) {
        var tour = tourRepository.findById(Long.parseLong(id));
        if(tour.isPresent()){
            tourRepository.delete(tour.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Tour tourForUpdate) {
        var tour = tourRepository.findById(tourForUpdate.getId());
        if(tour.isPresent()){
            tourRepository.save(tour.get());
            return true;
        }
        return false;
    }

    //korisceno za pdf
    public List<Tour> findByPriceRange(Integer minPrice, Integer maxPrice){
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

    public void removeExhibition(Long tourId, Long exhibitionId){
        tourRepository.removeExhibition(tourId, exhibitionId);
    }

    public List<Exhibition> findExhibitionsByTourId(Long tourId){
        return tourRepository.findExhibitionsByTourId(tourId);
    }

    public void cancelPurchasedTour(Long guestId, Long tourId){
        tourRepository.cancelPurchasedTour(guestId, tourId);
    }

    public void deleteConnectionsTour(Long orgainzerId, Long tourId){
        tourRepository.deleteConnectionsTour(orgainzerId, tourId);
    }

    public List<Tour> findToursByGuestId(Long guestId){
        return tourRepository.findToursByGuestId(guestId);
    }

    public List<Tour> findToursByOrganizerId(Long organizerId){
        return tourRepository.findToursByOrganizerId(organizerId);
    }

    public List<Tour> findForComplexPdf(Integer guestId, String mostFrequentCategory, Integer minPrice, Integer maxPrice){
        return tourRepository.findForComplexPdf(guestId, mostFrequentCategory, minPrice, maxPrice);
    }
}
