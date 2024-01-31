import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfigMenu {
    public static VBox scene(Stage primaryStage) {
        primaryStage.setHeight(500);
        primaryStage.setWidth(800);
        Text welcome = new Text("Settings:");
        welcome.setFont(new Font(20));
        String[] difficultys = {"Select Difficulty...", "Easy", "Medium", "Hard"};
        ComboBox<String> comboBox =
            new ComboBox<String>(FXCollections.observableArrayList(difficultys));
        comboBox.getSelectionModel().selectFirst();
        TextField field = new TextField("Name...");
        field.setMaxWidth(200);
        Button button = new Button("Start Game!");
        button.setOnAction(event -> {
            String pick = comboBox.getSelectionModel().getSelectedItem();
            Difficulty difficulty = null;
            Alert a = new Alert(AlertType.WARNING);
            a.setContentText("Invalid Inputs, Please select a difficulty and enter your name.");
            boolean gameStart = true;
            switch (pick) {
            case "Easy":
                difficulty = Difficulty.EASY;
                break;
            case "Medium":
                difficulty = Difficulty.MEDIUM;
                break;
            case "Hard":
                difficulty = Difficulty.HARD;
                break;
            default:
                a.show();
                gameStart = false;
            }
            String name = field.getText().trim();
            if (name.equals("Name...") || name.equals("") || name.trim().isEmpty()) {
                a.show();
                gameStart = false;
            }
            if (gameStart)  {
                Map map = new Map();
                map.scene(primaryStage, difficulty, name);
            }
        });
        VBox root = new VBox(welcome);
        root.getChildren().addAll(comboBox, field, button);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(40);
        return root;
    }
}