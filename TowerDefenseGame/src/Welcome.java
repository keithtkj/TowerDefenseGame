import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;


public class Welcome extends Application {

    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(welcome(stage), 800, 500);
        stage.setScene(scene);
        stage.setTitle("The 56's Tower Defense Game");
        stage.show();
    }

    public static Pane welcome(Stage stage) {
        // Pane of welcome screen
        Pane pane = new Pane();
        // Pane of next screen after button is clicked: the initial configuration screen
        //Pane pane2 = new Pane();

        // Game title on welcome screen
        Text text = new Text();
        text.setFont(Font.font("comicsans", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text.setX(190);
        text.setY(160);
        text.setText("Tower Defense Game");
        pane.getChildren().add(text);

        // Play button on welcome screen
        Button b = new Button("Play");
        Font font = Font.font("comicsans", FontWeight.BOLD, 36);
        b.setFont(font);
        b.setLayoutX(350);
        b.setLayoutY(350);
        EventHandler<ActionEvent> play = new EventHandler<ActionEvent>() {
            //Goes to the initial configuration screen when button is clicked
            public void handle(ActionEvent e) {
                stage.setScene(new Scene(ConfigMenu.scene(stage)));
            }
        };
        b.setOnAction((play));
        pane.getChildren().add(b);
        return pane;
    }


    public static void main(String[] args) {
        launch(args);
    }
}