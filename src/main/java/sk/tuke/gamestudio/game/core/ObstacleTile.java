package sk.tuke.gamestudio.game.core;

import java.util.Objects;

public class ObstacleTile implements Tile{
    private int value;
    private String color = "\033[1;38;2;255;255;255;48;2;139;69;19m";

    public ObstacleTile() {
        this.value = -1;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        System.out.println("Cant change value of Obstacle Tile");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObstacleTile that = (ObstacleTile) o;
        return Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
