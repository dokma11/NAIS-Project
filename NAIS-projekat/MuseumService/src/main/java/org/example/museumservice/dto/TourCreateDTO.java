package org.example.museumservice.dto;

//import com.veljko121.backend.dto.ExhibitionResponseDTO;
//import com.veljko121.backend.model.Exhibition;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import org.example.museumservice.enums.TourCategory;
import java.util.ArrayList;
import java.util.List;

import org.example.museumservice.enums.TourCategory;

@Getter
@Setter
public class TourCreateDTO {

    private String name;

    private String description;

    private List<ExhibitionResponseDTO> exhibitions = new ArrayList<>();

    private String duration;

    private LocalDateTime occurrenceDateTime;

    private String adultTicketPrice;

    private String minorTicketPrice;

    private Integer organizerId;

    private String capacity;

    private String picturePath;

    private TourCategory category;

}