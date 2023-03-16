package com.rikkimikki.torrentino.domain.pojo.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPositionResponce {
    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("position")
    @Expose
    private String position;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("title")
    @Expose
    private String title;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
