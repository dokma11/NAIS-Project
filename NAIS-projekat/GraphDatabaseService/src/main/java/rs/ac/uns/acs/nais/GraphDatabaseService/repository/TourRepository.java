package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Exhibition;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;

import java.util.List;

@Repository
public interface TourRepository extends Neo4jRepository<Tour, Long> {

    // Za generisanje pdfa, prosta sekcija pronaci sve ture u cenovnom rangu
    @Query("MATCH (t:Tour) " +
            "WHERE toFloat(t.adultTicketPrice) < 30 AND toFloat(t.adultTicketPrice) > 10 " +
            "AND toFloat(t.minorTicketPrice) < 30 AND toFloat(t.minorTicketPrice) > 10 " +
            "RETURN t"
    )
    List<Tour> findByPriceRange(String minPrice, String maxPrice);

    // Za generisanje pdfa, prosta sekcija pronaci sve ture koje pripadaju najucestalijoj kategoriji
    @Query("MATCH (tour:Tour) " +
            "WITH tour.category AS category, count(tour) AS categoryCount " +
            "ORDER BY categoryCount DESC " +
            "LIMIT 1 " +
            "MATCH (mostFrequentTourList:Tour {category: category}) " +
            "RETURN collect(mostFrequentTourList) AS tours "
    )
    List<Tour> findByMostFrequentCategory();

    // Pronalazi ture iste kategorije kao ture koje je gost kupio, a ne predlaze njih.
    @Query("MATCH (g:Guest {id: $guestId})-[:PURCHASED]->(purchased:Tour) " +
            "MATCH (similar:Tour) " +
            "WHERE similar.category = purchased.category AND NOT (g)-[:PURCHASED]->(similar) " +
            "WITH similar " +
            "RETURN DISTINCT similar " +
            "LIMIT 10")
    List<Tour> findSimilarToursViaPurchaseHistory(Long guestId);

