package org.example.museumservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.museumservice.enums.ExhibitionTheme;

@Entity
@Data
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String picture;

    @Column
    private String shortDescription;

    @Column
    private String longDescription;

    @Enumerated(EnumType.STRING)
    private ExhibitionTheme theme;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "tours_exhibitions", joinColumns = @JoinColumn(name = "tour_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "exhibition_id", referencedColumnName = "id"))
    private List<Tour> tours = new ArrayList<>();
    
}
