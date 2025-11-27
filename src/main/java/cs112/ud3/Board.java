package cs112.ud3;

import java.util.Random;

/**
 * Board class for Minesweeper game.
 * This class manages the game board, including the grid of cells,
 * mine placement, and player actions like revealing and flagging cells.
 *
 * Note: This is a simplified version without the Cell class implemented
 * @author Jeff Peterson
 */

public class Board {
    // fields
    private int rows;
    private int cols;
    private int totalMines;
    private int flagsRemaining;
    private Cell[][] cellGrid;       // placeholder for Cell[][] until Cell class is built

    /** constructor */
    public Board(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        this.flagsRemaining = totalMines;
        this.cellGrid = new Cell[rows][cols] ;  // placeholder for Cell objects
        mineRandomizer(); // placeholder for mine placement and adjacency setup once Cell is implemented
        updateBombCount(); // placeholder for neighboring mines checker
    }

    private void mineRandomizer(){
        int placedMines = 0;
        Random randomNum = new Random();
        int minesLeft;

        //For-loop fixes NullPointerExceptions by assigning all coordinates in the array to a SafeCell
        for(int x = 0; x < rows; x++){
            for(int y = 0; y < cols; y++){
                cellGrid[x][y] = new SafeCell();
            }
        }

        //has to be a while loop to make sure that the placedMines integer doesn't increase in value even if it 'places' a mine where there already is one. Gives more control to changing the placedMines integer.
        while(placedMines < totalMines){
            int randomRow = randomNum.nextInt(rows); //'rows' and 'cols' are the upper bound of the random.nextInt() range that it will generate a random number in
            int randomColumn = randomNum.nextInt(cols);
            minesLeft = (totalMines - 1) - placedMines;

            //'!' is included before the .equals to represent a 'does not equal' statement, similar to something like '!='
            if (!cellGrid[randomRow][randomColumn].isMine()) { //true represents a TRUE value of a bomb. It indicates that that space has a bomb present in that array coordinate and should register as such.
                //Gets rid of old SafeCell Object and adds a new MineCell in same spot
                cellGrid[randomRow][randomColumn] = new MineCell();

                //Remove later, tests function of isMine() and if bomb was placed correctly
                System.out.println("The coordinate of (" + randomRow + ", " + randomColumn + "), has been selected to place a bomb. Bombs left to place: " + minesLeft);
                System.out.println("isMine() returns: " + cellGrid[randomRow][randomColumn].isMine());
                placedMines++;
            } else if (cellGrid[randomRow][randomColumn].isMine()){
                System.out.println("Coordinate (" + randomRow + ", " + randomColumn +") already has bomb, placedMines was not increased.");
            }

        }
        System.out.println("All bombs placed.");
    }

    //Runs through cellGrid to assign NeighboringMines value to each safe cell
    private void updateBombCount() {
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                Cell currentCell = cellGrid[row][col];

                //Checks if current cell is not a bomb and continues if so
                if (!currentCell.isMine()){
                    int bombCount = 0;

                    //Loop through the 3x3 area around currentCell.
                    for (int r = row - 1;  r <= row + 1; r++) {
                        for (int c = col - 1; c <= col + 1; c++) {

                            //Skips the cell itself
                            if (r == row && c == col){
                                continue;
                            }

                            //Skips anything outside the grid
                            if (r < 0 || r >= rows || c < 0 || c >= cols) {
                                continue;
                            }

                            if (cellGrid[r][c].isMine()) {
                                bombCount++;
                            }
                        }
                    }
                    //Might be wrong: Casts current cell to make sure it's a SafeCell then sets the NeighboringMines
                    SafeCell currentSafeCell = (SafeCell) currentCell;
                    currentSafeCell.setNeighboringMines(bombCount);
                }
            }
        }

        //Mockup Grid
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                Cell currentCell = cellGrid[row][col];

                if  (!currentCell.isMine()){
                    SafeCell currentSafeCell = (SafeCell) currentCell;
                    System.out.print(" " + currentSafeCell.getNeighboringMines() + " ");
                } else {
                    System.out.print(" B ");
                }
                if (col == cols - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    /** actions */
    public void reveal(int row, int col) {
        if (!inBounds(row, col)) {
            // placeholder for throwing InvalidMoveException once defined
            System.out.println("Reveal out of bounds");
            return;
        }
        // placeholder for reveal logic using Cell once Cell class is built
    }

    public void flag(int row, int col) {
        if (!inBounds(row, col)) {
            System.out.println("Flag out of bounds"); // placeholder for exception later
            return;
        }
        if (flagsRemaining > 0) {
            flagsRemaining--;
            // placeholder for marking a Cell as flagged
        }
    }

    public void unflag(int row, int col) {
        if (!inBounds(row, col)) {
            System.out.println("Unflag out of bounds"); // placeholder for exception later
            return;
        }
        if (flagsRemaining < totalMines) {
            flagsRemaining++;
            // placeholder for unmarking a Cell as flagged
        }
    }

    /** helpers */
    private boolean inBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /** getters */
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getTotalMines() { return totalMines; }
    public int getFlagsRemaining() { return flagsRemaining; }

    /** setters */
    public void setRows(int rows) {
        this.rows = rows;
        this.cellGrid = new Cell[rows][cols];  // placeholder for Cell[][]
    }

    public void setCols(int cols) {
        this.cols = cols;
        this.cellGrid = new Cell[rows][cols];  // placeholder for Cell[][]
    }

    public void setTotalMines(int mines) {
        this.totalMines = mines;
        if (flagsRemaining > mines) {
            flagsRemaining = mines;
        }
        // placeholder for adjusting grid when mine count changes
    }

    public void setFlagsRemaining(int flags) {
        this.flagsRemaining = flags;
    }

    /** overrides */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Board)) return false;
        Board b = (Board) o;
        return rows == b.rows && cols == b.cols && totalMines == b.totalMines && flagsRemaining == b.flagsRemaining;
        // placeholder for deep comparison of grid once Cell exists
    }

    @Override
    public String toString() {
        int gridSize = (cellGrid != null && cellGrid.length > 0) ? cellGrid.length * cellGrid[0].length : 0;
        return "Board{rows=" + rows + ", cols=" + cols + ", totalMines=" + totalMines + ", flagsRemaining=" + flagsRemaining + ", gridSize=" + gridSize + "}";
    }

    /** inner class */
    public class Position {
        private int row;   // row index on the board
        private int col;   // column index on the board

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() { return row; }
        public int getCol() { return col; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Position)) return false;
            Position p = (Position) o;
            return row == p.row && col == p.col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }
}
