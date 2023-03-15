package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoTrailers {
    @SerializedName("items")
    @Expose
    private List<PromoTrailersItem> promoTrailersItems;

    public List<PromoTrailersItem> getPromoTrailersItems() {
        return promoTrailersItems;
    }

    public void setPromoTrailersItems(List<PromoTrailersItem> promoTrailersItems) {
        this.promoTrailersItems = promoTrailersItems;
    }
}
