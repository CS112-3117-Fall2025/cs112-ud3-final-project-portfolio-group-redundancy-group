package cs112.ud3;

public abstract class Cell {
    private boolean revealed;
    private boolean flagged;

    public abstract boolean isMine();

    public abstract boolean revealCell();

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
}

