package com.imdb.service;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by siddhi on 7/31/2019.
 */
public interface TitleRatingsRepository extends PagingAndSortingRepository<TitleRatings, String> {
    List<TitleRatings> findByTconst(String tconst);
}
