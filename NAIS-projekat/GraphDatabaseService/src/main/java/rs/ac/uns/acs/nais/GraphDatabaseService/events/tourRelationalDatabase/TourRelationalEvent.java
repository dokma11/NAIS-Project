package rs.ac.uns.acs.nais.GraphDatabaseService.events.tourRelationalDatabase;

import lombok.Data;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TourRelationalDatabaseDto;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.Event;

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
