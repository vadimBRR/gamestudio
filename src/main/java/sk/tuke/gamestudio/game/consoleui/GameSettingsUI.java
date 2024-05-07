package sk.tuke.gamestudio.game.consoleui;

import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.game.core.Field;

import java.util.Scanner;
import java.util.regex.Pattern;

@Component
public class GameSettingsUI {
    private Scanner scanner ;
    private int mode;

    private static final Pattern POSITIVE_ANSWER_PATTERN = Pattern.compile("y(y*)e(e*)s(s*)|(\\+)|(y)|g(g*)o(o*)|(yeah)");
    private static final Pattern NEGATIVE_AGAIN_PATTERN = Pattern.compile("n(n*)o(o*)|(-)|(n)|(nope)");
    public GameSettingsUI() {
        this.scanner = new Scanner(System.in);
    }

    public int chose_mode() {
        Pattern CHOOSE_MODE_PATTERN = Pattern.compile("[1-4]");

        System.out.println("   ____   _                                                              _            ");
        System.out.println("  / ___| | |__     ___     ___    ___    ___     _ __ ___     ___     __| |   ___   _ ");
        System.out.println(" | |     | '_ \\   / _ \\   / _ \\  / __|  / _ \\   | '_ ` _ \\   / _ \\   / _` |  / _ \\ (_)");
        System.out.println(" | |___  | | | | | (_) | | (_) | \\__ \\ |  __/   | | | | | | | (_) | | (_| | |  __/  _ ");
        System.out.println("  \\____| |_| |_|  \\___/   \\___/  |___/  \\___|   |_| |_| |_|  \\___/   \\__,_|  \\___| (_)");
        System.out.println();
        System.out.println("               [1 - Default Mode  2 - Obstacle mode   3 - Tutorial   4-Menu]                                      ");

        String temp_mode = scanner.nextLine();
        while (!CHOOSE_MODE_PATTERN.matcher(temp_mode).matches() ){
            System.out.println("Write 1, 2, 3 or 4!");
            temp_mode = scanner.nextLine();
        }

        mode = Integer.parseInt(temp_mode);
        if(mode == 3) tutorial_mode();

        System.out.println("\033[H\033[2J");
        return mode;
    }

    public int chose_difficult(Field field) {
        Pattern SCORES_PATTER = Pattern.compile("16|32|64|128|256|512|1024");
        Pattern LENGTH_PATTER = Pattern.compile("[2-9]");
        Pattern DIFFICULT_PATTERN = Pattern.compile("[1-5]");

        System.out.println("   ____  _                                     _  _   __   __  _               _  _      ");
        System.out.println("  / ___|| |__    ___    ___   ___   ___     __| |(_) / _| / _|(_)  ___  _   _ | || |_  _ ");
        System.out.println(" | |    | '_ \\  / _ \\  / _ \\ / __| / _ \\   / _` || || |_ | |_ | | / __|| | | || || __|(_)");
        System.out.println(" | |___ | | | || (_) || (_) |\\__ \\|  __/  | (_| || ||  _||  _|| || (__ | |_| || || |_  _ ");
        System.out.println("  \\____||_| |_| \\___/  \\___/ |___/ \\___|   \\__,_||_||_|  |_|  |_| \\___| \\__,_||_| \\__|(_)");
        System.out.println();
        System.out.println("            [1-Easy(5*5)   2-Normal(4*4)   3-Hard(3*3)   4-Custom   5-Menu]                                      ");

        String temp_difficult = scanner.nextLine();
        while (!DIFFICULT_PATTERN.matcher(temp_difficult).matches() ){
            System.out.println("Write number from 1 - 5!");
            temp_difficult = scanner.nextLine();

        }
        int difficult = Integer.parseInt(temp_difficult);
        if(difficult==5) return difficult;
        if(mode == 2){
            if(difficult==1) field.setCount_obstacle(3);
            if(difficult==2) field.setCount_obstacle(2);
            if(difficult==3) field.setCount_obstacle(1);
        }


        if(difficult==4){
            System.out.println();
            System.out.println("How many columns should there be (min - 2 | max - 9):");
            String temp_cols = scanner.nextLine();
            while ( !LENGTH_PATTER.matcher(temp_cols).matches()){
                System.out.println("The length must be in the range 2 to 9");
                temp_cols = scanner.nextLine();
            }
            field.setColCount(Integer.parseInt(temp_cols));

            System.out.println();
            System.out.println("How many rows should there be (min - 2 | max - 9):");
            String temp_rows = scanner.nextLine();
            while ( !LENGTH_PATTER.matcher(temp_rows).matches()){
                System.out.println("The length must be in the range 2 to 9");
                temp_rows = scanner.nextLine();
            }
            field.setRowCount(Integer.parseInt(temp_rows));

        }

        if(mode == 2){
            String answer="";
            if(difficult!=4){
                System.out.println();
                System.out.print("Do you want to change the number of obstacles (now: " + field.getCount_obstacle() + ")?");

                answer = scanner.nextLine().toLowerCase();
                while (!POSITIVE_ANSWER_PATTERN.matcher(answer).matches() && !NEGATIVE_AGAIN_PATTERN.matcher(answer).matches()){
                    System.out.println("I don`t understand, try again :)");
                    answer = scanner.nextLine().toLowerCase();
                }
            }

            if(POSITIVE_ANSWER_PATTERN.matcher(answer).matches() || difficult == 4){
                System.out.println();
                int max_obstacle;
                if(difficult == 4) max_obstacle = field.getRowCount() * field.getColCount() - 6;
                else max_obstacle = difficult * difficult - 6;
                if(max_obstacle>=0) {
                    Pattern OBSTACLE_COUNT_PATTER ;
                    if(max_obstacle>=10) {
                        max_obstacle = 10;
                        OBSTACLE_COUNT_PATTER=Pattern.compile("[0-9]");
                    }else{
                        OBSTACLE_COUNT_PATTER=Pattern.compile("[0-"+max_obstacle+"]");
                    }
                    System.out.println("How many obstacle should there be  (min - 0 | max - " + max_obstacle + "):");
                    String temp_obstacle_count = scanner.nextLine();
                    while (!OBSTACLE_COUNT_PATTER.matcher(temp_obstacle_count).matches()) {
                        System.out.println("The length must be in the range 0 to " + max_obstacle);
                        System.out.println("How many obstacle should there be (min - 0 | max - " + max_obstacle + "):");
                        temp_obstacle_count = scanner.nextLine();
                    }
                    field.setCount_obstacle(Integer.parseInt(temp_obstacle_count));
                }
            }
        }

        System.out.println();
        System.out.print("Would you like to change the tile required for victory (currently: 1024)? ");

        String answer = scanner.nextLine().toLowerCase();
        while (!POSITIVE_ANSWER_PATTERN.matcher(answer).matches() && !NEGATIVE_AGAIN_PATTERN.matcher(answer).matches()){
            System.out.println("I don`t understand, try again :)");
            answer = scanner.nextLine().toLowerCase();
        }

        if(POSITIVE_ANSWER_PATTERN.matcher(answer).matches()){
            System.out.print("OK, write how many scores: ");
            answer = scanner.nextLine();
            while (!SCORES_PATTER.matcher(answer).matches()){
                System.out.println("Choose from this list(16|32|64|128|256|512|1024) ");
                answer = scanner.nextLine();
            }
            field.setScores_to_win(Integer.parseInt(answer));

        }

        System.out.println();
        System.out.print("Want to use perks? ");

        answer = scanner.nextLine().toLowerCase();
        while (!POSITIVE_ANSWER_PATTERN.matcher(answer).matches() && !NEGATIVE_AGAIN_PATTERN.matcher(answer).matches()){
            System.out.println("I don`t understand, try again :)");
            answer = scanner.nextLine().toLowerCase();
        }

        field.setIs_use_perks(POSITIVE_ANSWER_PATTERN.matcher(answer).matches());
        System.out.println("\033[H\033[2J");



        return difficult;
    }

