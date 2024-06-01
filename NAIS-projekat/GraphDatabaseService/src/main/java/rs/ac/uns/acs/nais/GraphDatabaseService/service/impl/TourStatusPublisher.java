package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TourGraphDatabaseDto;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.tourGraphDatabase.TourGraphEvent;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.tourGraphDatabase.TourGraphStatus;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;

import java.time.LocalDateTime;

@Service
public class TourStatusPublisher {

    @Autowired
    private Sinks.Many<TourGraphEvent> tourSink;

    public void raiseTourGraphEvent(final Tour tour, TourGraphStatus tourGraphStatus){
        var dto = TourGraphDatabaseDto.of(
                tour.getId(),
                tour.getName(),
                tour.getDescription(),
                tour.getDuration(),
                LocalDateTime.parse(tour.getOccurrenceDateTime()),
                tour.getAdultTicketPrice(),
                tour.getMinorTicketPrice(),
                tour.getOrganizerId(),
                tour.getCapacity(),
                tour.getPicturePath()
        );
        var tourGraphEvent = new TourGraphEvent(dto, tourGraphStatus);
        this.tourSink.tryEmitNext(tourGraphEvent);
    }

}
