package rs.ac.uns.acs.nais.GraphDatabaseService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import rs.ac.uns.acs.nais.GraphDatabaseService.events.tourGraphDatabase.TourGraphEvent;

import java.util.function.Supplier;

@Configuration
public class TourGraphConfig {

    @Bean
    public Sinks.Many<TourGraphEvent> tourSink(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<TourGraphEvent>> tourSupplier(Sinks.Many<TourGraphEvent> sink){
        return sink::asFlux;
    }

}
