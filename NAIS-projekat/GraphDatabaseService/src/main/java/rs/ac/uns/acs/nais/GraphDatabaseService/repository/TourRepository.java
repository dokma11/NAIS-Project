package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;

import java.util.List;

@Repository
public interface TourRepository extends Neo4jRepository<Tour, Long> {

    @Query("MATCH (t:Tour)" +
            "WHERE t.adultTicketPrice < $maxPrice AND t.adultTicketPrice > $minPrice" +
            "WHERE t.minorTicketPrice < $maxPrice AND t.minorTicketPrice > $minPrice" +
            "RETURN t"
    )
    List<Tour> findByPriceRange(String minPrice, String maxPrice);

    @Query("MATCH (g:Guest {id: $guestId})-[:PURCHASED]->(purchased:Tour)" +
            "MATCH (similar:Tour)" +
            "WHERE similar.category = purchased.category AND NOT (g)-[:PURCHASED]->(similar)" +
            "WITH similar" +
            "RETURN DISTINCT similar" +
            "LIMIT 10")
    List<Tour> findSimilarToursViaPurchaseHistory(Long guestId);

    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(commonTour:Tour)<-[:PURCHASED]-(otherGuest:Guest)" +
            "MATCH (otherGuest)-[:PURCHASED]->(recommendedTour:Tour)" +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour)" +
            "WITH DISTINCT recommendedTour" +
            "RETURN recommendedTour" +
            "LIMIT 10")
    List<Tour> findOtherUsersBought(Long guestId);

    // Ovaj upit pronalazi ture koje su kupili drugi korisnici koji su kupili iste ture kao prijavljeni korisnik i filtrira ih na osnovu kategorije
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(commonTour:Tour)<-[:PURCHASED]-(otherGuest:Guest)" +
            "MATCH (otherGuest)-[:PURCHASED]->(recommendedTour:Tour)" +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour)" +
            "AND commonTour.category = recommendedTour.category" +
            "WITH DISTINCT recommendedTour, count(DISTINCT otherGuest) AS popularity" +
            "RETURN recommendedTour" +
            "ORDER BY popularity DESC" +
            "LIMIT 10")
    List<Tour> findOtherUsersBoughtAndCategory(Long guestId);

    // Ovaj mi je malo njesra
    // Ovaj upit pronalazi popularne ture koje su u skorije vreme
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(commonTour:Tour)<-[:PURCHASED]-(otherGuest:Guest)" +
            "MATCH (otherGuest)-[:PURCHASED]->(recommendedTour:Tour)" +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour)" +
            "AND datetime(recommendedTour.occurrenceDateTime) > datetime()" +
            "AND datetime(recommendedTour.occurrenceDateTime) < datetime()" +   // Ovde dodati granicu kao na 3 nedelje da bude
            "WITH DISTINCT recommendedTour, count(DISTINCT otherGuest) AS popularity" +
            "RETURN recommendedTour" +
            "ORDER BY popularity DESC, datetime(recommendedTour.occurrenceDateTime) ASC" +
            "LIMIT 10")
    List<Tour> findPopularInNearFuture(Long guestId);

    // Ovaj upit pronalazi ture koje imaju egzibicije koje je korisnik posetio na drugim svojim kupljenim turama
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)-[:HAS]->(exhibition:Exhibition)" +
            "MATCH (similarTour:Tour)-[:HAS]->(exhibition)" +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(similarTour)" +
            "WITH DISTINCT similarTour, count(similarTour) AS popularity" +
            "RETURN similarTour" +
            "ORDER BY popularity DESC" +
            "LIMIT 10")
    List<Tour> findBySimilarExhibitions(Long guestId);

    // Za egzibicije ovaj mi se vise svidja od gornjeg
    // Ovaj upit pronalazi ture koje imaju egzibicije koje je korisnik posetio na drugim svojim kupljenim turama
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)-[:HAS]->(exhibition:Exhibition)" +
            "MATCH (similarExhibition:Exhibition {theme: exhibition.theme})" +
            "MATCH (recommendedTour:Tour)-[:HAS]->(similarExhibition)" +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour)" +
            "WITH DISTINCT recommendedTour, count(recommendedTour) AS popularity" +
            "RETURN recommendedTour" +
            "ORDER BY popularity DESC" +
            "LIMIT 10")
    List<Tour> findBySimilarExhibitionThemes(Long guestId);

    // Ovaj upit pronalazi ture koje imaju slicu temu egzibiicje i slicnu kategoriju kao tura
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)-[:HAS]->(exhibition:Exhibition)" +
            "MATCH (similarExhibition:Exhibition {theme: exhibition.theme})" +
            "MATCH (recommendedTour:Tour)-[:HAS]->(similarExhibition)" +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour)" +
            "AND recommendedTour.category = purchasedTour.category" +
            "WITH DISTINCT recommendedTour, count(recommendedTour) AS popularity" +
            "RETURN recommendedTour" +
            "ORDER BY popularity DESC" +
            "LIMIT 10")
    List<Tour> findBySimilarExhibitionThemesAndSimilarCategories(Long guestId);

    // Ovaj upit pronalazi ture koje je kreirao isti organizator
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)<-[:MADE]-(organizer:Organizer)" +
            "MATCH (organizer)-[:MADE]->(recommendedTour:Tour)" +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour)" +
            "WITH DISTINCT recommendedTour, count(recommendedTour) AS popularity" +
            "RETURN recommendedTour" +
            "ORDER BY popularity DESC" +
            "LIMIT 10")
    List<Tour> findByOrganizer(Long guestId);

    // Ovaj upit pronalazi ture koje je kreirao isti organizator a da su iste kategorije
    @Query("MATCH (loggedInGuest:Guest {id: $guestId})-[:PURCHASED]->(purchasedTour:Tour)<-[:MADE]-(organizer:Organizer)" +
            "MATCH (organizer)-[:MADE]->(recommendedTour:Tour)" +
            "WHERE NOT (loggedInGuest)-[:PURCHASED]->(recommendedTour)" +
            "AND purchasedTour.category = recommendedTour.category" +
            "WITH DISTINCT recommendedTour, count(recommendedTour) AS popularity" +
            "RETURN recommendedTour" +
            "ORDER BY popularity DESC" +
            "LIMIT 10")
    List<Tour> findByOrganizerAndSimilarCategory(Long guestId);

}
