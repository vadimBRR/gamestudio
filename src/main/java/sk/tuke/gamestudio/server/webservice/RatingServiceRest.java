package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;


@RestController
@RequestMapping("/api/rating")
public class RatingServiceRest {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{game}")
    public int getComments(@PathVariable String game) {
        return ratingService.getAverageRating(game);
    }
    @GetMapping("/{game}/{player}")
    public int getComment(@PathVariable String game, @PathVariable String player) {
        return ratingService.getRating(game,player);
    }

    @PostMapping
    public void setRating(@RequestBody Rating rating) {
        ratingService.setRating(rating);
    }

    @DeleteMapping
    public void resetRating(){
        ratingService.reset();
    }
}