import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class GreenTower extends DefenderTower {
    private static final String FILE_PATH_1 = "/resources/GreenLevel1.png";
    //private static final String FILE_PATH_2 = "file:resources/GreenLevel2.png";
    //private static final String FILE_PATH_3 = "file:resources/GreenLevel3.png";

    public GreenTower(Difficulty difficulty, List<Line> paths) {
        super(new ImageView(new Image(FILE_PATH_1)), difficulty, paths, 120, 5);
    }
}
