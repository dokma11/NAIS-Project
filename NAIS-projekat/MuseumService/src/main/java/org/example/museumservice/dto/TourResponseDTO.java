package org.example.museumservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TourResponseDTO {

    private Integer id;

    private String name;

    private String description;

    //private List<ExhibitionResponseDTO> exhibitions = new ArrayList<>();

    private String duration;

    private LocalDateTime occurrenceDateTime;

    private String adultTicketPrice;

    private String minorTicketPrice;

    private Integer guideId;

    private String capacity;

    private String picturePath;

    private TourCategory category;

}
