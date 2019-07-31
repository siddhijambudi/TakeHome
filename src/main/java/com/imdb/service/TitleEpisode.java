package com.imdb.service;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by siddhi on 7/31/2019.
 */
@Document(collection = "TitleEpisode")
public class TitleEpisode {
    @Id
    public ObjectId _id;
    public String tconst;
    public String parentTconst;
    public Integer seasonNumber;
    public Integer episodeNumber;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getParentTconst() {
        return parentTconst;
    }

    public void setParentTconst(String parentTconst) {
        this.parentTconst = parentTconst;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
}
