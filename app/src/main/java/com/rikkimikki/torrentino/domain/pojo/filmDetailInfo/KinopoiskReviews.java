package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KinopoiskReviews {
    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("value")
    @Expose
    private Float value;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
