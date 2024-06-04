package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Getter;
import lombok.Setter;

@Node
@Getter
@Setter
public class Organizer {
    @Id
    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Relationship(value = "MADE", direction = Relationship.Direction.OUTGOING)
    private List<Make> made = new ArrayList<>();
}
