package com.example.MovieRecommender.models;
import jakarta.persistence.*;
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;
    private String title;
    private String genre;
    private int year;
    private String director;
    private  int popularity;
    public Movie(String title, String genre, int year, String director ) {
        this.title = title.toLowerCase();
        this.genre = genre.toLowerCase();
        this.year = year;
        this.director = director.toLowerCase();
    }

    public Movie() {

    }

    public String getTitle() {
        return this.title;
    }
    public String getGenre() {
        return this.genre;
    }
    public int getYear() {
        return this.year;
    }
    public String getDirector() {
        return this.director;
    }
    public int getPopularity() {return this.popularity;}
    public void updatePopularity() {
        this.popularity += 1;
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public String getString(){
        return "-> " + this.title + ": " + this.genre + " | " + Integer.toString(this.year) + " | " + this.director + " | " + this.popularity;
    }
}
