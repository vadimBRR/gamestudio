package sk.tuke.gamestudio.game.core;

import java.util.Objects;

public class NumeralTile implements Tile, Comparable<NumeralTile> {
    private int value;
    private String color;



    public NumeralTile(int value) {
        this.value = value;
        setColorForNumber();

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        setColorForNumber();

    }

    public String getColor() {
        return color;
    }



    public void setColorForNumber() {

        switch (value) {
            case 1:
                color =  ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 2:
                this.color =  ConsoleColors.YELLOW_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT;
                break;

            case 4:
                this.color =  ConsoleColors.CYAN_BACKGROUND+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 8:
                this.color =  ConsoleColors.CYAN_BACKGROUND_BRIGHT+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 16:
                this.color =  ConsoleColors.BLUE_BACKGROUND+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 32:
                this.color =  ConsoleColors.BLUE_BACKGROUND_BRIGHT+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 64:
                this.color =  ConsoleColors.GREEN_BACKGROUND_BRIGHT+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 128:
                this.color =  ConsoleColors.PURPLE_BACKGROUND+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 256:
                this.color =  ConsoleColors.PURPLE_BACKGROUND_BRIGHT+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 512:
                this.color =  ConsoleColors.RED_BACKGROUND+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            case 1024:
                this.color =  ConsoleColors.RED_BACKGROUND_BRIGHT+ ConsoleColors.WHITE_BOLD_BRIGHT;
                break;
            default:
                this.color =  ConsoleColors.BLACK_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT;
                break;

        }
    }

    @Override
    public int compareTo(NumeralTile o) {
        return Integer.compare(o.value, this.value);
    }

    private static class ConsoleColors {
        private static final String RED_BACKGROUND = "\033[41m";    // RED
        private static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
        private static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
        private static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
        private static final String CYAN_BACKGROUND = "\033[46m";   // CYAN

        // Bold High Intensity
        private static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
        private static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

        // High Intensity backgrounds
        private static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
        private static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
        private static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
        private static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
        private static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
        private static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
        private static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    }

    @Override
    public String toString() {
        return "" + value ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumeralTile tile = (NumeralTile) o;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }



}
