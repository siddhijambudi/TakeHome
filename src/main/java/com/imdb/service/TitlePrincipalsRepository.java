package com.imdb.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by siddhi on 7/31/2019.
 */
@Repository
public interface TitlePrincipalsRepository extends PagingAndSortingRepository<TitlePrincipals, String> {
    List<TitlePrincipals> findByTconst(String tconst);
}
