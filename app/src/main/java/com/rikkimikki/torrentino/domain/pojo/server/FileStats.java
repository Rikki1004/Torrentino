package com.rikkimikki.torrentino.domain.pojo.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileStats {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("length")
    @Expose
    private String length;

    @SerializedName("path")
    @Expose
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String hash) {
        this.path = hash;
    }
}

