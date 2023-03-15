package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ott {
    @SerializedName("promoTrailers")
    @Expose
    private PromoTrailers promoTrailers;

    public PromoTrailers getPromoTrailers() {
        return promoTrailers;
    }

    public void setPromoTrailers(PromoTrailers promoTrailers) {
        this.promoTrailers = promoTrailers;
    }
}
