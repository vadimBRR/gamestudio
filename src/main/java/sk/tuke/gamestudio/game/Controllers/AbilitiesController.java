package sk.tuke.gamestudio.game.Controllers;

import sk.tuke.gamestudio.game.core.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AbilitiesController {
    private Field field;

    public AbilitiesController(Field field) {
        this.field = field;
    }


    public void usePerk(int idx){
        List<String> availablePerks = new ArrayList<>(Arrays.asList("remove the tile", "sort field", "double the value of the tile","replace smaller values with 8"));
        int id_perk = availablePerks.indexOf(field.getPerks().get(idx));
        if(id_perk == -1){
            System.out.println("Cant use this perk!");
            return;
        }

        if(id_perk == 1 ) sortField();
        else if(id_perk == 3) replace_with_eight();
        else{
            Scanner scanner = new Scanner(System.in);

            System.out.print("Choose a column: ");
            Pattern COLUMN_PATTERN= Pattern.compile("[0-"+field.getColCount()+"]");
            String col = scanner.nextLine();
            while (!COLUMN_PATTERN.matcher(col).matches()) {
                System.out.println("The number must be in the range 0 to " + field.getColCount());
                col = scanner.nextLine();
            }
            System.out.print("Choose a row: ");
            Pattern ROW_PATTERN= Pattern.compile("[0-"+field.getRowCount()+"]");
            String row = scanner.nextLine();
            while (!ROW_PATTERN.matcher(row).matches()) {
                System.out.println("The number must be in the range 0 to " + field.getRowCount());
                row = scanner.nextLine();
            }

            if(id_perk == 0) remove_tile(Integer.parseInt(col), Integer.parseInt(row));
            if(id_perk == 2) double_tile_value(Integer.parseInt(col), Integer.parseInt(row));

        }
        field.removePerk(idx);

    }
    public void sortField(){

        System.out.println();
        int cols = field.getColCount();
        int rows = field.getRowCount();
        Tile[][] pole = field.getPole();
        int count_numbers = 0;

        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                if(pole[col][row] instanceof NumeralTile) count_numbers++;
            }
        }
        Tile[] temp_tile = new Tile[count_numbers];
        int count= 0;


        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                if(pole[col][row] instanceof NumeralTile) temp_tile[count++] = pole[col][row];
            }
        }

        Arrays.sort(temp_tile);
         count= 0;
        Tile[][] new_pole = field.getPole();
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {

                if(count_numbers!=count && !(pole[col][row] instanceof ObstacleTile)) {
                    new_pole[col][row] = temp_tile[count++];
                }
                else if(pole[col][row] instanceof ObstacleTile){
                    new_pole[col][row] = new ObstacleTile();
                }
                else{
                    new_pole[col][row] = new EmptyTile();
                }

            }

        }

        field.setPole(new_pole);


    }

    public void remove_tile(int col, int row){
        Tile[][] pole = field.getPole();
        if(pole[col][row] instanceof ObstacleTile){
            System.out.println("You can't remove the obstacle!");
            return;
        }
        field.setNewValue(col,row,new EmptyTile());
    }

    public void double_tile_value(int col, int row){
        Tile[][] pole = field.getPole();

        if(pole[col][row] instanceof EmptyTile) return;
        else if(pole[col][row] instanceof ObstacleTile){
            System.out.println("You can't double an obstacle!");
            return;
        }
        Tile tile= pole[col][row];
        tile.setValue(tile.getValue() * 2);
        field.setNewValue(col,row,tile);

    }

    public void replace_with_eight(){
        int cols = field.getColCount();
        int rows = field.getRowCount();
        Tile[][] pole = field.getPole();
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                if (!(pole[col][row]instanceof EmptyTile) && pole[col][row].getValue() < 8) {
                    pole[col][row].setValue(8);
                }
            }
        }


    }
}
