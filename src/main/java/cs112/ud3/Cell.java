package cs112.ud3;

public abstract class Cell {
    private boolean revealed;
    private boolean flagged;

    public abstract boolean isMine();

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

