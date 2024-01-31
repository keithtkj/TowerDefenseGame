import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;


public class PinkTower extends DefenderTower {
    private static final String FILE_PATH_1 = "/resources/PinkLevel1.png";
    //private static final String FILE_PATH_2 = "file:resources/PinkLevel2.png";
    //private static final String FILE_PATH_3 = "file:resources/PinkLevel3.png";

    public PinkTower(Difficulty difficulty, List<Line> paths) {
        super(new ImageView(new Image(FILE_PATH_1)), difficulty, paths, 150, 3);
    }
}
