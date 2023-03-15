package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilmInfoResponce {
    @SerializedName("data")
    @Expose
    private Data data;

    public String getTitleRU() {
        return data.getFilm().getTitle().getRussian();
    }
    public String getTitleORIG() {
        return data.getFilm().getTitle().getOriginal();
    }
    public int getRatingCount() {
        return data.getFilm().getRating().getKinopoiskReviews().getCount();
    }
    public Float getRatingValue() {
        return data.getFilm().getRating().getKinopoiskReviews().getValue();
    }
    public String getOverview() {
        return data.getFilm().getOverview();
    }
    public String getPoster() {
        return data.getFilm().getPoster().getUrl();
    }
    public String getTraller() {
        if (data.getFilm().getOtt().getPromoTrailers().getPromoTrailersItems().size() > 0)
            return data.getFilm().getOtt().getPromoTrailers().getPromoTrailersItems().get(0).getPromoTrailers();
        return null;
    }
    public int getDate() {
        return data.getFilm().getDateRelease();
    }

    public String getMainTraller() {
        return data.getFilm().getMainTraller().getUrl();
    }

    public Boolean hasTraller(){
        if (data.getFilm().getMainTraller() == null)
            return false;
        return true;
    }
    public String getMainTrallerPreview() {
        return data.getFilm().getMainTraller().getMainTrallerPreview().getUrl();
    }

    public void setData(Data data) {
        this.data = data;
    }
}
