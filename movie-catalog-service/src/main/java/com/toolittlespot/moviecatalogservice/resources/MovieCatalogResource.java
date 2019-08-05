package com.toolittlespot.moviecatalogservice.resources;

import com.toolittlespot.moviecatalogservice.models.CatalogItem;
import com.toolittlespot.moviecatalogservice.models.Movie;
import com.toolittlespot.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){
        UserRating ratings = restTemplate.getForObject(
                "http://rating-data-service/ratingsdata/users/" + userId,
                UserRating.class);

        return ratings.getUserRating().stream().map(rating ->  {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getMovieName(), "about robots", rating.getMovieRating());
        }).collect(Collectors.toList());
    }
}
