package com.rikkimikki.torrentino.domain.pojo.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddTorrentResponse {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("hash")
    @Expose
    private String hash;

    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("file_stats")
    @Expose
    private List<FileStats> file_stats= null;

    public List<FileStats> getFile_stats() {
        return file_stats;
    }

    public void setFile_stats(List<FileStats> file_stats) {
        this.file_stats = file_stats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
