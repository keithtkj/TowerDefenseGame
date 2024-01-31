import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class BlueTower extends DefenderTower {
    private static final String FILE_PATH_1 = "/resources/BlueLevel1.png";
    //private static final String FILE_PATH_2 = "file:resources/BlueLevel2.png";
    //private static final String FILE_PATH_3 = "file:resources/BlueLevel3.png";

    public BlueTower(Difficulty difficulty, List<Line> paths) {
        super(new ImageView(new Image(FILE_PATH_1)), difficulty, paths, 90, 10);
    }
}
