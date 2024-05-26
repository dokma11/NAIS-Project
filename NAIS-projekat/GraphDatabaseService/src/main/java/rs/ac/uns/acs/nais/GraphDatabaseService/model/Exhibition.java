package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import java.time.LocalDateTime;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import lombok.Getter;
import lombok.Setter;

import rs.ac.uns.acs.nais.GraphDatabaseService.enums.ExhibitionTheme;

@Node
@Getter
@Setter
public class Exhibition {

    @Id
    private Long id;

    private String name;

    private String picture;

    private String shortDescription;

    private String longDescription;

    private ExhibitionTheme theme;

    private LocalDateTime startDate;

    private LocalDateTime endDate; 

    private Integer price; 

}
