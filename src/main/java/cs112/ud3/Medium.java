package cs112.ud3;

public class Medium extends Difficulty {
    public Medium() {
        super("Medium", 15, 15, 30, 30);
    }

    @Override
    public Board createBoard() {
        return new Board(getHeight(), getWidth(), getMineTiles());
    }
}