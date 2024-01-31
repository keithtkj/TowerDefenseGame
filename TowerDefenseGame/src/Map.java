import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;



public class Map {
    private static Text bankText;
    private static Player player;
    private static List<Enemy> enemyList;
    private Path path;
    private List<Line> bounds;
    private List<DefenderTower> towerList;
    private Pane gameScreen;

    public void scene(Stage stage, Difficulty difficulty, String name) {
        player = new Player(difficulty, name);
        gameScreen = new Pane();
        gameScreen.setMinSize(1000, 800);
        //Tower Menu UI Setup
        Text towerMenuText = new Text("Tower Menu");
        towerMenuText.minWidth(250);
        towerMenuText.setFont(Font.font("Comic Sans MS"));
        VBox towerMenu = new VBox(towerMenuText);

        ImageView tower1pic = new ImageView(new Image("/resources/BlueLevel1.png"));
        VBox tower1 = new VBox(tower1pic);
        Button buyTower1 = new Button("Buy #1");
        tower1.getChildren().addAll(
            new Text("This is a short-range tower that does high damage!"), buyTower1);
        tower1.setAlignment(Pos.TOP_CENTER);

        ImageView tower2pic = new ImageView(new Image("/resources/GreenLevel1.png"));
        VBox tower2 = new VBox(tower2pic);
        Button buyTower2 = new Button("Buy #2");
        tower2.getChildren().addAll(
            new Text("This is a medium-range tower that does medium damage!"), buyTower2);
        tower2.setAlignment(Pos.TOP_CENTER);

        ImageView tower3pic = new ImageView(new Image("/resources/PinkLevel1.png"));
        VBox tower3 = new VBox(tower3pic);
        Button buyTower3 = new Button("Buy #3");
        tower3.getChildren().addAll(
            new Text("This is a high-range tower that does low damage!"), buyTower3);
        tower3.setAlignment(Pos.TOP_CENTER);

        towerMenu.getChildren().addAll(tower1, tower2, tower3);
        towerMenu.setAlignment(Pos.TOP_CENTER);
        towerMenu.setScaleY(0.73);
        towerMenu.setScaleX(0.73);
        towerMenu.setMinWidth(200);
        towerMenu.setStyle("-fx-background-color: #99CC99");

        //Bank Menu UI Setup
        bankText = new Text("Money: $" + player.getMoney());
        bankText.setFill(Color.BLACK);
        bankText.relocate(15, 25);
        bankText.setFont(Font.font("Comic Sans MS"));

        //Tower & Health UI Setup
        Tower tower = new Tower(difficulty);
        VBox towerStuff = new VBox(tower.getTower());
        towerStuff.setAlignment(Pos.CENTER);
        towerStuff.relocate(700, 160);
        gameScreen.getChildren().add(towerStuff);

        //Enemy paths
        makeLevel1();
        gameScreen.getChildren().addAll(this.path);
        
        //Button Events
        initilizeButtons(buyTower1, buyTower2, buyTower3, difficulty);
        
        Button startGame = new Button("Start Game");
        startGame.relocate(920, 25);
        startGame.setOnAction(event -> {

            enemyList = new ArrayList<Enemy>();
            AnimationTimer gameLoop = new AnimationTimer() {
                private long ticCounter = 0;
                private List<Enemy> toRemove = new ArrayList<Enemy>();
                @Override
                public void handle(long now) throws ConcurrentModificationException {
                    if (ticCounter % 60 == 0) {
                        enemyList.add(new Enemy(difficulty, path));
                        gameScreen.getChildren().add(enemyList.get(
                            enemyList.size() - 1).getEnemy());
                    }
                    if (ticCounter % 60 == 1) {
                        enemyList.get(enemyList.size() - 1).getEnemy().setVisible(true);
                    }
                    for (Enemy enemy : enemyList) {
                        if (ticCounter % 2 == 0) {
                            if (enemy.isInvisible()) {
                                enemy.makeVisible();
                            }
                        }
                        if (enemy.getEnemy().getBoundsInParent().intersects(
                            towerStuff.getBoundsInParent())) {
                            gameScreen.getChildren().remove(enemy.getEnemy());
                            toRemove.add(enemy);
                            tower.reduceHealth(enemy.getStrength());
                            if (tower.getHealth() <= 0) {
                                // Go to game over screen
                                stage.setScene(new Scene(GameOver.scene(stage)));
                                // stop the enemies
                                this.stop();
                            }
                        }
                        if (ticCounter % 50 == 0) {
                            for (DefenderTower tower : towerList) {
                                if (enemy.getEnemy().getBoundsInParent().intersects(
                                    tower.getCircle().getBoundsInParent())) {
                                    if (enemy.takeDamage(tower.getDamage())) {
                                        gameScreen.getChildren().remove(enemy.getEnemy());
                                        toRemove.add(enemy);
                                        addMoney(10);
                                    }
                                }
                            }
                        }
                    }
                    enemyList.removeAll(toRemove);
                    toRemove.clear();
                    ticCounter++;
                }
            };
            gameLoop.start();
            startGame.setDisable(true);
        });

        //Map setup
        gameScreen.getChildren().addAll(bankText, startGame);
        Separator separator = new Separator(Orientation.VERTICAL);
        HBox main = new HBox(gameScreen, separator, towerMenu);
        main.setAlignment(Pos.CENTER_RIGHT);
        main.setStyle("-fx-background-color: #99CC99");

        Scene scene = new Scene(main);
        stage.setTitle("Game Map");
        stage.setWidth(1250);
        stage.setHeight(800);

        stage.setScene(scene);
        stage.show();
    }

