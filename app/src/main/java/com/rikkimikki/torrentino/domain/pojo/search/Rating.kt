package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("kinopoisk")
    @Expose
    private Kinopoisk kinopoisk;
    @SerializedName("__typename")
    @Expose
    private String typename;

    public Kinopoisk getKinopoisk() {
        return kinopoisk;
    }

    public void setKinopoisk(Kinopoisk kinopoisk) {
        this.kinopoisk = kinopoisk;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
