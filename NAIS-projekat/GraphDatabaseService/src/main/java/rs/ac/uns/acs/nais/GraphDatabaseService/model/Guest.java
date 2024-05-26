package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Getter
@Setter
public class Guest {

    @Id
    private Integer id;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

}
