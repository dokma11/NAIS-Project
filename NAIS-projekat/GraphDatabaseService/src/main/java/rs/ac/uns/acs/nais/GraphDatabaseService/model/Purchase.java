package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;

@RelationshipProperties
@Getter
@Setter
public class Purchase {

    @RelationshipId
    private Long id;

    @TargetNode
    private Tour tour;

    private String adultTicketNumber;

    private String minorTicketNumber;

    private LocalDateTime reservationDateTime;

    private String totalPrice;

}
