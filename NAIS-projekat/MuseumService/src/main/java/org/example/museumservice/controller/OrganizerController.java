package org.example.museumservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.museumservice.dto.OrganizerResponseDTO;
import org.example.museumservice.service.IOrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/organizers")
//@PreAuthorize("hasRole('GUEST')")
@RequiredArgsConstructor
public class OrganizerController {

    private final IOrganizerService organizerService;

    private final ModelMapper modelMapper;

    @GetMapping(path = "{id}")
    //@PreAuthorize("hasAnyRole('ORGANIZER', 'GUEST')")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        var organizer = organizerService.findById(id);
        var organizerResponse = modelMapper.map(organizer, OrganizerResponseDTO.class);
        return ResponseEntity.ok().body(organizerResponse);
    }

}
