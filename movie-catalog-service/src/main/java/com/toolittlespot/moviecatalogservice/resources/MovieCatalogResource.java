package com.toolittlespot.moviecatalogservice.resources;

import com.toolittlespot.moviecatalogservice.models.CatalogItem;
import com.toolittlespot.moviecatalogservice.models.UserRating;
import com.toolittlespot.moviecatalogservice.services.MovieInfo;
import com.toolittlespot.moviecatalogservice.services.UserRatingInfo;
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

    @Autowired
    UserRatingInfo userRatingInfo;

    @Autowired
    MovieInfo movieInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        UserRating ratings = userRatingInfo.getUserRating(userId);

        return ratings.getUserRating().stream().map(movieInfo::getCatalogItem).collect(Collectors.toList());
    }
}
