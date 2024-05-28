package org.example.museumservice.mapper;

import org.example.museumservice.dto.ExhibitionResponseDTO;
import org.example.museumservice.dto.OrganizerResponseDTO;
import org.example.museumservice.model.Exhibition;
import org.example.museumservice.model.Organizer;
import org.springframework.stereotype.Component;

@Component
public class ExhibitionMapper {

    public ExhibitionResponseDTO mapToDTO(Exhibition exhibition) {
        ExhibitionResponseDTO dto = new ExhibitionResponseDTO();
        dto.setId(exhibition.getId());
        dto.setName(exhibition.getName());
        dto.setPicture(exhibition.getPicture());
        dto.setShortDescription(exhibition.getShortDescription());
        dto.setLongDescription(exhibition.getLongDescription());
        dto.setTheme(exhibition.getTheme());
        dto.setStartDate(String.valueOf(exhibition.getStartDate()));
        dto.setEndDate(String.valueOf(exhibition.getEndDate()));
        dto.setPrice(exhibition.getPrice());
        dto.setOrganizer(mapToOrganizerDTO(exhibition.getOrganizer()));

        return dto;
    }

    private OrganizerResponseDTO mapToOrganizerDTO(Organizer organizer) {
        if (organizer == null) {
            return null;
        }
        OrganizerResponseDTO organizerDTO = new OrganizerResponseDTO();
        organizerDTO.setId(organizer.getId());
        organizerDTO.setUsername(organizer.getUsername());
        organizerDTO.setEmail(organizer.getEmail());
        organizerDTO.setFirstName(organizer.getFirstName());
        organizerDTO.setLastName(organizer.getLastName());
        organizerDTO.setRole(organizer.getRole());
        organizerDTO.setBiography(organizer.getBiography());

        return organizerDTO;
    }

}