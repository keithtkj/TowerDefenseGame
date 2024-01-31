import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
// Lizzie has to rename this to junit to get it to work instead of junit5 :P sorry
//import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.Before;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.TextMatchers;

public class M3Tests extends ApplicationTest {
    @Before
    public void setUpClass() throws Exception {
        ApplicationTest.launch(Welcome.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    //John's JUnits
    //makes sure buying a tower in hard mode displays the correct image
    @Test
    public void testHardTowerImage() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("John");
        clickOn("Start Game!");
        clickOn("Buy #3");
        lookup("file:resources/PinkLevel1.png");
    }

    //makes sure buting two towers leaves the 
    //player with the correct amount of money on easy mode
    @Test
    public void buyTwoTowersEasy() {
        clickOn("Play");
        clickOn("Name...").write("John");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Start Game!");
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $975", TextMatchers.hasText("Money: $975"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $950", TextMatchers.hasText("Money: $950"));
    }


    //Lizzie's JUnits
    //Tests that buying towers in Hard mode takes away correct amounts
    @Test
    public void buyHardTowerMoney() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Lizzie");
        clickOn("Start Game!");
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $300", TextMatchers.hasText("Money: $300"));
        clickOn("Buy #2");
        FxAssert.verifyThat("Money: $200", TextMatchers.hasText("Money: $200"));
        clickOn("Buy #3");
        FxAssert.verifyThat("Money: $100", TextMatchers.hasText("Money: $100"));
    }

    @Test
    //Tests that money in hard mode does not go below 0
    public void buyHardTowerNotEnoughMoney() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Lizzie");
        clickOn("Start Game!");
        clickOn("Buy #1");
        clickOn("Buy #2");
        clickOn("Buy #3");
        FxAssert.verifyThat("Money: $100", TextMatchers.hasText("Money: $100"));
        lookup("Money: $100");
        clickOn("Buy #2");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
        lookup("Money: $0");
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
        lookup("Money: $0");
    }

    //Cole's JUnits

    //#1 here
    //Makes Sure the Money is subtracted correctly for Easy
    @Test
    public void buyEasyTowerMoney() {
        clickOn("Play");
        clickOn("Name...").write("Cole");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Start Game!");
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $975", TextMatchers.hasText("Money: $975"));
        clickOn("Buy #2");
        FxAssert.verifyThat("Money: $950", TextMatchers.hasText("Money: $950"));
        clickOn("Buy #3");
        FxAssert.verifyThat("Money: $925", TextMatchers.hasText("Money: $925"));
    }
    //#2 here
    //Makes Sure the Money is subtracted correctly for Medium
    @Test
    public void buyMediumTowerMoney() {
        clickOn("Play");
        clickOn("Name...").write("Cole");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Start Game!");
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $550", TextMatchers.hasText("Money: $550"));
        clickOn("Buy #2");
        FxAssert.verifyThat("Money: $500", TextMatchers.hasText("Money: $500"));
        clickOn("Buy #3");
        FxAssert.verifyThat("Money: $450", TextMatchers.hasText("Money: $450"));
    }

    //Dom's JUnits
    @Test
    //tests that money in medium mode does not go below zero.
    public void buyMediumTowerNotEnoughMoney() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Dom");
        clickOn("Start Game!");
        for (int i = 1; i <= 12; i++) {
            clickOn("Buy #1");
        }
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
        clickOn("Buy #2");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
    }
    //#2 here
    @Test
    //Tests correct image for tower is displayed when bought
    public void testMediumTowerImage() {
        clickOn("Play");
        clickOn("Name...").write("Dom");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Start Game!");
        clickOn("Buy #2");
        lookup("file:resources/GreenLevel1.png");
    }


    //Keith's JUnits
    @Test
    //#1 here
    //Tests that money in easy mode does not go below 0
    public void buyEasyTowerNotEnoughMoney() {
        clickOn("Play");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Name...").write("Keith");
        clickOn("Start Game!");
        for (int i = 1; i <= 40; i++) {
            clickOn("Buy #1");
        }
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
        clickOn("Buy #2");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
        clickOn("Buy #1");
        FxAssert.verifyThat("Money: $0", TextMatchers.hasText("Money: $0"));
    }

    @Test
    //#2 here
    //Test that when the player buys 2 tower2's, they are left with
    //the correct amount of money on easy mode
    public void buyTwoTower2Easy() {
        clickOn("Play");
        clickOn("Name...").write("Keith");
        clickOn("Select Difficulty...");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("Start Game!");
        clickOn("Buy #1");
        lookup("file:resources/BlueLevel1.png");
    }

}
