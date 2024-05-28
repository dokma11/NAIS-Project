package org.example.museumservice.dto;

import lombok.Data;
import org.example.museumservice.enums.Role;

@Data
public class EmployeeResponseDTO {
    private Integer id;
    private String name;
    private Role role;
    private String email;
    private Boolean isAccountLocked;
}
