package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
public class Has {

    @RelationshipId
    private Long id;

    @TargetNode
    private Exhibition exhibition;

    private String editTime;

    public Has(){

    }

    public Has(Exhibition exhibition, String editTime) {
        this.exhibition = exhibition;
        this.editTime = editTime;
    }
}
