package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainTraller {
    @SerializedName("streamUrl")
    @Expose
    private String url;

    @SerializedName("preview")
    @Expose
    private MainTrallerPreview mainTrallerPreview;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MainTrallerPreview getMainTrallerPreview() {
        return mainTrallerPreview;
    }

    public void setMainTrallerPreview(MainTrallerPreview mainTrallerPreview) {
        this.mainTrallerPreview = mainTrallerPreview;
    }
}
