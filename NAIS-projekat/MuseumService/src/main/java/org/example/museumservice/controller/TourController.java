package org.example.museumservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.museumservice.dto.TourCreateDTO;
import org.example.museumservice.dto.TourResponseDTO;
import org.example.museumservice.dto.TourUpdateDTO;
import org.example.museumservice.model.Exhibition;
import org.example.museumservice.model.Tour;
import org.example.museumservice.service.IExhibitionService;
import org.example.museumservice.service.IOrganizerService;
import org.example.museumservice.service.ITourService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final ITourService tourService;
    private final IExhibitionService exhibitionService;
    private final IOrganizerService organizerService;

    private final ModelMapper modelMapper;

    @PostMapping
    //@PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<?> create(@RequestBody TourCreateDTO tourCreateDTO) {
        var exhibitionDTOs = tourCreateDTO.getExhibitions();
        // mora zbog mapiranja
        tourCreateDTO.setExhibitions(null);
        var tour = modelMapper.map(tourCreateDTO, Tour.class);

        List<Exhibition> fetchedExhibitions = exhibitionDTOs.stream()
                .map(exhibition -> exhibitionService.findById(exhibition.getId()))
                .collect(Collectors.toList());
        tour.setExhibitions(fetchedExhibitions);

        var organizer = organizerService.findById(tourCreateDTO.getOrganizerId());
        tour.setOrganizer(organizer);

        tour.setDuration(String.valueOf(exhibitionDTOs.size() * 45 + (exhibitionDTOs.size() - 1) * 5));

        tourService.save(tour);

        return ResponseEntity.status(HttpStatus.CREATED).body(tourCreateDTO);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Tour> tours = tourService.findAll();
        var tourResponse = tours.stream()
                .map(tour -> modelMapper.map(tour, TourResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(tourResponse);
    }

    @PutMapping
    //@PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<?> update(@RequestBody TourUpdateDTO tourUpdateDTO) {
        var exhibitionDTOs = tourUpdateDTO.getExhibitions();
        // mora zbog mapiranja
        tourUpdateDTO.setExhibitions(null);
        var tour = modelMapper.map(tourUpdateDTO, Tour.class);

        List<Exhibition> fetchedExhibitions = exhibitionDTOs.stream()
                .map(exhibition -> exhibitionService.findById(exhibition.getId()))
                .collect(Collectors.toList());
        tour.setExhibitions(fetchedExhibitions);

        var organizer = organizerService.findById(tourUpdateDTO.getOrganizerId());
        tour.setOrganizer(organizer);

        tourService.update(tour);

        return ResponseEntity.status(HttpStatus.CREATED).body(tourUpdateDTO);
    }

    @DeleteMapping(value = "/{id}")
    //@PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Tour tour = tourService.findById(id);

        if (tour != null) {
            tourService.delete(tour);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
