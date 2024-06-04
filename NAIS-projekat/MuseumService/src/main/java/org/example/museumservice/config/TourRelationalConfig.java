package org.example.museumservice.config;

import org.example.museumservice.events.tourGraphDatabase.TourGraphEvent;
import org.example.museumservice.events.tourGraphDatabase.TourGraphStatus;
import org.example.museumservice.events.tourRelationalDatabase.TourRelationalEvent;
import org.example.museumservice.service.impl.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class TourRelationalConfig {

    @Autowired
    private TourService tourService;

    @Bean
    public Function<Flux<TourGraphEvent>, Flux<TourRelationalEvent>> tourRelationalProcessor() {
        return flux -> flux.flatMap(this::processTourRelational);
    }

    private Mono<TourRelationalEvent> processTourRelational(TourGraphEvent event){
        if(event.getStatus().equals(TourGraphStatus.TOUR_CREATED)){
            return Mono.fromSupplier(() -> this.tourService.newCreateTourEvent(event));
        }else{
            return Mono.fromRunnable(() -> this.tourService.cancelCreateTourEvent(event));
        }
    }

}
