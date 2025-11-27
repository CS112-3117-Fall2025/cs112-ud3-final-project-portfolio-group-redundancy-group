package cs112.ud3;

import cs112.ud3.Cell;

public class MineCell extends Cell {
    public MineCell() {

    }

    @Override
    public boolean revealCell() {
        return true;
    }

    @Override
    public boolean isMine() {
        return true;
    }

    @Override
    public String toString() {
        return "💣";
    }
}