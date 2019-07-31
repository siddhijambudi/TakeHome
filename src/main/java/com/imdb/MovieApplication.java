package com.imdb;

import com.imdb.service.*;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private TitlePrincipalsRepository titlePrincipalsRepository;

    @Autowired
    private TitleRatingsRepository titleRatingsRepository;

    @GetMapping("/{startYear}/{titleType}")
    public String getMovieData(@PathVariable("startYear") Integer startYear,
                               @PathVariable("titleType") String titleType) {

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase mydb = mongoClient.getDatabase("mydb");
        StringBuilder sb = new StringBuilder();

        MongoCollection<Document> movieTitleBasicDocument = mydb.getCollection("MovieTitleBasic");
        MongoCollection<Document> titlePrincipalsDocument = mydb.getCollection("TitlePrincipals");

        FindIterable<Document> movies = movieTitleBasicDocument.find(
                Filters.and(new BasicDBObject("startYear", startYear),
                            new BasicDBObject("titleType", titleType)));

        Set<String> tConsts = new HashSet<>();
        movies.forEach((Block<Document>) document -> {
            String tconst = document.getString("tconst");
            tConsts.add(tconst);

            System.out.println(tconst);
            sb.append(document.getInteger("startYear")).append("::");
        });
        FindIterable<Document> titles = titlePrincipalsDocument.find(Filters.in("tconst", tConsts));
        titles.forEach((Block<Document>) document -> {
            String nconst = document.getString("nconst");

            System.out.println(nconst);
            sb.append(nconst);
        });
        /*movieData.forEach((Block<Document>) document -> {
            String tconst = document.getString("tconst");
            sb.append(tconst);
            FindIterable<Document> titles = titlePrincipalsDocument.find(Filters.eq("tconst", tconst));
            for(Document docTitle : titles){
                sb.append(docTitle.get("nconst"));
            }
        });*/

        /*Set<String> tConsts = new HashSet<>();
        for(Document document : movieData){
            tConsts.add(document.getString("tconst"));
        }
        MongoCollection<Document> titlePrincipalsDocument = mydb.getCollection("TitlePrincipals");
        for(String tConst : tConsts){
            FindIterable<Document> titlePrincipal = titlePrincipalsDocument.find(new BasicDBObject("tconst", tConst));
            sb.append(tConst);
            for(Document document : titlePrincipal){
                sb.append(document.getString("nconst"));
            }
        }*/
        //List<MovieTitleBasic> movieData = movieTitleBasicRepository.findByStartYearAndTitleType(startYear,titleType);
        //Map<String,MovieTitleBasic> movieTitleBasicMap = generateMovieTitleMap(movieData);
        //Map<String,List<TitlePrincipals>> principalsMap = generatePrincipalsMap(movieTitleBasicMap.keySet());

        /*List<MovieResponseModel> movieResponseModelList = new ArrayList<>();
        for(Map.Entry<String,List<TitlePrincipals>> entry : principalsMap.entrySet()){
            MovieResponseModel movieResponseModel = new MovieResponseModel();

            MovieTitleBasic movieTitleBasic = movieTitleBasicMap.get(entry.getKey());
            List<TitlePrincipals> titlePrincipalsList = entry.getValue();

            movieResponseModel.setTitleType(movieTitleBasic.getTitleType());
            for(TitlePrincipals titlePrincipals : titlePrincipalsList){
                movieResponseModel.setnCost(titlePrincipals.getNconst());
            }
        }*/
        return sb.toString();
    }
    /*private Map<String,MovieTitleBasic> generateMovieTitleMap(List<MovieTitleBasic> movieData){
        Map<String,MovieTitleBasic> mapOfTitleBasic = new HashMap<>();
        for(MovieTitleBasic movieTitleBasic : movieData){
            mapOfTitleBasic.put(movieTitleBasic.getTconst(), movieTitleBasic);
        }
        return mapOfTitleBasic;
    }
    private Map<String,List<TitlePrincipals>> generatePrincipalsMap(Set<String> tConsts){
        Map<String,List<TitlePrincipals>> result = new HashMap<>();
        for(String tConst : tConsts){
            List<TitlePrincipals> titlePrincipals = titlePrincipalsRepository.findByTconst(tConst);
            result.put(tConst, titlePrincipals);
        }
        return result;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}
