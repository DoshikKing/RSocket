package com.example.rsocket.repo;

import com.example.rsocket.tables.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional
@Slf4j
public class MovieDataBaseService {
    MovieRepo movieRepo;

    @Autowired
    public MovieDataBaseService(MovieRepo movieRepo){
        this.movieRepo = movieRepo;
    }

    public List<Movie> findAll(){
        List<Movie> movieList = new ArrayList<Movie>();
        try {
            movieList = movieRepo.findAll();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return  movieList;
    }

}
