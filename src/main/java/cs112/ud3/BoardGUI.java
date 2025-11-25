package cs112.ud3;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * BoardGUI
 * Displays a Minesweeper board grid based on the Cell model.
 * If no real Cell generation exists, creates blank placeholder Cells.
 */
public class BoardGUI {

    /**
     * Shows a Minesweeper board for a given difficulty and size.
     */
    public void show(Stage stage, String difficulty, int rows, int cols) {
        Label title = new Label("Difficulty: " + difficulty);
        title.setFont(new Font("Arial", 24));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        // --- initialize placeholder cell grid ---
        Cell[][] cells = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // temporary blank cell (not a mine, not flagged)
                cells[r][c] = new Cell() {
                    @Override
                    public boolean isMine() {
                        return false;
                    }
                };
            }
        }

        // build button grid linked to cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = cells[r][c];
                Button button = new Button();
                button.setPrefSize(40, 40);

                int row = r, col = c;
                button.setOnAction(e -> {
                    cell.setRevealed(true);
                    System.out.printf("[%s] Clicked cell (%d, %d)%n", difficulty, row, col);
                    button.setText(cell.isMine() ? "💣" : " ");
                    button.setDisable(true);
                });

                grid.add(button, c, r);
            }
        }

        // back button
        Button back = new Button("Back");
        back.setFont(new Font("Arial", 16));
        back.setOnAction(e -> {
            Minesweeper m = new Minesweeper();
            m.start(stage);
        });

        VBox layout = new VBox(15, title, grid, back);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
    }
}