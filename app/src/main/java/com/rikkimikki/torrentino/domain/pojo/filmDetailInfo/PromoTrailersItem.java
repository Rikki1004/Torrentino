package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoTrailersItem {
    @SerializedName("streamUrl")
    @Expose
    private String promoTrailers;

    public String getPromoTrailers() {
        return promoTrailers;
    }

    public void setPromoTrailers(String promoTrailers) {
        this.promoTrailers = promoTrailers;
    }
}
