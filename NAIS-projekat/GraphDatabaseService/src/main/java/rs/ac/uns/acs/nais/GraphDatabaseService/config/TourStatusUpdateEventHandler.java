package rs.ac.uns.acs.nais.GraphDatabaseService.config;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.tourGraphDatabase.TourGraphStatus;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.tourRelationalDatabase.TourRelationalStatus;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Tour;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.TourRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.TourStatusPublisher;

import java.util.Objects;
import java.util.function.Consumer;

@Service
public class TourStatusUpdateEventHandler {

    @Autowired
    private TourRepository repository;

    @Autowired
    private TourStatusPublisher publisher;

    @Transactional
    public void updateTour(final Long id, Consumer<Tour> consumer){
        this.repository
                .findById(id)
                .ifPresent(consumer.andThen(this::updateTour));

    }

    private void updateTour(Tour tour){
        if(Objects.isNull(tour.getGraphStatus()))
            return;
        var isComplete = TourRelationalStatus.TOUR_CREATED.equals(tour.getRelationalStatus());
        var tourStatus = isComplete ? TourGraphStatus.TOUR_COMPLETED : TourGraphStatus.TOUR_CANCELLED;
        tour.setGraphStatus(tourStatus);
        if (!isComplete){
            this.publisher.raiseTourGraphEvent(tour, tourStatus);
        }
    }

}
