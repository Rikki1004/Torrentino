package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Poster;
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.Title;

import java.util.List;

public class Movie__1 {
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
    @SerializedName("viewOption")
    @Expose
    private Object viewOption;
    @SerializedName("__typename")
    @Expose
    private String typename;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("productionYear")
    @Expose
    private int productionYear;

    @SerializedName("releaseYears")
    @Expose
    private List<ReleaseYear> releaseYears = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getPoster() {
        if (poster != null)
            return poster.getUrl();
        return "https://error.error";
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public Object getViewOption() {
        return viewOption;
    }

    public void setViewOption(Object viewOption) {
        this.viewOption = viewOption;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReleaseDate(){
        if (productionYear != 0)
            return String.valueOf(productionYear);
        else if (getReleaseYears() != null){
            return ""+ getReleaseYears().get(0).getStart() + (getReleaseYears().get(0).getEnd() !=0 ? " - " + getReleaseYears().get(0).getEnd() : "");
        }else
            return "";
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public List<ReleaseYear> getReleaseYears() {
        return releaseYears;
    }

    public void setReleaseYears(List<ReleaseYear> releaseYears) {
        this.releaseYears = releaseYears;
    }
}
