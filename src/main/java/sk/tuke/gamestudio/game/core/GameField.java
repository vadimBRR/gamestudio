package sk.tuke.gamestudio.game.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameField {
    private int rowCount;
    private int colCount;
    private int scores;
    private Tile[][] tiles;
    private boolean isChanged;
    private boolean isGameFinished;
    private boolean isPlayerWon;
    private boolean isCanSendScore;
    private List<Integer> perks = new ArrayList<>();

    public GameField(Field field, boolean isChanged, boolean isGameFinished, boolean isPlayerWon, boolean isCanSendScore, List<String> perks) {
        this.rowCount = field.getRowCount();
        this.colCount = field.getColCount();
        this.isChanged = isChanged;
        this.isGameFinished = isGameFinished;
        this.tiles = field.getPole();
        this.scores = field.getScore();
        this.isPlayerWon = isPlayerWon;
        this.isCanSendScore = isCanSendScore;
        convertPerksToInt(perks);
    }
    private void convertPerksToInt(List<String> perksString){

        for (String perk : perksString){
            switch (perk) {
                case "remove the tile" -> this.perks.add(0);
                case "sort field" -> this.perks.add(1);
                case "double the value of the tile" -> this.perks.add(2);
                case "replace smaller values with 8" -> this.perks.add(3);
            }

        }
    }

//    private void convertTiles(Tile[][] tiles){
//        this.tiles = new int [tiles.length][tiles[0].length];
//        for (int col = 0; col < tiles.length; col++) {
//            for (int row = 0; row < tiles[0].length; row++) {
//                this.tiles[col][row] = tiles[col][row].getValue();
//            }
//        }
//    }


}
