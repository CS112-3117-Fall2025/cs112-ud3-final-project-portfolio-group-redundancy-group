package cs112.ud3;

import cs112.ud3.Cell;

public class SafeCell extends Cell {
    private final String[] emojiNumbers = {"1","2","3","4","5","6","7","8","9"};
    private int neighborMines;

    public SafeCell() {

    }

    public SafeCell(int neighborMines) {
        this.neighborMines = neighborMines;
    }

    public int getNeighboringMines() {
        return neighborMines;
    }

    public void setNeighboringMines(int neighboringMines) {
        this.neighborMines = neighboringMines;
    }

    @Override
    public boolean revealCell() {
        return false;
    }

    @Override
    public boolean isMine() {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }
}
