package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Poster;
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Title;

public class Global {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("poster")
    @Expose
    private Poster poster;

    @SerializedName("productionYear")
    @Expose
    private int productionYear;
    @SerializedName("__typename")
    @Expose
    private String typename;
}
