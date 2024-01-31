import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
// Lizzie has to rename this to junit to get it to work instead of junit5 :P sorry
//import org.testfx.framework.junit.ApplicationTest;

import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.TextMatchers;
import org.testfx.util.WaitForAsyncUtils;

// The tests we wrote were to ensure that all of the new game elements introduced in the
// M4 milestone worked as intended such as the game over screen, the start game button, etc
// We chose to test these because they are critical elements of a tower defense game, and failing
// any of these aspects would prevent us from 
// developing a more complex tower defense game which is our
// goal in future iterations, as these elements set the foundation for more complex ones in future


public class M4Tests extends ApplicationTest {
    @Before
    public void setUpClass() throws Exception {
        launch(Welcome.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }


    //Cole's Tests
    //This test checks to see if the game over screen is appearing
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
        WaitForAsyncUtils.sleep(22, TimeUnit.SECONDS);
        FxAssert.verifyThat(window("Game Over"), WindowMatchers.isShowing());
    }

    //This test is to check if the play again button goes back to the welcome screen
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
        WaitForAsyncUtils.sleep(22, TimeUnit.SECONDS);
        clickOn("Play Again");
        FxAssert.verifyThat(window("The 56's Tower Defense Game"), WindowMatchers.isShowing());
    }

    //Lizzie's tests
    // This test is to check that the quit game button quits the game
    @Test
    public void quitGame() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Lizzie");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(22, TimeUnit.SECONDS);
        clickOn("Quit Game");
        boolean success = false;
        try {
            FxAssert.verifyThat(window("Game Over"), WindowMatchers.isNotShowing());
        } catch (NoSuchElementException e) {
            success = true;
        }
        assertTrue(success);
    }

    //This test makes sure that when the enemies reach the tower it detracts health
    @Test
    public void enemyDoesDamage() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Lizzie");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(21, TimeUnit.SECONDS);
        FxAssert.verifyThat("Health: 40", TextMatchers.hasText("Health: 40"));
    }

    //John's JUnit Tests
    //both tests ensure that previous functionality was not lost with the added code

    //first test ensures that money decrements as expected when buying a tower
    @Test
    public void testMoneyDecrement() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("John");
        clickOn("Start Game!");
        FxAssert.verifyThat("Money: $400", TextMatchers.hasText("Money: $400"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $300", TextMatchers.hasText("Money: $300"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $200", TextMatchers.hasText("Money: $200"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $100", TextMatchers.hasText("Money: $100"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
        
    }
    //second test ensures that you cannot go below $0
    @Test
    public void testNotEnoughMoney() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("John");
        clickOn("Start Game!");

        for (int i = 0; i < 4; i++) {
            clickOn("Buy #1");
        }

        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
    }
    
    // Keith's Tests
    // Test ensures no enemies appear until start button 
    //is clicked - tower health should remain the same
    @Test
    public void noEnemies() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Keith");
        clickOn("Start Game!");
        WaitForAsyncUtils.sleep(20, TimeUnit.SECONDS);
        FxAssert.verifyThat("Health: 200", TextMatchers.hasText("Health: 200"));
    }

    // Test ensures tower health does not go below 0
    @Test
    public void validTowerHealth() {
        Tower testEasy = new Tower(Difficulty.EASY);
        Tower testMedium = new Tower(Difficulty.MEDIUM);
        Tower testHard = new Tower(Difficulty.HARD);
        for (int i = 0; i < 1000; i++) {
            testEasy.reduceHealth(100);
            testMedium.reduceHealth(100);
            testHard.reduceHealth(100);
        }
        assertEquals(0, testEasy.getHealth());
        assertEquals(0, testMedium.getHealth());
        assertEquals(0, testHard.getHealth());
    }
    //Dom's Tests
    //This test is to check if the play again button goes back to the welcome screen
    @Test
    public void testGameOverScreenAfterPlayAgain() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Dom");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(22, TimeUnit.SECONDS);
        clickOn("Play Again");
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Cole");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(22, TimeUnit.SECONDS);
        FxAssert.verifyThat(window("Game Over"), WindowMatchers.isShowing());
    }

    //This tests being able to buy a tower after playing again
    @Test
    public void testBuyTowersAfterPlayAgain() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Dom");
        clickOn("Start Game!");
        clickOn("Start Game");
        WaitForAsyncUtils.sleep(22, TimeUnit.SECONDS);
        clickOn("Play Again");
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Dom");
        clickOn("Start Game!");
        FxAssert.verifyThat("Money: $400", TextMatchers.hasText("Money: $400"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $300", TextMatchers.hasText("Money: $300"));
    }
}
    
