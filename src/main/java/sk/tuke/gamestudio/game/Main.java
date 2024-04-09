package sk.tuke.gamestudio.game;

import sk.tuke.gamestudio.game.consoleui.ConsoleUI;
import sk.tuke.gamestudio.game.core.Field;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(4);
        ConsoleUI consoleUI = new ConsoleUI(field);
        consoleUI.play();
    }
}
