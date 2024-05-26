package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;
import java.util.Date;

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


    private String totalPrice;

    public Purchase() {
    }

    public Purchase(Long id, Tour tour, String adultTicketNumber, String minorTicketNumber, String totalPrice) {
        this.id = id;
        this.tour = tour;
        this.adultTicketNumber = adultTicketNumber;
        this.minorTicketNumber = minorTicketNumber;
        this.totalPrice = totalPrice;
    }

}
