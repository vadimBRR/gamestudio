package sk.tuke.gamestudio.service;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Rating;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RatingServiceJDBCTest {
    private final String game = "test_game";

    @Test
    public void reset() {

        RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();

        ratingServiceJDBC.reset();

        int average_rating = ratingServiceJDBC.getAverageRating(game);
        assertEquals(0, average_rating);
    }
    @Test
    public void setGetRating(){
        RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();
        ratingServiceJDBC.reset();
        Rating testRating = new Rating(game, "super_mega_pro", 5, new Date());
        ratingServiceJDBC.setRating(testRating);
        int rating = ratingServiceJDBC.getRating(game, "super_mega_pro");
        assertEquals(rating, testRating.getRating());

        testRating.setRating(3);
        ratingServiceJDBC.setRating(testRating);
        rating = ratingServiceJDBC.getRating(game, "super_mega_pro");
        assertEquals(rating, testRating.getRating());
    }

    @Test
    public void getAverageRatingTest(){
        RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();
        ratingServiceJDBC.reset();
        Rating testRating1 = new Rating(game, "first_player", 2, new Date());
        Rating testRating2 = new Rating(game, "second_player", 4, new Date());
        Rating testRating3 = new Rating(game, "third_player", 5, new Date());
        Rating testRating4 = new Rating(game, "fourth_player", 1, new Date());

        ratingServiceJDBC.setRating(testRating1);
        ratingServiceJDBC.setRating(testRating2);
        ratingServiceJDBC.setRating(testRating3);
        ratingServiceJDBC.setRating(testRating4);

        int average_rating = ratingServiceJDBC.getAverageRating(game);
        assertEquals(3, average_rating);

    }









}