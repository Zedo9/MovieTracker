package Model;

import java.sql.JDBCType;
import java.util.ArrayList;

public class Movie {
    public boolean isFavourite;
    public boolean isWatchList;
    private int id;
    private ArrayList<String> genres;
    private float rating;
    private int length;
    private String posterLink;
    private String title;
    private String releaseDate;
    private String overview;
    private ArrayList<Movie> recommendations;
    private ArrayList<Actor> actors;

    public Movie(int id,
                 float rating,
                 int length,
                 String posterLink,
                 String title,
                 String releaseDate,
                 String overview,
                 boolean isFavourite,
                 boolean isWatchList){
        this.id = id;
        this.rating = rating;
        this.length = length;
        this.posterLink = posterLink;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.isFavourite = isFavourite;
        this.isWatchList = isWatchList;

    }


    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public ArrayList<Movie> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(ArrayList<Movie> recommendations) {
        this.recommendations = recommendations;
    }
    
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "isFavourite=" + isFavourite +
                ", isWatchList=" + isWatchList +
                ", id=" + id +
                ", rating=" + rating +
                ", length=" + length +
                ", posterLink='" + posterLink + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
