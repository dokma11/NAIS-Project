package org.example.museumservice.model;

import org.example.museumservice.enums.TourCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "tours_exhibitions", joinColumns = @JoinColumn(name = "tour_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "exhibition_id", referencedColumnName = "id"))
    private List<Exhibition> exhibitions = new ArrayList<>();

    @NotEmpty
    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private LocalDateTime occurrenceDateTime;

    @NotEmpty
    @Column(nullable = false)
    private String adultTicketPrice;

    @NotEmpty
    @Column(nullable = false)
    private String minorTicketPrice;

//    @ManyToOne
//    @JoinColumn(name = "organizer_id")
//    private Organizer organizer;

    @NotEmpty
    @Column(nullable = false)
    private String picturePath;

    @NotEmpty
    @Column(nullable = false)
    private String capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(20)")
    private TourCategory category;

}