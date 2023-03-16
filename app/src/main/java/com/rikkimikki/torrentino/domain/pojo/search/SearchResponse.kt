package com.rikkimikki.torrentino.domain.pojo.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchResponce {
    @SerializedName("data")
    @Expose
    private Data data;

    public List<Movie__1> getData() {
        List<Movie__1> oneList = new ArrayList<>();
        if (!data.getSuggest().getTop().getTopResult().getGlobal().getTypename().equals("Person"))
            oneList.add(data.getSuggest().getTop().getTopResult().getGlobal());
        for (Movie i:data.getSuggest().getTop().getMovies())
            oneList.add(i.getMovie());
        //oneList.addAll(data.getSuggest().getTop().getMovies());


        return oneList;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
