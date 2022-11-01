package com.example.rsocket.service;

import com.example.rsocket.Wrapper;
import com.example.rsocket.dto.MovieScene;
import com.example.rsocket.repo.MovieDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    MovieDataBaseService movieDataBaseService;
    Wrapper wrapper;
    @Autowired
    public MovieService(MovieDataBaseService movieDataBaseService, Wrapper wrapper){
        this.movieDataBaseService = movieDataBaseService;
        this.wrapper = wrapper;
    }
    private final List<MovieScene> scenes(){
      return wrapper.wrapDataFromTables(movieDataBaseService.findAll());
    }

    public List<MovieScene> getScenes(){
        return scenes();
    }

    public MovieScene getScene(int index){
        return scenes().get(index);
    }

}
