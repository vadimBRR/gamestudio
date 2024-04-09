package sk.tuke.gamestudio.Controllers;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.Controllers.GameController;
import sk.tuke.gamestudio.game.Controllers.MotionController;
import sk.tuke.gamestudio.game.core.Field;
import sk.tuke.gamestudio.game.core.NumeralTile;
import sk.tuke.gamestudio.game.core.Tile;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void isPlayerLoseTest1() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        GameController gameController = new GameController(field, motionController);



        assertTrue(gameController.isPlayerLost());
    }

    @Test
    void isPlayerLoseTest2() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(1), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        GameController gameController = new GameController(field, motionController);



        assertFalse(gameController.isPlayerLost());
    }

    @Test
    void isPlayerWinTest1() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(4), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(64), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(1024), new NumeralTile(16)},
                {new NumeralTile(32), new NumeralTile(128), new NumeralTile(1), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        GameController gameController = new GameController(field, motionController);



        assertTrue(gameController.isPlayerWin());
    }
    @Test
    void isPlayerWinTest2() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(4), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(64), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(512), new NumeralTile(16)},
                {new NumeralTile(32), new NumeralTile(128), new NumeralTile(1), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        GameController gameController = new GameController(field, motionController);


        assertFalse(gameController.isPlayerWin());
    }

    @Test
    void isGameFinishedTest1() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(4), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(64), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(512), new NumeralTile(16)},
                {new NumeralTile(32), new NumeralTile(128), new NumeralTile(1), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        GameController gameController = new GameController(field, motionController);


        assertFalse(gameController.isGameFinished());
    }

    @Test
    void isGameFinishedTest2() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        GameController gameController = new GameController(field, motionController);


        assertTrue(gameController.isGameFinished());
    }






}