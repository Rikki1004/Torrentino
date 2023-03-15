package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("kinopoisk")
    @Expose
    private KinopoiskReviews kinopoiskReviews;

    public KinopoiskReviews getKinopoiskReviews() {
        return kinopoiskReviews;
    }

    public void setKinopoiskReviews(KinopoiskReviews kinopoiskReviews) {
        this.kinopoiskReviews = kinopoiskReviews;
    }
}
