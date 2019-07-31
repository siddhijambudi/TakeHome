package com.imdb;

import com.imdb.service.MovieTitleBasic;
import com.imdb.service.MovieTitleBasicRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by siddhi on 7/26/2019.
 */

@SpringBootApplication
@EnableAutoConfiguration
@RestController
@RequestMapping("/movies")
public class MovieApplication {

    @Autowired
    private MovieTitleBasicRepository movieTitleBasicRepository;

    //http://localhost:8080/movies/2017/movie
    @GetMapping("/{startYear}/{titleType}")
    public List<MovieTitleBasic> getMovieData(@PathVariable("startYear") Integer startYear,
                                              @PathVariable("titleType") String titleType) {
        List<MovieTitleBasic> movieData = movieTitleBasicRepository.findByStartYearAndTitleType(startYear,titleType);

        return movieData;
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}
