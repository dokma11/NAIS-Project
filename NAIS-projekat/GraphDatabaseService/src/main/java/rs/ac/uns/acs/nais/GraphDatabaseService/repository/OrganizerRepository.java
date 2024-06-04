package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Make;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Organizer;

public interface OrganizerRepository extends Neo4jRepository<Organizer, Long>{

    @Query("MATCH (o:Organizer {id: $organizerId})-[m:MADE]->(t:Tour {id: $tourId}) " +
            "SET m.editTime = $newTime " +
            "RETURN m ")
    void updateEditTime(Integer organizerId, Integer tourId, String newTime);

} 