    public static void subtractMoney(int amount) {
        player.setMoney(player.getMoney() - amount);
        bankText.setText("Money: $" + player.getMoney());
    }

    public static void addMoney(int amount) {
        player.setMoney(player.getMoney() + amount);
        bankText.setText("Money: $" + player.getMoney());
    }

    public static boolean canBuyTower(Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            return player.getMoney() >= 25;
        case MEDIUM:
            return player.getMoney() >= 50;
        default:
            return player.getMoney() >= 100;
        }
    }

    private void makeLevel1() {
        //Path
        this.path = new Path();
        this.path.getElements().add(new MoveTo(0, 400));
        this.path.getElements().add(new LineTo(200, 400));
        this.path.getElements().add(new LineTo(200, 500));
        this.path.getElements().add(new LineTo(600, 500));
        this.path.getElements().add(new LineTo(600, 300));
        this.path.getElements().add(new LineTo(800, 300));
        //Path Bounds
        this.bounds = new ArrayList<Line>();
        Line enemyPath1 = new Line();
        enemyPath1.setStartX(0);
        enemyPath1.setStartY(400);
        enemyPath1.setEndX(200);
        enemyPath1.setEndY(400);
        enemyPath1.setStrokeWidth(5);
        this.bounds.add(enemyPath1);
        Line enemyPath2 = new Line();
        enemyPath2.setStartX(200);
        enemyPath2.setStartY(400);
        enemyPath2.setEndX(200);
        enemyPath2.setEndY(500);
        enemyPath2.setStrokeWidth(5);
        this.bounds.add(enemyPath2);
        Line enemyPath3 = new Line();
        enemyPath3.setStartX(200);
        enemyPath3.setStartY(500);
        enemyPath3.setEndX(600);
        enemyPath3.setEndY(500);
        enemyPath3.setStrokeWidth(5);
        this.bounds.add(enemyPath3);
        Line enemyPath4 = new Line();
        enemyPath4.setStartX(600);
        enemyPath4.setStartY(500);
        enemyPath4.setEndX(600);
        enemyPath4.setEndY(300);
        enemyPath4.setStrokeWidth(5);
        this.bounds.add(enemyPath4);
        Line enemyPath5 = new Line();
        enemyPath5.setStartX(600);
        enemyPath5.setStartY(300);
        enemyPath5.setEndX(800);
        enemyPath5.setEndY(300);
        enemyPath5.setStrokeWidth(5);
        this.bounds.add(enemyPath5);
    }

    public void initilizeButtons(Button buyTower1, Button buyTower2, 
        Button buyTower3, Difficulty difficulty) {
        towerList = new ArrayList<DefenderTower>();
        buyTower1.setOnAction(event -> {
            if (canBuyTower(difficulty)) {
                BlueTower blueTower = new BlueTower(difficulty, bounds);
                towerList.add(blueTower);
                gameScreen.getChildren().add(blueTower.getTower());
                gameScreen.getChildren().add(blueTower.getCircle());
            }
        });
        buyTower2.setOnAction(event -> {
            if (canBuyTower(difficulty)) {
                GreenTower greenTower = new GreenTower(difficulty, bounds);
                towerList.add(greenTower);
                gameScreen.getChildren().add(greenTower.getTower());
                gameScreen.getChildren().add(greenTower.getCircle());
            }
        });
        buyTower3.setOnAction(event -> {
            if (canBuyTower(difficulty)) {
                PinkTower pinkTower = new PinkTower(difficulty, bounds);
                towerList.add(pinkTower);
                gameScreen.getChildren().add(pinkTower.getTower());
                gameScreen.getChildren().add(pinkTower.getCircle());
            }
        });
    }
}