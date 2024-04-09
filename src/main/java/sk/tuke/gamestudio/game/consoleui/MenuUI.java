package sk.tuke.gamestudio.game.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.service.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
@Component
public class MenuUI {
    private Scanner scanner;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    private String player_name;

    public MenuUI() {
        scanner= new Scanner(System.in);
    }

    public String start_menu(){
        Pattern MENU_PATTERN = Pattern.compile("(play)|(p)|(scores)|(s)|(comments)|(c)|(rating)|(r)");

        System.out.println("\033[H\033[2J");

        System.out.println();

        System.out.println("      __        __     _                                 _            _    _                  ");
        System.out.println("      \\ \\      / /___ | |  ___  ___   _ __ ___    ___   | |_  ___    | |_ | |__    ___        ");
        System.out.println("       \\ \\ /\\ / // _ \\| | / __|/ _ \\ | '_ ` _ \\  / _ \\  | __|/ _ \\   | __|| '_ \\  / _ \\       ");
        System.out.println("        \\ V  V /|  __/| || (__| (_) || | | | | ||  __/  | |_| (_) |  | |_ | | | ||  __/       ");
        System.out.println("         \\_/\\_/  \\___||_| \\___|\\___/ |_| |_| |_| \\___|   \\__|\\___/    \\__||_| |_| \\___|       ");
        System.out.println("  _   ___  ____   _  _       ____                    _          ____                          ");
        System.out.println(" / | / _ \\|___ \\ | || |     |  _ \\  _   _  ____ ____| |  ___   / ___|  __ _  _ __ ___    ___  ");
        System.out.println(" | || | | | __) || || |_    | |_) || | | ||_  /|_  /| | / _ \\ | |  _  / _` || '_ ` _ \\  / _ \\ ");
        System.out.println(" | || |_| |/ __/ |__   _|   |  __/ | |_| | / /  / / | ||  __/ | |_| || (_| || | | | | ||  __/ ");
        System.out.println(" |_| \\___/|_____|   |_|     |_|     \\__,_|/___|/___||_| \\___|  \\____| \\__,_||_| |_| |_| \\___| ");

        System.out.println();
        System.out.println("               Choose: [Play - P | Scores - S | Comments - C | Rating - R]                                      ");


        String menu_choice = scanner.nextLine();
        while (!MENU_PATTERN.matcher(menu_choice.toLowerCase()).matches() ){
            System.out.println("Choose: [Play - P | Score - S | Comments - C | Rating - R]");
            menu_choice = scanner.nextLine();
        }

        System.out.println("\033[H\033[2J");
        player_name="";
        if(menu_choice.charAt(0) == 's') scores_menu();
        else if(menu_choice.charAt(0) == 'c') comments_menu();
        else if(menu_choice.charAt(0) == 'r') rating_menu();
        else{

            System.out.print("Write your name: ");
            player_name = scanner.nextLine();
            if(player_name.isEmpty()) player_name = "player";
            System.out.println("\033[H\033[2J");

            return player_name;
        }
        return player_name;
    }

    private void scores_menu(){
        Pattern SCORES_PATTERN = Pattern.compile("(x)|(b)|(back)|(remove)|(r)");

        System.out.println("  ____                                  ");
        System.out.println(" / ___|   ___  ___   _ __  ___  ___   _ ");
        System.out.println(" \\___ \\  / __|/ _ \\ | '__|/ _ \\/ __| (_) ");
        System.out.println("  ___) || (__| (_) || |  |  __/\\__ \\  _ ");
        System.out.println(" |____/  \\___|\\___/ |_|   \\___||___/ (_) ");
        System.out.println("         [Back-X   RemoveAll-R]\n");

        List<Score> scores = scoreService.getTopScores("game_1024");
        if(scores.isEmpty()) System.out.println("There are no results here yet.");
        else{
            int count =1;
            for (Score score: scores){
                System.out.println(count++ + ")" + score);
            }
        }

        String scores_choice = scanner.nextLine();
        while (!SCORES_PATTERN.matcher(scores_choice.toLowerCase()).matches() ){
            System.out.println("[Back-X   RemoveAll-R]");
            scores_choice = scanner.nextLine();
        }
        if(scores_choice.charAt(0) == 'b' || scores_choice.charAt(0) == 'x') start_menu();
        else if (scores_choice.charAt(0) == 'r' ){
            scoreService.reset();
            System.out.println("\033[H\033[2J");
            scores_menu();
        }
    }

