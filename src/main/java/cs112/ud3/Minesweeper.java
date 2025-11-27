package cs112.ud3;

import javafx.application.Application;  //abstract class used for JavaFX GUI's
import javafx.geometry.Pos;             //used to reposition text and other components within the window of the vbox
import javafx.stage.Stage;              //class for GUI window
import javafx.scene.Scene;              //class for specific view in GUI window
import javafx.scene.layout.VBox;        //class for layout pane, organized top-to-bottom
import javafx.scene.control.Label;      //class for label component
import javafx.scene.control.Button;     //class for button component
import javafx.event.EventHandler;       //interface for handling events
import javafx.event.ActionEvent;        //class for type of event for action (like button or key pressed)

import javafx.scene.text.Font;          //Assists with text resizing

/* Minesweeper
*  This class acts as the main driver for the rest of the program, being the 'home base' of the GUI components.
* Currently, it sets up the start page and then moves on to the difficulty page when the play button is clicked
* it will then display the difficulty buttons, upon which, they will provide data about the Model class that each
* difficulty is based upon.
* For example, when the Easy button is clicked, it will print a message to the console saying that it was clicked,
* and then it will access the Easy class to utilize its toString to then print that to the console to display the
* data stored in that class.
*
* @author Noah Shew
* @version v3
*/
public class Minesweeper extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        launch(args);
    }

    //SETTING UP VARIABLES
    private Button startButton;
    private Label startLabel;

    public Button easyButton, mediumButton, hardButton;
    public Label difficultyLabel;

    //Overrides the inherited class from Application, uses it to set up and execute the window/program
    @Override
    public void start(Stage primaryStage) {
        //ASSIGNING VALUES TO THE VARIABLES/MAKING THEM COMPONENTS
        startLabel = new Label("Welcome to Minesweeper!"); //makes it a label
        startButton = new Button("Play!"); //makes it a button
        startButton.setOnAction(this); //allows the button to print to the console when clicked

        //ADDING COMPONENTS
        VBox layout = new VBox(25); //15 = the spacing between components
        layout.getChildren().add(startLabel); //gets the label for the layout
        layout.getChildren().add(startButton); //gets the button for the layout
        layout.setAlignment(Pos.CENTER); //positions the layout of the label and button to the center of the window
        startLabel.setFont(new Font("Arial", 24)); //RESIZES THE LABEL AND CHANGES FONT
        startButton.setFont(new Font("Arial", 24)); //RESIZES THE BUTTON AND CHANGES FONT

        //DISPLAYING COMPONENTS
        Scene scene = new Scene(layout, 400, 400); //assigns data to the variable
        primaryStage.setScene(scene); //sets up the window in accordance to the variable given
        primaryStage.setTitle("Minesweeper"); //titles the window/program
        primaryStage.show(); //shows the window
    }

    public void showDifficultyWindow() {

        difficultyLabel = new Label("Choose a difficulty!");

        easyButton = new Button("Easy");
        easyButton.setOnAction(this);

        mediumButton = new Button("Medium");
        mediumButton.setOnAction(this);

        hardButton = new Button("Hard");
        hardButton.setOnAction(this);

        //ADDING NEW COMPONENTS
        VBox layout = new VBox(5, difficultyLabel, easyButton, mediumButton, hardButton); //25 = the spacing between components
        layout.setAlignment(Pos.CENTER);
        difficultyLabel.setFont(new Font("Arial", 24)); //RESIZES THE LABEL AND CHANGES FONT
        easyButton.setFont(new Font("Arial", 24)); //RESIZES THE BUTTON AND CHANGES FONT
        mediumButton.setFont(new Font("Arial", 24)); //RESIZES THE BUTTON AND CHANGES FONT
        hardButton.setFont(new Font("Arial", 24)); //RESIZES THE BUTTON AND CHANGES FONT

        //DISPLAYING NEW COMPONENTS
        Scene difficultyScene = new Scene(layout, 400, 400); //new scene to prepare it to be changed
        Stage stage = (Stage) startButton.getScene().getWindow(); //accesses the old window and scene to change it
        stage.setScene(difficultyScene); //changes to show the new window

    }

    //overrides the handler from the parent class, allows the button to respond by printing a message to the console
    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == startButton) { // when start button is clicked
            showDifficultyWindow(); // display a new "page" with difficulty options
            System.out.println("Start button was pressed!");
            return;
        }
        Difficulty d = null; //declare difficulty
        Object src = actionEvent.getSource();
        if (src == easyButton) {
            d = new Easy();
            System.out.println("Easy button was clicked!");
        } else if (src == mediumButton) {
            d = new Medium();
            System.out.println("Medium button was clicked!");
        } else if (src == hardButton) {
            d = new Hard();
            System.out.println("Hard button was clicked!");
        }
        // If a difficulty is chosen, build the board and show the GUI
        if (d != null) {
            Board newBoard = new Board(d.getHeight(), d.getWidth(), d.getMineTiles());
            System.out.println(newBoard);  // optional: verify the board

            Stage stage = (Stage) ((Button) src).getScene().getWindow();
            new BoardGUI().show(stage, d.getName(), d.getHeight(), d.getWidth());
        }
    }
}