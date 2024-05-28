package org.example.museumservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.museumservice.enums.ExhibitionTheme;

@Getter
@Setter
public class ExhibitionResponseDTO {
    private Integer id;
    private String name;
    private String picture;
    private String shortDescription;
    private String longDescription;
    private ExhibitionTheme theme;
    private String startDate;
    private String endDate;
    private Integer price;
    private OrganizerResponseDTO organizer; // Assuming OrganizerResponseDTO is defined
}
