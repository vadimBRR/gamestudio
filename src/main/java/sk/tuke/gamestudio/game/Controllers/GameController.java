package sk.tuke.gamestudio.game.Controllers;

import sk.tuke.gamestudio.game.core.Field;
import sk.tuke.gamestudio.game.core.Tile;
import sk.tuke.gamestudio.game.core.enums.Direction;
import sk.tuke.gamestudio.game.core.enums.GameState;

public class GameController {
    private Field field;
    private MotionController motionController;




    public GameController(Field field, MotionController motionController) {
        this.field = field;
        this.motionController = motionController;
    }

    public boolean isGameFinished() {
        return isPlayerWin() || isPlayerLost();
    }

    public boolean isPlayerWin() {
        Tile[][] pole = field.getPole();

        for (int col = 0; col < pole.length; col++) {
            for (int row = 0; row < pole[0].length; row++) {
                if(pole[col][row].getValue() >= field.getScores_to_win()) {
                    field.setState(GameState.SOLVED);
                    return true;
                }
            }
        }
        return false;
    }



    public boolean isPlayerLost() {

        Tile[][] pole = field.getPole();

        for (int col = 0; col < pole.length; col++) {
            for (int row = 0; row < pole[0].length; row++) {
                if(pole[col][row].getValue() == 0){
                    return false;
                }
            }
        }



        if(motionController.moveOrCheckIsPossible(Direction.UP, false) ||
                motionController.moveOrCheckIsPossible(Direction.DOWN, false)||
                motionController.moveOrCheckIsPossible(Direction.RIGHT, false)||
                motionController.moveOrCheckIsPossible(Direction.LEFT, false)){
            return false;
        }
        field.setState(GameState.FAILED);
        return true;
    }




}
