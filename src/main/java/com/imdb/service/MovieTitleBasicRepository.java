package com.imdb.service;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by siddhi on 7/26/2019.
 */

@Repository
public interface MovieTitleBasicRepository extends PagingAndSortingRepository<MovieTitleBasic, String> {
    List<MovieTitleBasic> findByStartYearAndTitleType(Integer startYear, String titleType);
}
