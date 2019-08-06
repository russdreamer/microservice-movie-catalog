package com.toolittlespot.ratingdataservice.resources;

import com.toolittlespot.ratingdataservice.models.Rating;
import com.toolittlespot.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResources {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable String userId) {
        var userRating = new UserRating();
        userRating.setUserRating(
                Arrays.asList(
                        new Rating("100", 4),
                        new Rating("200", 4)
                )
        );
        return userRating;
    }
}
