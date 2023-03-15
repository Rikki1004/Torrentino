package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Suggest {
    @SerializedName("top")
    @Expose
    private Top top;
    @SerializedName("__typename")
    @Expose
    private String typename;

    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
