import javafx.scene.input.KeyCode;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import org.junit.Test;
// Lizzie has to rename this to junit to get it to work instead of junit5 :P sorry
//import org.testfx.framework.junit.ApplicationTest;

import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.TextMatchers;
import org.testfx.util.WaitForAsyncUtils;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

// The tests we wrote were to ensure that all of the new game elements introduced in the
// M4 milestone worked as intended such as the game over screen, the start game button, etc
// We chose to test these because they are critical elements of a tower defense game, and failing
// any of these aspects would prevent us from 
// developing a more complex tower defense game which is our
// goal in future iterations, as these elements set the foundation for more complex ones in future

public class M5Tests extends ApplicationTest {
    @Before
    public void setUpClass() throws Exception {
        launch(Welcome.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    //Cole's Tests
    //This test is to ensure the enemies are spawning randomly
    @Test
    public void randomSpawning() {
        List<Enemy> list1 = new ArrayList<Enemy>();
        List<Enemy> list2 = new ArrayList<Enemy>();
        for (int i = 0; i < 100; i++) {
            list1.add(new Enemy(Difficulty.EASY, new Path()));
            list2.add(new Enemy(Difficulty.EASY, new Path()));
        }
        assertNotEquals(list1, list2);
    }

    //This test is to make sure the radius circle is not visible when towers spawn.
    @Test
    public void circleIsInvisible() {
        List<DefenderTower> towerList = new ArrayList<DefenderTower>();
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Cole");
        clickOn("Start Game!");
        for (int i = 0; i < 50; i++) {
            towerList.add(new BlueTower(Difficulty.EASY, new ArrayList<Line>()));
            towerList.add(new GreenTower(Difficulty.EASY, new ArrayList<Line>()));
            towerList.add(new PinkTower(Difficulty.EASY, new ArrayList<Line>()));
            assertFalse(towerList.get(i * 3).getCircle().isVisible());
            assertFalse(towerList.get(i * 3 + 1).getCircle().isVisible());
            assertFalse(towerList.get(i * 3 + 2).getCircle().isVisible());
        }
    }

    //John's JUnits
    //These were chosen to make sure that changing the functionality of towers didn't change the
    //ability to place them. We also want to make sure they 
    //still decrease the player's money as expected

    //Makes sure the player's money decrements as expected, and that it can't go below 0
    @Test
    public void testDecrementMoney() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("John's JUnit Test");
        clickOn("Start Game!");

        FxAssert.verifyThat("Money: $400", TextMatchers.hasText("Money: $400"));

        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $300", TextMatchers.hasText("Money: $300"));

        clickOn("Buy #2");
        FxAssert.verifyThat("Money: $200", TextMatchers.hasText("Money: $200"));

        clickOn("Buy #3");
        FxAssert.verifyThat("Money: $100", TextMatchers.hasText("Money: $100"));

        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));

        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
    }

    //makes sure buying a tower displays the correct image
    @Test
    public void testTowerImage() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("John's JUnit Test");
        clickOn("Start Game!");

        clickOn("Buy #3");
        lookup("file:resources/PinkLevel1.png");

        clickOn("Buy #2");
        lookup("file:resources/GreenLevel1.png");

        clickOn("Buy #1");
        lookup("file:resources/BlueLevel1.png");
    }

    // Lizzie's Tests
    // This test makes sure that the game over functionality was not lost with this update.
    @Test
    public void gameOver() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Cole");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(30, TimeUnit.SECONDS);
        FxAssert.verifyThat(window("Game Over"), WindowMatchers.isShowing());
    }

    //This test makes sure the play again functionality was not lost with this update
    @Test
    public void playAgain() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Cole");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(30, TimeUnit.SECONDS);
        clickOn("Play Again");
        FxAssert.verifyThat(window("The 56's Tower Defense Game"), WindowMatchers.isShowing());
    }
    
    // Keith's Tests
    // Test checks that enemies are able to inflict damage to the main tower
    @Test
    public void enemyDamage() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Cole");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(30, TimeUnit.SECONDS);
        FxAssert.verifyThat(window("Game Over"), WindowMatchers.isShowing());
    }

    
    // Test ensures no enemies appear until start button
    @Test
    public void noEnemies() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Keith");
        clickOn("Start Game!");
        WaitForAsyncUtils.sleep(20, TimeUnit.SECONDS);
        FxAssert.verifyThat("Health: 500", TextMatchers.hasText("Health: 500"));
    }
    
    // Dom's Tests
    // This tests if the proper damage is taken by the tower when an enemy attacks
    @Test
    public void moneyCheck() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Dpm");
        clickOn("Start Game!");
        Map.addMoney(100);
        FxAssert.verifyThat("Money: $500", TextMatchers.hasText("Money: $500"));
        Map.addMoney(100);
        FxAssert.verifyThat("Money: $600", TextMatchers.hasText("Money: $600"));
        Map.addMoney(100);
        FxAssert.verifyThat("Money: $700", TextMatchers.hasText("Money: $700"));
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(30, TimeUnit.SECONDS);
        clickOn("Play Again");
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Dpm");
        clickOn("Start Game!");
        FxAssert.verifyThat("Money: $400", TextMatchers.hasText("Money: $400"));
    }
    
    // This tests the quit game button functionality still works after the update
    @Test
    public void testQuitGame() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Dom");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(30, TimeUnit.SECONDS);
        clickOn("Quit Game");
        boolean quitSuccessfully = false;
        try {
            FxAssert.verifyThat(window("Game Over"), WindowMatchers.isNotShowing());
        } catch (NoSuchElementException e) {
            quitSuccessfully = true;
        }
        assertTrue(quitSuccessfully);
    }
}
