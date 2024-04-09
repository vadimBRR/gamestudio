package sk.tuke.gamestudio.game.core;

import java.util.Objects;

public class EmptyTile implements Tile{
    int value;
    private String color = "\033[0;107m" + "\033[1;90m";
    public EmptyTile() {
        value=0;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        System.out.println("Cant change value of Empty Tile");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmptyTile empty = (EmptyTile) o;
        return Objects.equals(color, empty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
