import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class GameOver {
    public static final String FILE_PATH = "/resources/gameOver.png";
    public static VBox scene(Stage primaryStage) {
        primaryStage.setTitle("Game Over");
        primaryStage.setHeight(500);
        primaryStage.setWidth(700);

        // Ladybug pic
        ImageView gameOverPic = new ImageView(new Image(FILE_PATH));
        //gameOverPic.setTranslateX(200);
        //gameOverPic.setTranslateY(10);

        // Game over text set-up
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 40));
        gameOverText.setFill(Color.RED);
        //gameOverText.setTranslateX(250);
        //gameOverText.setY(200);


        //Button set-up
        Button playAgain = new Button("Play Again");
        Button quit = new Button("Quit Game");
        HBox buttonMenu = new HBox(playAgain, quit);
        buttonMenu.setSpacing(30);
        buttonMenu.setAlignment(Pos.TOP_CENTER);

        // Formatting
        VBox vbox = new VBox(gameOverPic, gameOverText, buttonMenu);
        vbox.setStyle("-fx-background-color: #000000");
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(20);
        //VBox.setMargin(gameOverPic, new Insets(10, 10, 10, 10));
        vbox.setPadding(new Insets(10, 10, 10, 10));

        // BUTTON EVENT HANDLERS

        playAgain.setOnAction(event -> {
            Stage stage = new Stage();
            /**
            stage.setScene(new Scene(Welcome.welcome(stage)));
            stage.show();
             **/

            // FIX BUG WHERE ENEMIES DON'T SPAWN RIGHT AFTER RESTARTING FROM GAME OVER

            Scene scene = new Scene(Welcome.welcome(stage), 800, 500);
            stage.setScene(scene);
            stage.setTitle("The 56's Tower Defense Game");
            stage.show();

            Stage oldStage = (Stage) playAgain.getScene().getWindow();
            oldStage.close();

        });

        quit.setOnAction(event -> {
            Stage stage = (Stage) quit.getScene().getWindow();
            stage.close();
        });





        return vbox;
    }
}
