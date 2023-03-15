package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainTrallerPreview {
    @SerializedName("avatarsUrl")
    @Expose
    private String url;

    public String getUrl() {
        return "https:"+url+"/600x380";
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
