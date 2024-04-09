package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceJDBCTest {

    private final String game = "test_game";

    @Test
    public void reset() {
        CommentServiceJDBC commentServiceJDBC = new CommentServiceJDBC();

        commentServiceJDBC.reset();
        List<Comment> comments =  commentServiceJDBC.getComments(game);

        assertEquals(0, comments.size());
    }
    @Test
    public void addCommentTest(){
        CommentServiceJDBC commentServiceJDBC = new CommentServiceJDBC();
        commentServiceJDBC.reset();
        Comment testScore = new Comment(game, "super_mega_pro", "You look amazing bro :)", new Date());
        commentServiceJDBC.addComment(testScore);
        List<Comment> scores =  commentServiceJDBC.getComments(game);
        assertEquals(1,scores.size());
        assertTrue(scores.contains(testScore));

    }

    @Test
    public void addSomeComments(){
        CommentServiceJDBC commentServiceJDBC = new CommentServiceJDBC();
        commentServiceJDBC.reset();
        Comment testComment1 = new Comment(game, "first_player", "Nice game!", new Date());
        Comment testComment2 = new Comment(game, "second_player", "How are u?", new Date());
        Comment testComment3 = new Comment(game, "third_player", "I'm tired!", new Date());
        Comment testComment4 = new Comment(game, "fourth_player", "Don't worry everything will be fine! Mb....", new Date());

        commentServiceJDBC.addComment(testComment1);
        commentServiceJDBC.addComment(testComment2);
        commentServiceJDBC.addComment(testComment3);
        commentServiceJDBC.addComment(testComment4);
        List<Comment> scores =  commentServiceJDBC.getComments(game);

        assertEquals(4, scores.size());

        assertTrue(scores.contains(testComment1));
        assertTrue(scores.contains(testComment2));
        assertTrue(scores.contains(testComment3));
        assertTrue(scores.contains(testComment4));


    }






}