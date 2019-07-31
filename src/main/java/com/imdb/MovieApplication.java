package com.imdb;

import com.imdb.service.MovieTitleBasic;
import com.imdb.service.MovieTitleBasicRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by siddhi on 7/26/2019.
 */

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class MovieApplication {

    @Autowired
    private MovieTitleBasicRepository movieTitleBasicRepository;

    @RequestMapping("/api")
    String home() {
        StringBuilder sb = new StringBuilder();
        sb.append("starting to read from database");

        for (MovieTitleBasic movieTitleBasic : movieTitleBasicRepository.findByTitleTypeAndStartYear("short", "1894")) {
            sb.append(movieTitleBasic.getTconst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}
