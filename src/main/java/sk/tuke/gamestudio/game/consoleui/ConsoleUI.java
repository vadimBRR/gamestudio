package sk.tuke.gamestudio.game.consoleui;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.game.Controllers.AbilitiesController;
import sk.tuke.gamestudio.game.Controllers.GameController;
import sk.tuke.gamestudio.game.Controllers.MotionController;
import sk.tuke.gamestudio.game.core.Field;
import sk.tuke.gamestudio.game.core.enums.Direction;
import sk.tuke.gamestudio.game.core.enums.GameState;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleUI {

    private  Pattern INPUT_PATTERN = Pattern.compile("(w)|(s)|(d)|(a)|(end)|(exit)|(ex)|(e)|(x)");
    private static final Pattern POSITIVE_ANSWER_PATTERN = Pattern.compile("y(y*)e(e*)s(s*)|(\\+)|(y)|g(g*)o(o*)|(yeah)");
    private static final Pattern NEGATIVE_AGAIN_PATTERN = Pattern.compile("n(n*)o(o*)|(-)|(n)|(nope)");
    private Pattern PERK_PETTER;

    private Field field;
    private GameController gameController;
    private MotionController motionController;
    private AbilitiesController abilitiesController;
    private Scanner scanner = new Scanner(System.in);
    private int difficult;
    private String player_name;

    @Autowired
    private MenuUI menuUI;
    @Autowired
    private GameSettingsUI gameSettingsUI;
    @Autowired
    private ScoreService scoreService;



    public ConsoleUI(Field field) {
        this.field = field;
        this.motionController = new MotionController(field);
        this.gameController = new GameController(field, motionController);
        this.abilitiesController = new AbilitiesController(field);

    }

    public void play(){
        player_name= menuUI.start_menu();
        if(gameSettingsUI.chose_mode() == 4) play();

        difficult= gameSettingsUI.chose_difficult(field);
        if(difficult == 5) play();
        field.createField(difficult);

        show();
        while (field.getState().equals(GameState.PLAYING)){
            processInput();
            show();
        }

        System.out.println("\033[H\033[2J");
        show();

        if(field.getState() == GameState.SOLVED) {
            System.out.println("Congratulations!");
        } else if(field.getState() == GameState.FAILED){
            System.out.println("Failed!");
        }

        Score score = new Score("game_1024",player_name, field.getScore(), new Date());
        scoreService.addScore(score);

        restartGame();
    }

    private void processInput() {
        Pattern DIRECTION_PATTER = Pattern.compile("(w)|(s)|(d)|(a)");
        Direction direction = Direction.UP;
        if(field.getIs_use_perks() && !field.getPerks().isEmpty()){
            List<String> perks = field.getPerks();
            System.out.println("Your perks: ");
            for (int perkIndex = 0; perkIndex < perks.size(); perkIndex++) {
                System.out.print((perkIndex +1) + ")" + perks.get(perkIndex));
                if(perkIndex !=perks.size()-1) System.out.print(", ");
            }
            System.out.println();
            System.out.print("Write direction | perk number | exit:");
            INPUT_PATTERN =Pattern.compile("(w)|(s)|(d)|(a)|(end)|(exit)|(ex)|(e)|(x)|[1-" + perks.size() +"]");
            PERK_PETTER = Pattern.compile("[1-" + perks.size() + "]");
        }else System.out.print("Write direction or exit:");


        String command = scanner.nextLine().toLowerCase();
        if(INPUT_PATTERN.matcher(command).matches() ){
            if(DIRECTION_PATTER.matcher(command).matches()){
                if(command.charAt(0) == 'w') direction = Direction.UP;
                else if(command.charAt(0) == 'a') direction = Direction.LEFT;
                else if(command.charAt(0) == 's') direction = Direction.DOWN;
                else direction = Direction.RIGHT;
            }
            if(command.charAt(0) == 'e' || command.charAt(0) == 'x' ) {
                System.out.println();
                System.out.print("Do you really want to stop the game? ");

                command = scanner.nextLine().toLowerCase();
                while (!POSITIVE_ANSWER_PATTERN.matcher(command).matches() && !NEGATIVE_AGAIN_PATTERN.matcher(command).matches()){
                    System.out.println("I don`t understand, try again :)");
                    command = scanner.nextLine().toLowerCase();
                }

                if(POSITIVE_ANSWER_PATTERN.matcher(command).matches()) field.setState(GameState.FAILED);


            }else if(field.getIs_use_perks() && !field.getPerks().isEmpty() && PERK_PETTER.matcher(command).matches()){
                abilitiesController.usePerk( Integer.parseInt(command) - 1);
            }
            else if(DIRECTION_PATTER.matcher(command).matches() && motionController.moveOrCheckIsPossible(direction, true)){
                field.addOneStep();
                field.generate();
                gameController.isGameFinished();
            }
            System.out.println("\033[H\033[2J");

        }else{
            System.out.println("\033[H\033[2J");
            System.out.println("Unknown command. Try again... ");
        }
    }

    private void show(){
        field.printPole();
    }

    private void restartGame(){

        System.out.println("Do you want to play again?");
        var command = scanner.nextLine().toLowerCase();
        if(POSITIVE_ANSWER_PATTERN.matcher(command).matches() && !command.isEmpty()){
            System.out.println("\033[H\033[2J");
            System.out.println("Good choice, let's start again!");
            field.reset();
            play();
        }else if(NEGATIVE_AGAIN_PATTERN.matcher(command).matches()  && !command.isEmpty()){
            System.out.println("Okey, bye... See you later :(");
        }else {
            System.out.println("Sorry, I dont understand, try again :)");
            restartGame();
        }

    }



}
