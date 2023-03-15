package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Film {
    @SerializedName("title")
    @Expose
    private Title title;

    @SerializedName("mainTrailer")
    @Expose
    private MainTraller mainTraller;

    @SerializedName("productionYear")
    @Expose
    private int dateRelease;

    @SerializedName("rating")
    @Expose
    private Rating rating;

    @SerializedName("synopsis")
    @Expose
    private String overview;

    @SerializedName("poster")
    @Expose
    private Poster poster;

    @SerializedName("ott")
    @Expose
    private Ott ott;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public Ott getOtt() {
        return ott;
    }

    public void setOtt(Ott ott) {
        this.ott = ott;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public int getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(int dateRelease) {
        this.dateRelease = dateRelease;
    }

    public MainTraller getMainTraller() {
        return mainTraller;
    }

    public void setMainTraller(MainTraller mainTraller) {
        this.mainTraller = mainTraller;
    }
}
