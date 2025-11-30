package cs112.ud3;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * BoardGUI
 * Displays a Minesweeper board grid based on the Cell model.
 * If no real Cell generation exists, creates blank placeholder Cells.
 */
public class BoardGUI {

    /**
     * Shows a Minesweeper board for a given difficulty and size.
     */
    public void show(Stage stage, Board board, Difficulty difficulty) {
        // UI header label
        Label title = new Label("Difficulty: " + difficulty.getName());
        title.setFont(new Font("Arial", 24));
        // total mines and remaining flags
        Label mineInfo = new Label(
                "Mines: " + board.getTotalMines() +
                        "    Flags: " + board.getFlagsRemaining()
        );
        mineInfo.setFont(new Font("Arial", 16));

        // Grid container for cell buttons
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        Button[][] buttons = new Button[board.getRows()][board.getCols()];

        // Build button grid from board data
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {

                Button button = new Button();
                button.setPrefSize(40, 40);

                int row = r;
                int col = c;

                buttons[r][c] = button;  // add this line

                button.setOnMouseClicked(e -> {
                    Cell cell = board.getCell(row, col);

                    // Right-click: toggle flag
                    if (e.getButton() == MouseButton.SECONDARY) {
                        if (cell.isRevealed()) return;

                        if (cell.isFlagged()) {
                            board.unflag(row, col);
                            button.setText("");
                        } else {
                            board.flag(row, col);
                            button.setText("🚩");
                        }

                        mineInfo.setText(
                                "Mines: " + board.getTotalMines() +
                                        "    Flags: " + board.getFlagsRemaining()
                        );
                        return;
                    }
                    // Left-click: reveal (ignore if flagged)
                    if (e.getButton() == MouseButton.PRIMARY) {
                        if (cell.isFlagged()) return;

                        board.reveal(row, col);

                        //refresh all revealed cells
                        refreshRevealedCells(board, buttons);

                        // game-over / win checks
                        // check lose
                        if (board.hasRevealedMine()) {
                            // show all bombs as 💣
                            revealAllMines(board, buttons);
                            // after a short delay, turn them into 💥 and show "Game Over"
                            Timeline timeline = new Timeline(
                                    new KeyFrame(Duration.millis(1000), ev -> explodeMines(board, buttons, mineInfo))
                            );
                            timeline.setCycleCount(1);
                            timeline.play();

                            return;
                        }
                        // check win
                        if (board.allSafeCellsRevealed()) {
                            mineInfo.setText("You win – all safe cells revealed");
                            disableAllButtons(buttons);
                        }
                    }
                });
                grid.add(button, c, r);
            }
        }

        // Return to main menu
        Button back = new Button("Back");
        back.setFont(new Font("Arial", 16));
        back.setOnAction(e -> new Minesweeper().start(stage));

        // Root layout container
        VBox layout = new VBox(15, title, mineInfo, grid, back);
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout));
        stage.sizeToScene();
    }
    private void refreshRevealedCells (Board board, Button[][]buttons){
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Cell cell = board.getCell(r, c);
                Button b = buttons[r][c];

                // Only update newly revealed, non-flagged cells
                if (cell.isRevealed() && !b.isDisabled() && !cell.isFlagged()) {
                    if (cell.isMine()) {
                        b.setText("💣");
                    } else {
                        SafeCell safe = (SafeCell) cell;
                        int count = safe.getNeighboringMines();
                        b.setText(count == 0 ? "" : String.valueOf(count));
                    }
                    b.setDisable(true);
                }
            }
        }
    }
    private void revealAllMines(Board board, Button[][] buttons) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Cell cell = board.getCell(r, c);
                Button b = buttons[r][c];

                if (cell.isMine()) {
                    b.setText("💣");
                }
                b.setDisable(true);
            }
        }
    }
    private void explodeMines(Board board, Button[][] buttons, Label mineInfo) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Cell cell = board.getCell(r, c);
                Button b = buttons[r][c];

                if (cell.isMine()) {
                    b.setText("💥");
                }
            }
        }
        mineInfo.setText("Game Over – you hit a mine");
    }

    private void disableAllButtons(Button[][] buttons) {
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
                buttons[r][c].setDisable(true);
            }
        }
    }
}