package org.example.museumservice.controller;

import org.example.museumservice.service.IExhibitionService;

@RestController
@RequestMapping("/api/exhibitions")
@RequiredArgsConstructor
public class ExhibitionController {
    
    private final IExhibitionService exhibitionService;
    private final ModelMapper modelMapper;
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