    public void tutorial_mode(){


        String boldText = "\u001B[1m";
        String resetStyle = "\u001B[0m";

        String blueColor = "\u001B[34m";
        String greenColor = "\u001B[32m";

        String gameInstructions = boldText + blueColor + "How to Play 1024 Console Game" + resetStyle + "\n\n" +
                "The 1024 game is an engaging and challenging experience. To play, follow the rules and use commands to move tiles in the chosen direction.\n\n" +
                boldText + greenColor + "Game Rules:" + resetStyle + "\n" +
                "1. Combine tiles of the same value to create larger tiles.\n" +
                "2. The goal is to achieve a tile with a value of 1024.\n\n" +
                boldText + greenColor + "Movement Commands:" + resetStyle + "\n" +
                "- `up` or `u`: Move tiles upwards.\n" +
                "- `down` or `d`: Move tiles downwards.\n" +
                "- `right` or `r`: Move tiles to the right.\n" +
                "- `left` or `l`: Move tiles to the left.\n\n" +
                boldText + greenColor + "Game Modes:" + resetStyle + "\n" +
                "1. Default Mode: No obstacles, basic game mode.\n" +
                "2. Obstacle Mode: With obstacles, the player must navigate around hindrances.\n\n" +
                boldText + greenColor + "Game Difficulties:" + resetStyle + "\n" +
                "Players can choose one of four difficulties:\n" +
                "- `easy` (5x5)\n" +
                "- `normal` (4x4)\n" +
                "- `hard` (3x3)\n" +
                "- `custom` - allows specifying custom grid dimensions.\n\n" +
                boldText + greenColor + "Obstacles (Only in Obstacle Mode):" + resetStyle + "\n" +
                "Players can choose the number and placement of obstacles.\n\n" +
                boldText + greenColor + "Power-Ups:" + resetStyle + "\n" +
                "Players can utilize power-ups, which are granted after a specific number of moves:\n" +
                "1. Sort Field: Sorts all tiles on the field.\n" +
                "2. Remove the Tile: Removes a tile at the specified column and row.\n" +
                "3. Double the Value of the Tile: Doubles the value of the specified tile.\n" +
                "4. Replace Smaller Values with 8: Replaces values of tiles less than 8 with 8.\n\n" +
                boldText + greenColor + "Power-Up Distribution:" + resetStyle + "\n" +
                "- 1st power-up: after 10 moves.\n" +
                "- 2nd power-up: after 20 moves.\n" +
                "- 3rd power-up: after 30 moves.\n" +
                "- 4th power-up: after 40 moves.\n\n" +
                "Play and strive to reach the tile with a value of 1024!";

        System.out.println(gameInstructions);
        scanner.nextLine();
        System.out.println("\033[H\033[2J");

        chose_mode();

    }
}

