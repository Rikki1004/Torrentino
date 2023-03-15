package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("suggest")
    @Expose
    private Suggest suggest;

    public Suggest getSuggest() {
        return suggest;
    }

    public void setSuggest(Suggest suggest) {
        this.suggest = suggest;
    }
}
