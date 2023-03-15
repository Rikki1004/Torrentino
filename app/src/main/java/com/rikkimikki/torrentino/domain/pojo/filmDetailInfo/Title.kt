package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Title {
    @SerializedName("russian")
    @Expose
    private String russian;

    @SerializedName("original")
    @Expose
    private String original;

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getOriginal() {
        if (original != null)
            return original;
        return getRussian();
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
