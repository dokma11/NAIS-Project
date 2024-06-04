package org.example.museumservice.dto;

import lombok.Data;
import org.example.museumservice.enums.Role;

@Data
public class UserResponseDTO {

    private Integer id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private Role role;

}
