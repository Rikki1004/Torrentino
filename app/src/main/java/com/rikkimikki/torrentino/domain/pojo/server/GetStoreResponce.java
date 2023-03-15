package com.rikkimikki.torrentino.domain.pojo.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStoreResponce {
    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("hash")
    @Expose
    private String hash;

    @SerializedName("poster")
    @Expose
    private String poster;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("stat_string")
    @Expose
    private String stat_string;

    @SerializedName("torrent_size")
    @Expose
    private double torrent_size;

    @SerializedName("pending_peers")
    @Expose
    private int pending_peers;

    @SerializedName("active_peers")
    @Expose
    private int active_peers;

    @SerializedName("half_open_peers")
    @Expose
    private int half_open_peers;

    @SerializedName("upload_speed")
    @Expose
    private float upload_speed;

    public void setTorrent_size(double torrent_size) {
        this.torrent_size = torrent_size;
    }

    public int getPending_peers() {
        return pending_peers;
    }

    public void setPending_peers(int total_peers) {
        this.pending_peers = total_peers;
    }

    public int getActive_peers() {
        return active_peers;
    }

    public void setActive_peers(int active_peers) {
        this.active_peers = active_peers;
    }

    public int getHalf_open_peers() {
        return half_open_peers;
    }

    public void setHalf_open_peers(int half_open_peers) {
        this.half_open_peers = half_open_peers;
    }

    public String getUpload_speed() {
        return upload_speed != 0 ? String.format("%.2f", upload_speed/1024) + " Mb/s": "---";
    }

    public void setUpload_speed(float upload_speed) {
        this.upload_speed = upload_speed;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPoster() {
        return !poster.equals("") ? poster : "https://error.error";
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStat_string() {
        return stat_string;
    }

    public void setStat_string(String stat_string) {
        this.stat_string = stat_string;
    }

    public double getTorrent_size() {
        return torrent_size;
    }

    public void setTorrent_size(int torrent_size) {
        this.torrent_size = torrent_size;
    }
    public String getAllPeers(){
        if (getActive_peers() != 0)
            return ""+getActive_peers() +" . "+getHalf_open_peers()+" / " +getPending_peers();
        return "---";
    }
}
