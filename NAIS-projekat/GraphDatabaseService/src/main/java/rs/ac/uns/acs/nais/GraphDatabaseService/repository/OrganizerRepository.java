package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Organizer;

public interface OrganizerRepository extends Neo4jRepository<Organizer, Long>{

    
} 
