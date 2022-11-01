package com.example.rsocket.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sceneId;
    @Column(name="description")
    private String sceneDescription;
}
