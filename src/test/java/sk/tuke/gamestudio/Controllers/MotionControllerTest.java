package sk.tuke.gamestudio.Controllers;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.Controllers.MotionController;
import sk.tuke.gamestudio.game.core.*;
import sk.tuke.gamestudio.game.core.enums.Direction;

import static org.junit.jupiter.api.Assertions.*;

class MotionControllerTest {

    @Test
    void isPossibleToMoveUpTest1(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        assertTrue(motionController.moveOrCheckIsPossible(Direction.UP, false));
    }
    @Test
    void isPossibleToMoveUpTest2(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(1), new NumeralTile(1), new NumeralTile(1), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        assertFalse(motionController.moveOrCheckIsPossible(Direction.UP, false));
    }

    @Test
    void isPossibleToMoveDownTest1(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        assertTrue(motionController.moveOrCheckIsPossible(Direction.DOWN, false));
    }
    @Test
    void isPossibleToMoveDownTest2(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        assertFalse(motionController.moveOrCheckIsPossible(Direction.DOWN, false));
    }

    @Test
    void isPossibleToMoveRightTest1(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(2), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(2)},
                {new NumeralTile(3), new NumeralTile(2), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        assertTrue(motionController.moveOrCheckIsPossible(Direction.RIGHT, false));
    }

    @Test
    void isPossibleToMoveRightTest2(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        assertFalse(motionController.moveOrCheckIsPossible(Direction.RIGHT, false));
    }


    @Test
    void isPossibleToMoveLeftTest1(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new EmptyTile(), new NumeralTile(1), new NumeralTile(2), new EmptyTile()},
                {new NumeralTile(3), new NumeralTile(2), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(2)},
                {new NumeralTile(3), new NumeralTile(2), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        assertTrue(motionController.moveOrCheckIsPossible(Direction.LEFT, false));
    }

    @Test
    void isPossibleToMoveLeftTest2(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        assertFalse(motionController.moveOrCheckIsPossible(Direction.LEFT, false));
    }

    @Test
    void checkingScoreTest1(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        motionController.moveOrCheckIsPossible(Direction.LEFT, true);
        assertEquals (0,field.getScore());
    }

    @Test
    void checkingScoreTest2(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(2), new NumeralTile(4), new NumeralTile(2), new NumeralTile(2)},
        };
        field.setPole(pole);
        MotionController motionController = new MotionController(field);
        motionController.moveOrCheckIsPossible(Direction.LEFT, true);
        assertEquals (4,field.getScore());
    }



    @Test
    void isCorrectMoveUpTest() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(2), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(3)},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(4), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(2), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(3), new NumeralTile(6)},
                {new NumeralTile(3), new NumeralTile(1), new EmptyTile(), new EmptyTile()},

        };

        MotionController motionController = new MotionController(field);
        motionController.moveOrCheckIsPossible(Direction.UP, true);

        assertArrayEquals(pole2, field.getPole());


    }

    @Test
    void isCorrectMoveDownTest() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(3), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(4), new NumeralTile(1)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(2), new NumeralTile(3)},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new NumeralTile(2), new NumeralTile(3), new EmptyTile(), new EmptyTile()},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(3)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(4), new NumeralTile(1)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(4), new NumeralTile(6)},

        };

        MotionController motionController = new MotionController(field);
        motionController.moveOrCheckIsPossible(Direction.DOWN, true);

        assertArrayEquals(pole2, field.getPole());




    }@Test
    void isCorrectMoveLeftTest() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(3), new NumeralTile(6)},
                {new NumeralTile(2), new NumeralTile(2), new EmptyTile(), new NumeralTile(4)},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(2), new NumeralTile(2), new NumeralTile(3)},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new NumeralTile(2), new NumeralTile(6), new NumeralTile(6), new EmptyTile()},
                {new NumeralTile(4), new NumeralTile(4), new EmptyTile(), new EmptyTile()},
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(4), new NumeralTile(3), new EmptyTile()},

        };

        MotionController motionController = new MotionController(field);
        motionController.moveOrCheckIsPossible(Direction.LEFT, true);

        assertArrayEquals(pole2, field.getPole());


    }

    @Test
    void isCorrectMoveRightTest() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(3), new NumeralTile(6)},
                {new NumeralTile(2), new NumeralTile(2), new EmptyTile(), new NumeralTile(4)},
                {new NumeralTile(2), new EmptyTile(), new EmptyTile(), new EmptyTile()},
                {new NumeralTile(3), new NumeralTile(2), new NumeralTile(2), new NumeralTile(3)},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new EmptyTile(), new NumeralTile(2), new NumeralTile(6), new NumeralTile(6)},
                {new EmptyTile(), new EmptyTile(), new NumeralTile(4), new NumeralTile(4)},
                {new EmptyTile(), new EmptyTile(), new EmptyTile(), new NumeralTile(2)},
                {new EmptyTile(), new NumeralTile(3), new NumeralTile(4), new NumeralTile(3)},

        };

        MotionController motionController = new MotionController(field);
        motionController.moveOrCheckIsPossible(Direction.RIGHT, true);

        assertArrayEquals(pole2, field.getPole());


    }



    //Obstacle

    @Test
    void isCorrectMoveUpWithObstacleTest() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new EmptyTile(), new NumeralTile(2), new EmptyTile()},
                {new ObstacleTile(),new EmptyTile(), new EmptyTile(), new NumeralTile(1)},
                {new EmptyTile(), new NumeralTile(3), new ObstacleTile(), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(3)},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new NumeralTile(2), new NumeralTile(3), new NumeralTile(2), new NumeralTile(1)},
                {new ObstacleTile(),new NumeralTile(1), new EmptyTile(), new NumeralTile(6)},
                {new  NumeralTile(3), new EmptyTile(), new ObstacleTile(), new EmptyTile()},
                {new EmptyTile(), new EmptyTile(), new NumeralTile(3), new EmptyTile()},
        };

        MotionController motionController = new MotionController(field);
        motionController.moveOrCheckIsPossible(Direction.UP, true);

        assertArrayEquals(pole2, field.getPole());


    }

    @Test
    void isCorrectMoveRightWithObstacleTest() {
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(2), new EmptyTile(), new NumeralTile(2), new EmptyTile()},
                {new ObstacleTile(),new EmptyTile(), new EmptyTile(), new NumeralTile(1)},
                {new EmptyTile(), new NumeralTile(3), new ObstacleTile(), new NumeralTile(3)},
                {new NumeralTile(3), new NumeralTile(1), new NumeralTile(3), new NumeralTile(3)},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new EmptyTile(), new EmptyTile(), new EmptyTile(), new NumeralTile(4)},
                {new ObstacleTile(),new EmptyTile(), new EmptyTile(), new NumeralTile(1)},
                {new EmptyTile(), new NumeralTile(3), new ObstacleTile(), new NumeralTile(3)},
                {new EmptyTile(), new NumeralTile(3), new NumeralTile(1), new NumeralTile(6)},
        };

        MotionController motionController = new MotionController(field);
        motionController.moveOrCheckIsPossible(Direction.RIGHT, true);

        assertArrayEquals(pole2, field.getPole());


    }




}