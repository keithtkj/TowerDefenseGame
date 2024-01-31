import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;


public class DefenderTower {

    private double deltaX;
    private double deltaY;
    private ImageView tower;
    private int radius;
    private int damage;
    private Circle circle;


    public DefenderTower(ImageView towerPic, Difficulty difficulty, 
                         List<Line> paths, int radius, int damage) {
        this.damage = damage;
        this.radius = radius;
        switch (difficulty) {
        case EASY:
            Map.subtractMoney(25);
            break;
        case MEDIUM:
            Map.subtractMoney(50);
            break;
        default:
            Map.subtractMoney(100);
        }
        this.tower = towerPic;
        tower.setTranslateX(100);
        tower.setTranslateY(100);
        this.circle = new Circle();
        circle.setRadius(radius);
        circle.setVisible(false);
        this.tower.setPreserveRatio(true);
        this.tower.setFitHeight(75);
        circle.setCenterX(tower.getTranslateX() + tower.getFitHeight() / 2);
        circle.setCenterY(tower.getTranslateY() + tower.getFitHeight() / 2);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        tower.setOnMousePressed(newEvent -> {
            deltaX = tower.getTranslateX() - newEvent.getSceneX();
            deltaY = tower.getTranslateY() - newEvent.getSceneY();
            circle.setVisible(true);
        });
        tower.setOnMouseDragged(newEvent -> {
            double oldX = tower.getTranslateX();
            double oldY = tower.getTranslateY();
            tower.setTranslateX(newEvent.getSceneX() + deltaX);
            tower.setTranslateY(newEvent.getSceneY() + deltaY);
            if (outOfBounds(paths, tower)) {
                tower.setTranslateX(oldX);
                tower.setTranslateY(oldY);
            }
            circle.setCenterX(tower.getTranslateX() + tower.getFitHeight() / 2);
            circle.setCenterY(tower.getTranslateY() + tower.getFitHeight() / 2);
        });
        tower.setOnMouseReleased(newEvent -> {
            circle.setVisible(false);
        });
    }

    private static boolean outOfBounds(List<Line> paths, ImageView tower) {
        boolean oob = false;
        for (Line path : paths) {
            if (path.getBoundsInLocal().intersects(tower.getBoundsInParent())) {
                oob = true;
            }
        }
        return oob;
    }

    public ImageView getTower() {
        return this.tower;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getDamage() {
        return this.damage;
    }
}

