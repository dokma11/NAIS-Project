package rs.ac.uns.acs.nais.GraphDatabaseService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.tourRelationalDatabase.TourRelationalEvent;

import java.util.function.Consumer;

@Configuration
public class EventHandlerConfig {

    @Autowired
    private TourStatusUpdateEventHandler tourStatusUpdateEventHandler;

    @Bean
    public Consumer<TourRelationalEvent> tourRelationalEventConsumer(){
        return pe -> {
            tourStatusUpdateEventHandler.updateTour(pe.getTourRelationalDatabaseDto().getId(), po -> {
                po.setRelationalStatus(pe.getTourRelationalStatus());
            });
        };
    }

}
