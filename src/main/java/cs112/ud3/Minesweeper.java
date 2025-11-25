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
public class Minesweeper extends Application implements EventHandler<ActionEvent>{

    public static void main(String[] args){
        launch(args);
    }
    //SETTING UP VARIABLES
    private Button startButton;
    private Label startLabel;

    public Button easyButton, mediumButton, hardButton;
    public Label difficultyLabel;

    //Overrides the inherited class from Application, uses it to set up and execute the window/program
    @Override
    public void start(Stage primaryStage){
        //ASSIGNING VALUES TO THE VARIABLES/MAKING THEM COMPONENTS
        startLabel = new Label("Welcome to Minesweeper!"); //makes it a label
        startButton = new Button("Play!"); //makes it a button
        startButton.setOnAction(this); //allows the button to print to the console when clicked

        //ADDING COMPONENTS
        VBox layout = new VBox(25); //15 = the spacing between components
        layout.getChildren().add(startLabel); //gets the label for the layout
        layout.getChildren().add(startButton); //gets the button for the layout
        layout.setAlignment(Pos.CENTER); //positions the layout of the label and button to the center of the window
        startLabel.setFont(new Font("Arial",24)); //RESIZES THE LABEL AND CHANGES FONT
        startButton.setFont(new Font("Arial", 24)); //RESIZES THE BUTTON AND CHANGES FONT

        //DISPLAYING COMPONENTS
        Scene scene = new Scene(layout, 400,400); //assigns data to the variable
        primaryStage.setScene(scene); //sets up the window in accordance to the variable given
        primaryStage.setTitle("Minesweeper"); //titles the window/program
        primaryStage.show(); //shows the window
    }

    //overrides the handler from the parent class, allows the button to respond by printing a message to the console
    @Override
    public void handle(ActionEvent actionEvent){
        if (actionEvent.getSource() == startButton){ //checks that the source is the start button
            System.out.println("Start button was clicked!");
             showDifficultyWindow(); // calls on the helper method to display a new "page" as shown in the paper storyboard diagram

        } if (actionEvent.getSource() == easyButton){
            System.out.println("Easy button was clicked!");
            String easy = new Easy().toString(); //Calls on the Easy class to print its stored data
            System.out.println(easy); //prints the data
            //can also do custom values by doing String easy = new Easy("Custom", #,#,#,#).toString();
            new BoardGUI().show((Stage) easyButton.getScene().getWindow(), "Easy", 4, 4);  //opens the Easy board GUI showing 4x4 grid
        } else if (actionEvent.getSource() == mediumButton){
            System.out.println("Medium button was clicked!");
            new BoardGUI().show((Stage) mediumButton.getScene().getWindow(), "Medium", 5, 5);   //opens the Medium board GUI showing 5x5 grid
        } else if (actionEvent.getSource() == hardButton){
            System.out.println("Hard button was clicked!");
            new BoardGUI().show((Stage) hardButton.getScene().getWindow(), "Hard", 6, 6);  //opens the Hard board GUI showing 6x6 grid
        }
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
            difficultyLabel.setFont(new Font("Arial",24)); //RESIZES THE LABEL AND CHANGES FONT
            easyButton.setFont(new Font("Arial", 24)); //RESIZES THE BUTTON AND CHANGES FONT
            mediumButton.setFont(new Font("Arial", 24)); //RESIZES THE BUTTON AND CHANGES FONT
            hardButton.setFont(new Font("Arial", 24)); //RESIZES THE BUTTON AND CHANGES FONT

            //DISPLAYING NEW COMPONENTS
            Scene difficultyScene = new Scene(layout, 400,400); //new scene to prepare it to be changed
            Stage stage = (Stage) startButton.getScene().getWindow(); //accesses the old window and scene to change it
            stage.setScene(difficultyScene); //changes to show the new window

    }

}
