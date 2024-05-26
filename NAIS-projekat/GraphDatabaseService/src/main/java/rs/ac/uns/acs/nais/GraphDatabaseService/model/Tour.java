package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import rs.ac.uns.acs.nais.GraphDatabaseService.enums.TourCategory;

import java.time.LocalDateTime;
import java.util.Date;

@Node
@Getter
@Setter
public class Tour {

    @Id
    private Long id;

    private String name;

    private String description;

    private String duration;

    private String occurrenceDateTime;

    private Integer organizerId;

    private String adultTicketPrice;

    private String minorTicketPrice;

    private String picturePath;

    private String capacity;

    private TourCategory category;

}
