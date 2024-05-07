package sk.tuke.gamestudio.game.core;


import sk.tuke.gamestudio.game.core.enums.GameState;

import java.util.*;

public class Field {
    private int rowCount;
    private int colCount;
    private Tile pole[][];
    private int score=0;
    private int count_obstacle = 0;
    private boolean isFirstRun = true;
    private int scores_to_win = 1024;
    private int count_steps = 0;
    private boolean is_use_perks = false;
    private List<String> perks= new ArrayList<>();
    private GameState state = GameState.PLAYING;

    public Field(int length) {
        this.rowCount = length;
        this.colCount = length;
        pole = new Tile[colCount][rowCount];
        generate();

    }

    public Field(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        pole = new Tile[colCount][rowCount];
        generate();
    }


    public void createField(int difficult){
        if(difficult == 1){
            setRowCount(5);
            setColCount(5);
        }else if(difficult == 2){
            setRowCount(4);
            setColCount(4);
        }else if(difficult==3){
            setRowCount(3);
            setColCount(3);
        }


        pole = new Tile[colCount][rowCount];
        int temp_obstacle = count_obstacle;
        reset();
        setCount_obstacle(temp_obstacle);
        generate();
    }

    public Tile[][] generate(){

        int count_numbers = 1;
        int temp_count_obstacle = 0;
        if(isFirstRun){
            for (int col = 0; col < colCount; col++) {
                for (int row = 0; row < rowCount; row++) {
                    pole[col][row] = new EmptyTile();
                }
            }
            isFirstRun = false;
            count_numbers=2;
            temp_count_obstacle = count_obstacle;

        }

        boolean isFound = false;
        Random rand = new Random();
        while(!isFound){
            int dx = rand.nextInt(rowCount);
            int dy = rand.nextInt(colCount);

            if(pole[dy][dx]instanceof EmptyTile) {
                if(count_numbers == 0){
                    pole[dy][dx] = new ObstacleTile();
                    temp_count_obstacle--;
                }else{
                    pole[dy][dx] = new NumeralTile(1);
                    count_numbers--;
                }

            }
            if(count_numbers == 0 && temp_count_obstacle == 0) isFound = true;
        }



        return pole;
    }

    public void printPole() {
        System.out.print("\t\t\t");

        for (int row = 0; row < rowCount; row++) {
            System.out.print("    " + row + "   ");
        }
        System.out.println();
        System.out.println();

        System.out.print("\t\t\t");
        System.out.print("\033[0;107m ");
        for (int row = 0; row < rowCount; row++) {
            System.out.print("        ");
        }
        System.out.println("\033[0m");

        for (int col = 0; col < colCount; col++) {

            System.out.print("\t\t\t");

            System.out.print("\033[0;107m ");

            for (int row = 0; row < rowCount; row++) {
                System.out.print(pole[col][row].getColor());
                System.out.print("+-----+");
                System.out.print("\033[0;107m ");
            }

            System.out.println("\033[0m");


            System.out.print("\t\t");
            System.out.print("     " + col + "  ");
            System.out.print("\033[0;107m ");

            for (int row = 0; row < rowCount; row++) {
                if (!(pole[col][row] instanceof EmptyTile)) {
                    System.out.print(pole[col][row].getColor());
                    int value = pole[col][row] instanceof NumeralTile ? (pole[col][row]).getValue() : 0;
                    String formattedValue;
                    if(value>=100){
                        formattedValue = String.format("|%4s%1s|", value , "");
                    }else{

                        if(value == 0 && pole[col][row] instanceof ObstacleTile){
                            formattedValue = String.format("|%3s%2s|","X", "");
                        }else formattedValue = String.format("|%3s%2s|",value > 0 ? value : "", "");

                    }

                    System.out.print(formattedValue);
                    System.out.print("\033[0;107m ");


                } else {
                    System.out.print(pole[col][row].getColor());
                    System.out.print("|     |");
                    System.out.print("\033[0;107m ");

                }
            }
            System.out.println("\033[0m");
            System.out.print("\t\t\t");
            System.out.print("\033[0;107m ");

            for (int row = 0; row < rowCount; row++) {
                System.out.print(pole[col][row].getColor());
                System.out.print("+_____+");
                System.out.print("\033[0;107m ");

            }

            System.out.println("\033[0m");
            System.out.print("\t\t\t");
            System.out.print("\033[0;107m ");
            for (int row = 0; row < rowCount; row++) {
                System.out.print("        ");
            }
            System.out.println("\033[0m");

        }
        System.out.println("\t\t\tScore: " + getScore());
        if(is_use_perks) System.out.println("\t\t\tSteps: " + getCount_steps());
        System.out.println();
    }


    public void reset(){
        isFirstRun=true;
        count_obstacle = 0;
        setState(GameState.PLAYING);
        setScore(0);
        setCount_steps(0);
        perks = new ArrayList<>();

    }

    public void setNewValue(int i, int j, Tile tile) {
        pole[i][j] = tile;
    }

    public void addScore(int number){
        this.score+=number;
    }


    public void addPerk(){
        if(!is_use_perks || count_steps < (perks.size() +1) * 10 ) return;

        setCount_steps(0);
        List<String> availablePerks = new ArrayList<>(Arrays.asList("remove the tile", "sort field", "double the value of the tile","replace smaller values with 8"));
        if(perks.size() == availablePerks.size()) return;


        this.count_steps = 0;
        availablePerks.removeAll(perks);
        if(availablePerks.size() == 1) perks.add(availablePerks.get(0));
        else{
            Random random = new Random();
            int randomIndex = random.nextInt(availablePerks.size());
            String randomPerk = availablePerks.get(randomIndex);

            perks.add(randomPerk);
        }



    }

    public void removePerk(int idx){
        perks.remove(idx);
    }
    public Tile[][] getPole() {
        return pole;
    }
    public void setPole(Tile[][] pole) {

        this.pole = pole;
        this.colCount = pole.length;
        this.rowCount = pole[0].length;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getRowCount() {
        return rowCount;
    }
    public int getColCount() {
        return colCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    public GameState getState() {
        return state;
    }
    public void setState(GameState state) {
        this.state = state;
    }

    public int getCount_obstacle() {
        return count_obstacle;
    }

    public void setCount_obstacle(int count_obstacle) {
        this.count_obstacle = count_obstacle;
    }

    public int getScores_to_win() {
        return scores_to_win;
    }

    public void setScores_to_win(int scores_to_win) {
        this.scores_to_win = scores_to_win;
    }

    public boolean getIs_use_perks() {
        return is_use_perks;
    }

    public void setIs_use_perks(boolean is_use_perks) {
        this.is_use_perks = is_use_perks;
    }

    public int getCount_steps() {
        return count_steps;
    }

    public void setCount_steps(int count_steps) {
        this.count_steps = count_steps;
    }
    public void addOneStep(){
        this.count_steps++;
        addPerk();
    }

    public void cleanTilesInfo(){
        for (int col = 0; col < colCount; col++) {
            for (int row = 0; row < rowCount; row++) {
                if(pole[col][row] instanceof NumeralTile){
                    ((NumeralTile) pole[col][row]).setNew(false);
                    ((NumeralTile) pole[col][row]).setMoved(false);
                    ((NumeralTile) pole[col][row]).setDoubled(false);
                }
            }
        }
    }

    public List<String> getPerks() {
        return perks;
    }


}
