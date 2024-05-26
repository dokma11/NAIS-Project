package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.Getter;
import lombok.Setter;

@RelationshipProperties
@Getter
@Setter

public class Make {

    @RelationshipId
    private Long id;

    @TargetNode
    private Tour tour;

    private LocalDateTime editTime;


}
