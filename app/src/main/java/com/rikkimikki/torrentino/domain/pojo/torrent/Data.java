package com.rikkimikki.torrentino.domain.pojo.torrent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("film")
    @Expose
    private String film;

    @SerializedName("magnetLink")
    @Expose
    private String magnetLink;

    @SerializedName("peers")
    @Expose
    private String peers;

    @SerializedName("size")
    @Expose
    private String size;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMagnetLink() {
        return magnetLink;
    }

    public void setMagnetLink(String magnetLink) {
        this.magnetLink = magnetLink;
    }

    public String getPeers() {
        return peers;
    }

    public void setPeers(String peers) {
        this.peers = peers;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }
    public String toString(){
        return film+" - "+size + " ("+peers+")";
    }
}
