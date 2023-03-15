package com.rikkimikki.torrentino.domain.pojo.tvSerie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Film;

public class Data {
    @SerializedName("tvSeries")
    @Expose
    private Film film;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