    private void comments_menu(){
        Pattern COMMENTS_PATTERN = Pattern.compile("(x)|(b)|(back)|(write)|(w)|(remove)|(r)");

        System.out.println("   ____                                           _            ");
        System.out.println("  / ___| ___   _ __ ___   _ __ ___    ___  _ __  | |_  ___   _ ");
        System.out.println(" | |    / _ \\ | '_ ` _ \\ | '_ ` _ \\  / _ \\| '_ \\ | __|/ __| (_)");
        System.out.println(" | |___| (_) || | | | | || | | | | ||  __/| | | || |_ \\__ \\  _ ");
        System.out.println("  \\____|\\___/ |_| |_| |_||_| |_| |_| \\___||_| |_| \\__||___/ (_)");
        System.out.println("            [Back-X   Write comment-W  RemoveAll-R]\n");

        List<Comment> comments= commentService.getComments("game_1024");
        if(comments.isEmpty()) System.out.println("There are no comments here yet.");
        else{
            int count =1;
            for (Comment comment: comments){
                System.out.println(count++ + ")" + comment);
            }
        }

        System.out.println();
        String comment_choice = scanner.nextLine();
        while (!COMMENTS_PATTERN.matcher(comment_choice.toLowerCase()).matches() ){
            System.out.println("[Back-X   Write comment-W  RemoveAll-R]");
            comment_choice = scanner.nextLine();
        }
        if(comment_choice.charAt(0) == 'b' || comment_choice.charAt(0) == 'x') start_menu();
        else if (comment_choice.charAt(0) == 'r' ){
            commentService.reset();
            System.out.println("\033[H\033[2J");
            comments_menu();
        }
        else{
            System.out.print("Write your name: ");
            player_name = scanner.nextLine();
            if(player_name.isEmpty()) player_name = "player";

            System.out.print("Write a comment: ");
            String comment_text = scanner.nextLine();
            Comment comment = new Comment("game_1024", player_name, comment_text, new Date());
            commentService.addComment(comment);
            System.out.println("\033[H\033[2J");
            comments_menu();
        }

    }

    private void rating_menu(){
        Pattern RATING_PATTERN = Pattern.compile("(x)|(b)|(back)|(evaluate)|(e)|(remove)|(r)|(find)|(f)");

        System.out.println("  ____         _    _                   ");
        System.out.println(" |  _ \\  __ _ | |_ (_) _ __    __ _   _ ");
        System.out.println(" | |_) |/ _` || __|| || '_ \\  / _` | (_)");
        System.out.println(" |  _ <| (_| || |_ | || | | || (_| |  _ ");
        System.out.println(" |_| \\_\\__,_| \\__||_||_| |_| \\__, | (_)");
        System.out.println("                              |___/      ");
        System.out.println("[Back-X   Evaluate-E  RemoveAll-R  FindByName-F]\n");


        int averageRating= ratingService.getAverageRating("game_1024");
        if(averageRating == 0) System.out.println("No one has rated yet.");
        else{
            System.out.println("Average rating: " + averageRating);
        }

        System.out.println();
        String rating_choice = scanner.nextLine();
        while (!RATING_PATTERN.matcher(rating_choice.toLowerCase()).matches() ){
            System.out.println("[Back-X   Evaluate-E  RemoveAll-R  FindByName-F]\n");

            rating_choice = scanner.nextLine();
        }
        if(rating_choice.charAt(0) == 'b' || rating_choice.charAt(0) == 'x') start_menu();
        else if (rating_choice.charAt(0) == 'r' ){
            ratingService.reset();

            System.out.println("\033[H\033[2J");
            rating_menu();
        }else if(rating_choice.charAt(0)=='f'){
            System.out.print("Write the name: ");
            player_name = scanner.nextLine();
            if(player_name.isEmpty()) player_name = "player";
            int rating = ratingService.getRating("game_1024",player_name);
            System.out.println();
            if(rating == 0 ) System.out.println(player_name + ": not found");
            else System.out.println(player_name + ": " + rating);
            scanner.nextLine();
            System.out.println("\033[H\033[2J");

            rating_menu();

        }
        else{
            System.out.print("Write your name: ");
            player_name = scanner.nextLine();
            if(player_name.isEmpty()) player_name = "player";

            System.out.print("Write a rating from 1 to 5: ");
            String new_rating = scanner.nextLine();
            while (Integer.parseInt(new_rating)  < 1 || Integer.parseInt(new_rating)>5){
                System.out.print("Write a rating from 1 to 5: ");
                new_rating = scanner.nextLine();
            }
            Rating rating = new Rating("game_1024", player_name, Integer.parseInt(new_rating), new Date());
            ratingService.setRating(rating);
            System.out.println("\033[H\033[2J");
            rating_menu();
        }
    }

}
