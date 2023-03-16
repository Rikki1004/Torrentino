package com.rikkimikki.torrentino.domain.pojo.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VolumeResponce {
    @SerializedName("volume")
    @Expose
    private int volume;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
