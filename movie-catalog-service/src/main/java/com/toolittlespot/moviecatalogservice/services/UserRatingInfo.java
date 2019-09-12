package com.toolittlespot.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.toolittlespot.moviecatalogservice.models.Rating;
import com.toolittlespot.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject(
                "http://rating-data-service/ratingsdata/users/" + userId,
                UserRating.class);
    }

    public UserRating getFallbackUserRating(String userId) {
        UserRating rating = new UserRating();
        rating.setId(userId);
        rating.setUserRating(Arrays.asList(
                new Rating("0", 0)
        ));
        return rating;
    }
}
