package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import java.util.List;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;

public interface ITourService {
    List<Tour> findAll();
    Tour create(Tour tour);
    boolean delete(String id);
    boolean update(Tour tour);
    List<Tour> findByPriceRange(String minPrice, String maxPrice);
    List<Tour> findByMostFrequentCategory();
    List<Tour> findOtherUsersBoughtAndCategory(Long guestId);
    List<Tour> findOtherUsersBought(Long guestId);
    List<Tour> findSimilarToursViaPurchaseHistory(Long guestId);
    List<Tour> findPopularInNearFuture(Long guestId);
    List<Tour> findBySimilarExhibitions(Long guestId);
    List<Tour> findBySimilarExhibitionThemes(Long guestId);
    List<Tour> findBySimilarExhibitionThemesAndSimilarCategories(Long guestId);
    List<Tour> findByOrganizer(Long guestId);
    List<Tour> findByOrganizerAndSimilarCategory(Long guestId);
    void addExhibition(Long tourId, Long exhibitionId);
    void makesTour(Long tourId, Long organizerId);
    void purchaseTour(Long tourId, Long guestId, String adultTicketNumber, String minorTicketNumber, String totalPrice);

}
