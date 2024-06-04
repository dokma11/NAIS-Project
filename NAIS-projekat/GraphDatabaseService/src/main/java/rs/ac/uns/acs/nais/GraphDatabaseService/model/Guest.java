package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
@Getter
@Setter
public class Guest {

    @Id
    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Relationship(value = "PURCHASED", direction = Relationship.Direction.OUTGOING)
    private List<Purchase> purchased = new ArrayList<>();

}
