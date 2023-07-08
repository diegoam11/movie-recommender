package entities.vertices;

public class Movie {

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
    public int getPopularity() {return popularity;}

    public String getString(){
        return "-> " + this.title + ": " + this.genre + " | " + Integer.toString(this.year) + " | " + this.director + " | " + this.popularity;
    }
}
