package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Top {
    @SerializedName("topResult")
    @Expose
    private TopResult topResult;
    @SerializedName("movies")
    @Expose
    private List<Movie> movies = null;

    public TopResult getTopResult() {
        return topResult;
    }

    public void setTopResult(TopResult topResult) {
        this.topResult = topResult;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
