package cs112.ud3;

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
    private Object[][] grid;       // placeholder for Cell[][] until Cell class is built

    /** constructor */
    public Board(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        this.flagsRemaining = totalMines;
        this.grid = new Object[rows][cols];  // placeholder for Cell objects
        // placeholder for mine placement and adjacency setup once Cell is implemented
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
        this.grid = new Object[rows][cols];  // placeholder for Cell[][]
    }

    public void setCols(int cols) {
        this.cols = cols;
        this.grid = new Object[rows][cols];  // placeholder for Cell[][]
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
        int gridSize = (grid != null && grid.length > 0) ? grid.length * grid[0].length : 0;
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
