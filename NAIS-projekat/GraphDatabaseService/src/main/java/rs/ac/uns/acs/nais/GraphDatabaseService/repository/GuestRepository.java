package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Guest;

@Repository
public interface GuestRepository extends Neo4jRepository<Guest, Long> {

    @Query("MATCH (g:Guest {id: $guestId})-[p:PURCHASED]->(t:Tour {id: $tourId}) " +
            "SET p.adultTicketNumber = '$newNumber' " +
            "RETURN p ")
    void updateAdultTicketPriceNumber(String guestId, String tourId, String newNumber);

    @Query("MATCH (g:Guest {id: $guestId})-[p:PURCHASED]->(t:Tour {id: $tourId}) " +
            "SET p.minorTicketNumber = '$newNumber' " +
            "RETURN p ")
    void updateMinorTicketPriceNumber(String guestId, String tourId, String newNumber);

}
