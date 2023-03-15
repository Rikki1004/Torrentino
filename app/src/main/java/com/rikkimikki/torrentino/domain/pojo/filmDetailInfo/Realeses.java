package com.rikkimikki.torrentino.domain.pojo.filmDetailInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Realeses {
    @SerializedName("releases")
    @Expose
    private List<Release> releases;

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }
}
