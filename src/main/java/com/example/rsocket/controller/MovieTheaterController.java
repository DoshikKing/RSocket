package com.example.rsocket.controller;

import com.example.rsocket.dto.MovieScene;
import com.example.rsocket.dto.TicketRequest;
import com.example.rsocket.dto.TicketStatus;
import com.example.rsocket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.function.Function;
import org.springframework.messaging.handler.annotation.MessageMapping;

@Controller
public class MovieTheaterController {

    @Autowired
    private MovieService movieService;

    @MessageMapping("ticket.cancel")
    public void cancelTicket(Mono<TicketRequest> request){
        // cancel and refund asynchronously
        request
                .doOnNext(t -> t.setStatus(TicketStatus.TICKET_CANCELLED))
                .doOnNext(t -> System.out.println("cancelTicket :: " + t.getRequestId() + " : " + t.getStatus()))
                .subscribe();
    }

    @MessageMapping("ticket.purchase")
    public Mono<TicketRequest> purchaseTicket(Mono<TicketRequest> request){
        return request
                .doOnNext(t -> t.setStatus(TicketStatus.TICKET_ISSUED))
                .doOnNext(t -> System.out.println("purchaseTicket :: " + t.getRequestId() + " : " + t.getStatus()));

    }

    @MessageMapping("movie.stream")
    public Flux<MovieScene> playMovie(Mono<TicketRequest> request){
        return request
                .map(t -> t.getStatus().equals(TicketStatus.TICKET_ISSUED) ? this.movieService.getScenes() : Collections.emptyList())
                .flatMapIterable(Function.identity())
                .cast(MovieScene.class)
                .delayElements(Duration.ofSeconds(1));
    }

}
