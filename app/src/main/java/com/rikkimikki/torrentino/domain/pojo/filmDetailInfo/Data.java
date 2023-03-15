package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("film")
    @Expose
    private Film film;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
