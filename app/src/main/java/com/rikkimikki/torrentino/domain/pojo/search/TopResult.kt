package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopResult {
    @SerializedName("global")
    @Expose
    private Movie__1 global;
    @SerializedName("__typename")
    @Expose
    private String typename;

    public Movie__1 getGlobal() {
        return global;
    }

    public void setGlobal(Movie__1 global) {
        this.global = global;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
