package org.example.museumservice.events.tourGraphDatabase;

import lombok.Data;
import org.example.museumservice.dto.TourGraphDatabaseDto;
import org.example.museumservice.events.Event;

import java.util.Date;
import java.util.UUID;

@Data
public class TourGraphEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private TourGraphDatabaseDto tour;
    private TourGraphStatus status;

    public TourGraphEvent() {
    }

    public TourGraphEvent(TourGraphDatabaseDto tour, TourGraphStatus status) {
        this.tour = tour;
        this.status = status;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public TourGraphDatabaseDto getTour() {
        return tour;
    }

    public TourGraphStatus getStatus() {
        return status;
    }

}
