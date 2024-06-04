package org.example.museumservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.museumservice.mapper.ExhibitionMapper;
import org.example.museumservice.service.IExhibitionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exhibitions")
@RequiredArgsConstructor
public class ExhibitionController {
    
    private final IExhibitionService exhibitionService;
    private final ExhibitionMapper exhibitionMapper;

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        var exhibition = exhibitionService.findById(id);
        var exhibitionResponse = exhibitionMapper.mapToDTO(exhibition);
        return ResponseEntity.ok().body(exhibitionResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        var exhibitions = exhibitionService.findAll();
        var response = exhibitions.stream()
                .map(exhibitionMapper::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
