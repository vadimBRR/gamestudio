package sk.tuke.gamestudio.game.Controllers;

import sk.tuke.gamestudio.game.core.EmptyTile;
import sk.tuke.gamestudio.game.core.Field;
import sk.tuke.gamestudio.game.core.NumeralTile;
import sk.tuke.gamestudio.game.core.Tile;
import sk.tuke.gamestudio.game.core.enums.Direction;

public class MotionController {

    private int[][] placedNumbers;
    private Field field;

    public MotionController(Field field) {
        this.field = field;
        placedNumbers = new int[field.getColCount()][field.getColCount()];
        resetMatrix();
    }



    public boolean moveOrCheckIsPossible(Direction direction , boolean isMove){
        boolean isCanMove = false;
        Tile[][] pole  = field.getPole();
        resetMatrix();
        if(isMove) field.cleanTilesInfo();

        for (int col = 0; col < pole.length; col++) {
            for (int row = 0; row < pole[0].length; row++) {

                if (isValidMove(direction, col, row, pole)) {
                    isCanMove = true;
                    if (isMove) {
                        int idx_i = (direction==Direction.DOWN? (pole.length - col - 1) : col );
                        int idx_j = (direction==Direction.RIGHT? (pole[0].length - row - 1)  : row);
                        pole= move(idx_i, idx_j, direction).getPole();
                    } else {
                        return true;
                    }
                }


            }
        }
        if (isMove) field.setPole(pole);
        return isCanMove;
    }

    private boolean isValidMove(Direction direction, int i, int j, Tile[][] pole) {
        int cols = pole.length;
        int rows = pole[0].length;
        if(placedNumbers.length!=cols || placedNumbers[0].length!=rows){
            placedNumbers = new int[cols][rows];
        }

        if((direction==Direction.DOWN && (cols - i - 1) != cols - 1 ) || ( direction == Direction.RIGHT && (rows - j - 1) != rows - 1 )){
            int idx_i = (direction==Direction.DOWN? (cols - i - 1) : i );
            int idx_j = (direction==Direction.RIGHT? (rows - j - 1)  : j);

            int next_idx_i = (direction==Direction.DOWN? (idx_i +1): i );
            int next_idx_j = (direction==Direction.RIGHT? (idx_j + 1)  : j);


            if(pole[idx_i][idx_j] instanceof NumeralTile && (( pole[next_idx_i][next_idx_j] instanceof EmptyTile)
                    || (pole[idx_i][idx_j].getValue() == pole[next_idx_i][next_idx_j].getValue()))){
                return true;
            }
        }


        if((direction == Direction.UP && i != 0) || (direction == Direction.LEFT && j != 0)){
            int val1 = (direction==Direction.UP? i -1 : i );
            int val2 = (direction==Direction.LEFT? j -1  : j);

            if(pole[i][j] instanceof NumeralTile && ( pole[val1][val2] instanceof EmptyTile
                    ||  pole[i][j].getValue() == pole[val1][val2].getValue())){
                return true;
            }
        }



        return false;
    }

    public Field move(int i, int j, Direction direction){

        if(direction!=Direction.RIGHT && direction!=Direction.LEFT && direction!=Direction.UP && direction!=Direction.DOWN) return field;

        Tile[][] pole = field.getPole();
        int finded_i = i, finded_j = j;

        if(direction==Direction.LEFT || direction == Direction.RIGHT){
            while ((direction == Direction.LEFT && finded_j-1!=0 && pole[finded_i][finded_j-1] instanceof EmptyTile) ||
                    (direction == Direction.RIGHT && finded_j+1!=field.getRowCount() && pole[finded_i][finded_j+1] instanceof EmptyTile)) {
                finded_j+=(direction==Direction.RIGHT ? 1 : -1);
            }
        }else{
            while ((direction == Direction.UP && finded_i-1!=0 && pole[finded_i-1][finded_j] instanceof EmptyTile) ||
                    (direction == Direction.DOWN && finded_i+1<field.getColCount() && pole[finded_i+1][finded_j] instanceof EmptyTile)) {
                finded_i+=(direction==Direction.DOWN ? 1 : -1);
            }
        }




        if((direction == Direction.LEFT && finded_j !=0) || (direction ==Direction.RIGHT && finded_j!=field.getRowCount() - 1)){
            finded_j+=(direction == Direction.RIGHT ? 1 : -1);
        }else if((direction == Direction.UP && finded_i !=0) || (direction ==Direction.DOWN && finded_i!=field.getColCount() - 1)){
            finded_i+=(direction == Direction.DOWN ? 1 : -1);
        }



        if(pole[finded_i][finded_j].getValue() ==pole[i][j].getValue() && placedNumbers[finded_i][finded_j]==0 && placedNumbers[i][j] ==0){
            int new_value = pole[i][j].getValue() + pole[finded_i][finded_j].getValue();

            pole[finded_i][finded_j].setValue(new_value);
            field.addScore(pole[finded_i][finded_j].getValue());
            pole[i][j]=new EmptyTile();


            placedNumbers[finded_i][finded_j] = 1;
        }else{
            if(pole[finded_i][finded_j].getValue()!=0 ){
                if (direction == Direction.LEFT || direction==Direction.RIGHT) finded_j+= (direction==Direction.RIGHT ? -1 : 1);
                else if (direction == Direction.UP || direction==Direction.DOWN ) finded_i+= (direction==Direction.DOWN ? -1 : 1);
            }

            if(pole[finded_i][finded_j] instanceof EmptyTile){
                pole[finded_i][finded_j]= new NumeralTile(pole[i][j].getValue(),false,true,false);
            }else{
                pole[finded_i][finded_j].setValue(pole[i][j].getValue());
                ((NumeralTile) pole[finded_i][finded_j]).setMoved(true);
            }
            pole[i][j]=new EmptyTile();

        }
        field.setPole(pole);
        return field;
    }

    private void resetMatrix(){
        for (int col = 0; col < placedNumbers.length; col++) {
            for (int row = 0; row < placedNumbers[0].length; row++) {
                placedNumbers[col][row] = 0;
            }
        }

    }


}
