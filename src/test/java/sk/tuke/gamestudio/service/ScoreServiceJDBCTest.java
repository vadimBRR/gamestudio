package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Score;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreServiceJDBCTest {
    private final String game = "test_game";

    @Test
    public void reset() {
        ScoreServiceJDBC scoreServiceJDBC = new ScoreServiceJDBC();

        scoreServiceJDBC.reset();
        List<Score> scores =  scoreServiceJDBC.getTopScores(game);

        assertEquals(0, scores.size());
    }
    @Test
    public void addScoreTest(){
        ScoreServiceJDBC scoreServiceJDBC = new ScoreServiceJDBC();
        scoreServiceJDBC.reset();
        Score testScore = new Score(game, "super_mega_pro", 500, new Date());
        scoreServiceJDBC.addScore(testScore);
        List<Score> scores =  scoreServiceJDBC.getTopScores(game);
        assertTrue(scores.contains(testScore));

    }

    @Test
    public void getTopScoresTest(){
        ScoreServiceJDBC scoreServiceJDBC = new ScoreServiceJDBC();
        scoreServiceJDBC.reset();
        Score testScore1 = new Score(game, "first_player", 420, new Date());
        Score testScore2 = new Score(game, "second_player", 658, new Date());
        Score testScore3 = new Score(game, "third_player", 589, new Date());
        Score testScore4 = new Score(game, "fourth_player", 300, new Date());

        scoreServiceJDBC.addScore(testScore1);
        scoreServiceJDBC.addScore(testScore2);
        scoreServiceJDBC.addScore(testScore3);
        scoreServiceJDBC.addScore(testScore4);
        List<Score> scores =  scoreServiceJDBC.getTopScores(game);

        assertEquals(4, scores.size());
        System.out.println(scores);
        assertTrue(scores.get(0).equals(testScore2));
        assertTrue(scores.get(1).equals(testScore3));
        assertTrue(scores.get(2).equals(testScore1));
        assertTrue(scores.get(3).equals(testScore4));


    }






}