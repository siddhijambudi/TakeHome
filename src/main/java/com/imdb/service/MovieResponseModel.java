package com.imdb.service;

/**
 * Created by siddhi on 7/31/2019.
 */
public class MovieResponseModel {
    private String titleType;
    private String rating;
    private String nCost;

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getnCost() {
        return nCost;
    }

    public void setnCost(String nCost) {
        this.nCost = nCost;
    }
}
