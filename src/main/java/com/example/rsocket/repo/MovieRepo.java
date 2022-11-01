package com.example.rsocket.repo;

import com.example.rsocket.tables.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepo extends JpaRepository<Movie, Integer> {
}
