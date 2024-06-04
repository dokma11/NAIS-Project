package org.example.museumservice.events.tourRelationalDatabase;

import lombok.Data;
import org.example.museumservice.dto.TourRelationalDatabaseDto;
import org.example.museumservice.events.Event;

import java.util.Date;
import java.util.UUID;

@Data
public class TourRelationalEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();
    private TourRelationalDatabaseDto tourRelationalDatabaseDto;
    private TourRelationalStatus tourRelationalStatus;

    public TourRelationalEvent() {
    }

    public TourRelationalEvent(TourRelationalDatabaseDto tourRelationalDatabaseDto, TourRelationalStatus tourRelationalStatus) {
        this.tourRelationalDatabaseDto = tourRelationalDatabaseDto;
        this.tourRelationalStatus = tourRelationalStatus;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public TourRelationalDatabaseDto getTourRelationalDatabaseDto() {
        return tourRelationalDatabaseDto;
    }

    public TourRelationalStatus getTourRelationalStatus() {
        return tourRelationalStatus;
    }
}
