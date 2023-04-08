package fr.snooker4real.movies.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import fr.snooker4real.movies.model.Review;
import fr.snooker4real.movies.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateReview() {
        // create some test data
        Map<String, String> payload = new HashMap<>();
        payload.put("reviewBody", "This is a great movie!");
        payload.put("imdbId", "1234");

        Review review = new Review(new ObjectId(), payload.get("reviewBody"));

        // mock the reviewService.createReview() method to return the test data
        when(reviewService.createReview(any(String.class), any(String.class))).thenReturn(review);

        // call the controller method
        ResponseEntity<Review> result = reviewController.createReview(payload);

        // verify the result
        assert result.getStatusCode() == HttpStatus.CREATED;
        assert result.getBody().getBody().equals("This is a great movie!");

        log.info("result.getBody().getBody() = " + result.getBody().getBody());
    }
}
