package sk.tuke.gamestudio.Controllers;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.Controllers.AbilitiesController;
import sk.tuke.gamestudio.game.core.EmptyTile;
import sk.tuke.gamestudio.game.core.Field;
import sk.tuke.gamestudio.game.core.NumeralTile;
import sk.tuke.gamestudio.game.core.Tile;

import static org.junit.jupiter.api.Assertions.*;

class AbilitiesControllerTest {
    @Test
    void isCorrectSortField(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(16), new NumeralTile(64), new EmptyTile(), new EmptyTile()},
                {new NumeralTile(2), new EmptyTile(), new NumeralTile(4), new NumeralTile(1)},
                {new NumeralTile(4), new NumeralTile(32), new EmptyTile(), new NumeralTile(64)},
                {new EmptyTile(), new NumeralTile(1), new NumeralTile(2), new EmptyTile()},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new NumeralTile(64), new NumeralTile(64), new NumeralTile(32), new NumeralTile(16)},
                {new NumeralTile(4), new NumeralTile(4), new NumeralTile(2), new NumeralTile(2)},
                {new NumeralTile(1), new NumeralTile(1), new EmptyTile(), new EmptyTile()},
                {new EmptyTile(), new EmptyTile(), new EmptyTile(), new EmptyTile()},

        };

        AbilitiesController abilitiesController = new AbilitiesController(field);
        abilitiesController.sortField();
        assertArrayEquals(pole2, field.getPole());
    }

    @Test
    void DoubleTileValueTest1(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(16), new NumeralTile(64), new EmptyTile(), new EmptyTile()},
                {new NumeralTile(2), new EmptyTile(), new NumeralTile(4), new NumeralTile(1)},
                {new NumeralTile(4), new NumeralTile(32), new EmptyTile(), new NumeralTile(64)},
                {new EmptyTile(), new NumeralTile(1), new NumeralTile(2), new EmptyTile()},
        };
        field.setPole(pole);

        AbilitiesController abilitiesController = new AbilitiesController(field);
        abilitiesController.double_tile_value(0,1);
        assertEquals(new NumeralTile(128), field.getPole()[0][1]);


        abilitiesController.double_tile_value(0,3);
        assertEquals(new EmptyTile(), field.getPole()[0][3]);

    }

    @Test
    void ReplaceWithEightTest(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(16), new NumeralTile(64), new NumeralTile(1), new NumeralTile(2)},
                {new NumeralTile(2), new EmptyTile(), new NumeralTile(4), new NumeralTile(1)},
                {new NumeralTile(4), new NumeralTile(32), new EmptyTile(), new NumeralTile(64)},
                {new EmptyTile(), new NumeralTile(1), new NumeralTile(2), new EmptyTile()},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new NumeralTile(16), new NumeralTile(64), new NumeralTile(8), new NumeralTile(8)},
                {new NumeralTile(8), new EmptyTile(), new NumeralTile(8), new NumeralTile(8)},
                {new NumeralTile(8), new NumeralTile(32), new EmptyTile(), new NumeralTile(64)},
                {new EmptyTile(), new NumeralTile(8), new NumeralTile(8), new EmptyTile()},
        };

        AbilitiesController abilitiesController = new AbilitiesController(field);
        abilitiesController.replace_with_eight();
        assertArrayEquals(pole2, field.getPole());
    }

    @Test
    void RemoveTileTest(){
        Field field = new Field(4);
        Tile[][] pole = {
                {new NumeralTile(16), new NumeralTile(64), new NumeralTile(1), new NumeralTile(2)},
                {new NumeralTile(2), new EmptyTile(), new NumeralTile(4), new NumeralTile(1)},
                {new NumeralTile(4), new NumeralTile(32), new EmptyTile(), new NumeralTile(64)},
                {new EmptyTile(), new NumeralTile(1), new NumeralTile(2), new EmptyTile()},
        };
        field.setPole(pole);

        Tile[][] pole2 = {
                {new NumeralTile(16), new EmptyTile(), new NumeralTile(1), new NumeralTile(2)},
                {new NumeralTile(2), new EmptyTile(), new NumeralTile(4), new NumeralTile(1)},
                {new NumeralTile(4), new NumeralTile(32), new EmptyTile(), new NumeralTile(64)},
                {new EmptyTile(), new NumeralTile(1), new NumeralTile(2), new EmptyTile()},
        };

        AbilitiesController abilitiesController = new AbilitiesController(field);
        abilitiesController.remove_tile(0, 1);
        assertArrayEquals(pole2, field.getPole());

        abilitiesController.remove_tile(1, 1);
        assertArrayEquals(pole2, field.getPole());
    }

}