    // Pronadje ture koje su kupilio drugi korisnici koji su kupili istu turu kao user (npr. ID=0)
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(commonTour:Tour)<-[:PURCHASED]-(otherGuest:Guest) " +
            "MATCH (otherGuest)-[:PURCHASED]->(recommendedTour:Tour) " +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour) " +
            "WITH DISTINCT recommendedTour " +
            "RETURN recommendedTour " +
            "LIMIT 10")
    List<Tour> findOtherUsersBought(Long guestId);

    // Koristice se za slozenu sekciju (npr. ID=4)
    // Ovaj upit pronalazi ture koje su kupili drugi korisnici koji su kupili iste ture kao prijavljeni korisnik i filtrira ih na osnovu kategorije
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(commonTour:Tour)<-[:PURCHASED]-(otherGuest:Guest) " +
            "MATCH (otherGuest)-[:PURCHASED]->(recommendedTour:Tour) " +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour) " +
            "AND commonTour.category = recommendedTour.category " +
            "WITH DISTINCT recommendedTour, count(DISTINCT otherGuest) AS popularity " +
            "RETURN recommendedTour " +
            "ORDER BY popularity DESC " +
            "LIMIT 10")
    List<Tour> findOtherUsersBoughtAndCategory(Long guestId);

    // Ovaj mi je malo bezveze
    // Ovaj upit pronalazi popularne ture koje su u skorije vreme
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(commonTour:Tour)<-[:PURCHASED]-(otherGuest:Guest) " +
            "MATCH (otherGuest)-[:PURCHASED]->(recommendedTour:Tour) " +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour) " +
            "AND datetime(recommendedTour.occurrenceDateTime) > datetime() " +
            "AND datetime(recommendedTour.occurrenceDateTime) < datetime() " +   // Ovde dodati granicu kao na 3 nedelje da bude
            "WITH DISTINCT recommendedTour, count(DISTINCT otherGuest) AS popularity " +
            "RETURN recommendedTour " +
            "ORDER BY popularity DESC, datetime(recommendedTour.occurrenceDateTime) ASC " +
            "LIMIT 10")
    List<Tour> findPopularInNearFuture(Long guestId);

    // Ovaj upit pronalazi ture koje imaju egzibicije koje je korisnik posetio na drugim svojim kupljenim turama (npr. ID = 3)
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)-[:HAS]->(exhibition:Exhibition) " +
            "MATCH (similarTour:Tour)-[:HAS]->(exhibition) " +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(similarTour) " +
            "WITH DISTINCT similarTour, count(similarTour) AS popularity " +
            "RETURN similarTour " +
            "ORDER BY popularity DESC " +
            "LIMIT 10")
    List<Tour> findBySimilarExhibitions(Long guestId);

    // Za egzibicije ovaj mi se vise svidja od gornjeg
    // Ovaj upit pronalazi ture koje imaju egzibicije koje je korisnik posetio na drugim svojim kupljenim turama TEMA
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)-[:HAS]->(exhibition:Exhibition) " +
            "MATCH (similarExhibition:Exhibition {theme: exhibition.theme}) " +
            "MATCH (recommendedTour:Tour)-[:HAS]->(similarExhibition) " +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour) " +
            "WITH DISTINCT recommendedTour, count(recommendedTour) AS popularity " +
            "RETURN recommendedTour " +
            "ORDER BY popularity DESC " +
            "LIMIT 10")
    List<Tour> findBySimilarExhibitionThemes(Long guestId);

    // Ovaj upit pronalazi ture koje imaju slicu temu egzibiicje i slicnu kategoriju kao tura (npr. ID=7)
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)-[:HAS]->(exhibition:Exhibition) " +
            "MATCH (similarExhibition:Exhibition {theme: exhibition.theme}) " +
            "MATCH (recommendedTour:Tour)-[:HAS]->(similarExhibition) " +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour) " +
            "AND recommendedTour.category = purchasedTour.category " +
            "WITH DISTINCT recommendedTour, count(recommendedTour) AS popularity " +
            "RETURN recommendedTour " +
            "ORDER BY popularity DESC " +
            "LIMIT 10")
    List<Tour> findBySimilarExhibitionThemesAndSimilarCategories(Long guestId);

    // Ovaj upit pronalazi ture koje je kreirao isti organizator (npr ID=6)
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)<-[:MADE]-(organizer:Organizer) " +
            "MATCH (organizer)-[:MADE]->(recommendedTour:Tour) " +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour) " +
            "WITH DISTINCT recommendedTour, count(recommendedTour) AS popularity " +
            "RETURN recommendedTour " +
            "ORDER BY popularity DESC " +
            "LIMIT 10")
    List<Tour> findByOrganizer(Long guestId);

    // Ovaj upit pronalazi ture koje je kreirao isti organizator a da su iste kategorije
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)<-[:MADE]-(organizer:Organizer) " +
            "MATCH (organizer)-[:MADE]->(recommendedTour:Tour) " +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour) " +
            "AND purchasedTour.category = recommendedTour.category " +
            "WITH DISTINCT recommendedTour, count(recommendedTour) AS popularity " +
            "RETURN recommendedTour " +
            "ORDER BY popularity DESC " +
            "LIMIT 10")
    List<Tour> findByOrganizerAndSimilarCategory(Long guestId);

    @Query("MATCH (t:Tour {id: $tourId}), (e:Exhibition {id: $exhibitionId}) " +
            "CREATE (t)-[h:HAS]->(e) " +
            "SET h.editTime = '$editTime'")
    void addExhibition(Long tourId, Long exhibitionId, String editTime);



    @Query("MATCH (o:Organizer {id: $organizerId}), (t:Tour {id: $tourId}) " +
            "CREATE (o)-[m:MADE]->(t) " +
            "SET h.editTime = '$editTime'")
    void makesTour(Long tourId, Long organizerId, String editTime);


    @Query("MATCH (g:Guest {id: $guestId}), (t:Tour {id: $tourId}) " +
           "CREATE (g)-[p:PURCHASED]->(t) " +
           "SET p.adultTicketNumber = '$adultTicketNumber', p.minorTicketNumber = '$minorTicketNumber', p.totalPrice = '$totalPrice'")
    void purchaseTour(Long tourId, Long guestId, String adultTicketNumber, String minorTicketNumber, String totalPrice);

    @Query("MATCH (t:Tour {id: $tourId})-[h:HAS]->(e:Exhibition {id: $exhibitionId}) " +
            "DELETE h")
    boolean removeExhibition(Long tourId, Long exhibitionId);

    @Query("MATCH (t:Tour {id: $tourId})-[r]->(m) " +
            "RETURN m ")
    List<Exhibition> findExhibitionsByTourId(Long tourId);


}
