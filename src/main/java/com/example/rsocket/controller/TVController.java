package com.example.rsocket.controller;

import com.example.rsocket.dto.MovieScene;
import com.example.rsocket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class TVController {

    @Autowired
    private MovieService movieService;

    @MessageMapping("tv.movie")
    public Flux<MovieScene> playMovie(Flux<Integer> sceneIndex){
        return sceneIndex
                .map(index -> index - 1)
                .map(this.movieService::getScene)
                .delayElements(Duration.ofSeconds(1));
    }

}
