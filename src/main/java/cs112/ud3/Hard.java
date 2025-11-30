package cs112.ud3;

public class Hard extends Difficulty {
    public Hard() {
        super("Hard", 20, 20, 50, 50);
    }

    @Override
    public Board createBoard() {
        return new Board(getHeight(), getWidth(), getMineTiles());
    }
}