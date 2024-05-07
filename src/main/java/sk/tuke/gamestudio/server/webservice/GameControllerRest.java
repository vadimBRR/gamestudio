package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.game.Controllers.AbilitiesController;
import sk.tuke.gamestudio.game.Controllers.GameController;
import sk.tuke.gamestudio.game.Controllers.MotionController;
import sk.tuke.gamestudio.game.core.Field;
import sk.tuke.gamestudio.game.core.GameField;
import sk.tuke.gamestudio.game.core.enums.Direction;

@RestController
@RequestMapping("/api/game")
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GameControllerRest {

    private final GameController gameController;
    private final MotionController motionController;
    private final AbilitiesController abilitiesController;

    private final Field field;

    private boolean isSended;
    @Autowired
    public GameControllerRest() {
        isSended = false;
        this.field = new Field(4);
        this.motionController = new MotionController(field);
        this.gameController = new GameController(field, motionController);
        this.abilitiesController = new AbilitiesController(field);
    }

    @GetMapping("/move")
    public GameField moveTile(@RequestParam Direction direction) {

        boolean isChanged = false;

        if(!gameController.isPlayerWin() && motionController.moveOrCheckIsPossible(direction, true)){
            field.addOneStep();
            field.generate();
            isChanged = true;
        }
        return getGameField(isChanged);
    }



    @GetMapping("/new")
    public GameField newGame(@RequestParam Integer field_size, @RequestParam Integer mode, @RequestParam String tile, @RequestParam boolean is_abilities) {
        isSended = false;
        field.reset();
        field.setIs_use_perks(is_abilities);
        if(mode ==2){
            if(field_size==1) field.setCount_obstacle(3);
            if(field_size==2) field.setCount_obstacle(2);
            if(field_size==3) field.setCount_obstacle(1);
        }
        field.createField(field_size);
        field.setScores_to_win(Integer.parseInt(tile));
        return new GameField(field, true, false, false,true, field.getPerks());

    }

    @GetMapping("/status")
    public String getGameStatus() {
        if (gameController.isPlayerWin()) {
            return "Game Won!";
        } else {
            return "Game Over!";
        }
    }

    @GetMapping("/field")
    public GameField getGameField() {
        isSended = true;
        boolean isChanged = true;
        return getGameField(isChanged);
    }
    @GetMapping("/abilities")
    public GameField useAbilities(@RequestParam Integer number,@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer col ) {
        boolean isChanged = false;

        if(number == 1 || number == 3 ){
            abilitiesController.usePerkWeb(number, -1, -1);
        }else{
            abilitiesController.usePerkWeb(number, row, col);
        }

        return getGameField(isChanged);
    }

    private GameField getGameField(boolean isChanged) {
        boolean isPlayerWon = false;
        boolean isGameFinished = gameController.isGameFinished();
        if(isGameFinished) {
            isPlayerWon = gameController.isPlayerWin();
            if(isSended){
                return new GameField(field, isChanged, isGameFinished,isPlayerWon, false, field.getPerks());
            }else{
                isSended = true;
                return new GameField(field, isChanged, isGameFinished,isPlayerWon, true, field.getPerks());
            }
        }else{
            return new GameField(field, isChanged, isGameFinished,isPlayerWon, true, field.getPerks());
        }
    }


}
