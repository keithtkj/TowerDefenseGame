import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Enemy {
    private static final String RED_FILE_PATH = "/resources/Bug.png";
    private static final String BLUE_FILE_PATH = "/resources/BlueBug.png";
    private static final String WHITE_FILE_PATH = "/resources/WhiteBug.png";
    private ImageView enemyImage;
    private PathTransition pT;
    private static final Random RAND = new Random();
    private int health;
    private int strength;
    private boolean isInvisible;

    public Enemy(Difficulty difficulty, Path path) {
        isInvisible = false;
        int randNum = RAND.nextInt(10);
        if (randNum <= 6) {
            enemyImage = new ImageView(new Image(RED_FILE_PATH));
            health = 50;
            strength = 20;
        } else if (randNum <= 8) {
            enemyImage = new ImageView(new Image(BLUE_FILE_PATH));
            health = 100;
            strength = 40;
        } else {
            enemyImage = new ImageView(new Image(WHITE_FILE_PATH));
            health = 200;
            strength = 100;
        }
        enemyImage.setVisible(false);
        enemyImage.setPreserveRatio(true);
        enemyImage.setFitHeight(50);
        pT = new PathTransition(Duration.millis(20000), path);
        pT.setInterpolator(Interpolator.LINEAR);
        pT.setNode(enemyImage);
        pT.setCycleCount(Transition.INDEFINITE);
        pT.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
        pT.play();
    }

    public ImageView getEnemy() {
        return this.enemyImage;
    }

    public void makeVisible() {
        enemyImage.setVisible(true);
    }

    public int getStrength() {
        return strength;
    }

    public boolean isInvisible() {
        return isInvisible;
    }

    public boolean takeDamage(int damage) {
        health -= damage;
        enemyImage.setVisible(false);
        isInvisible = true;
        return health <= 0;

    }
}
