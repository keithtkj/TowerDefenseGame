import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Tower {
    public static final String FILE_PATH = "/resources/tower.png";
    private int health;

    private VBox tower;
    private Text healthText;
    private Difficulty difficulty;

    //added so tower can access player's money


    public Tower(Difficulty difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
        case EASY:
            this.health = 500;
            break;
        case MEDIUM:
            this.health = 400;
            break;
        case HARD:
            this.health = 200;
            break;
        default:
            this.health = 0;
        }
        ImageView towerImage = new ImageView(new Image(FILE_PATH));
        this.healthText = new Text("Health: " + String.valueOf(this.health));
        this.healthText.setFont(Font.font("Comic Sans MS"));
        this.healthText.setTextAlignment(TextAlignment.CENTER);
        this.tower = new VBox(this.healthText, towerImage);
    }

    public VBox getTower() {
        return this.tower;
    }

    public int getHealth() {
        return this.health;
    }

    public boolean reduceHealth(int loss) {
        this.health -= loss;
        if (this.health < 0) {
            this.health = 0;
        }
        this.healthText.setText("Health: " + String.valueOf(this.health));
        return this.health <= 0;
    }
}
