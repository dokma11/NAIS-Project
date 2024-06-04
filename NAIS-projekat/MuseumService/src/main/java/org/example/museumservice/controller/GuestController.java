package org.example.museumservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.museumservice.dto.GuestResponseDTO;
import org.example.museumservice.service.IGuestService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/guests")
//@PreAuthorize("hasRole('GUEST')")
@RequiredArgsConstructor
public class GuestController {

    private final IGuestService guestService;

    private final ModelMapper modelMapper;

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        var guest = guestService.findById(id);
        var guestResponse = modelMapper.map(guest, GuestResponseDTO.class);
        return ResponseEntity.ok().body(guestResponse);
    }

}
