package com.example.rsocket;

import com.example.rsocket.dto.MovieScene;
import com.example.rsocket.tables.Movie;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class Wrapper {

    public List<MovieScene> wrapDataFromTables(List<Movie> movieList){
        List<MovieScene> movieSceneList = new ArrayList<MovieScene>();
        try{
            for (Movie movie:movieList) {
                movieSceneList.add(new MovieScene(movie.getSceneId(), movie.getSceneDescription()));
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return  movieSceneList;
    }
}
