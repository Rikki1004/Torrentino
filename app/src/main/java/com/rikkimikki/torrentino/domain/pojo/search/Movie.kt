package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("movie")
    @Expose
    private Movie__1 movie;
    @SerializedName("__typename")
    @Expose
    private String typename;

    public Movie__1 getMovie() {
        return movie;
    }

    public void setMovie(Movie__1 movie) {
        this.movie = movie;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTitleRU() {
        return movie.getTitle().getRussian();
    }
    //public int getReleaseDate(){return movie.getProductionYear();}
    public String getPoster(){ return movie.getPoster();}
    public String getTitleORIG() {
        return movie.getTitle().getOriginal();
    }
    public double getRating() {
        return movie.getRating().getKinopoisk().getValue();
    }
}
