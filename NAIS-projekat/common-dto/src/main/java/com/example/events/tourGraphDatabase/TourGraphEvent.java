package com.example.events.inventory;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

import com.example.dto.TourGraphDatabaseDto;
import com.example.dto.TourRelationalDatabaseDto;
import com.example.events.Event;

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
