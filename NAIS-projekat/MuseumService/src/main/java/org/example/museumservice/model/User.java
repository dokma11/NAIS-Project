package org.example.museumservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.example.museumservice.enums.Role;

@Entity
@Data
@Table(name = "_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Enumerated
    private Role role;

    @Column(nullable = false)
    private Boolean isAccountLocked = false;

}
