import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Because of the announcement, we did not put any UI unit tests in this file.
// The tests we created are to make sure that all of the gampelay elements were initialized properly
// and that the difficulty enumeration works as planned.
// We also wrote tests to make sure that the desired fields can be updated with new data.
// We chose to test these because a failure in any of these fields would make running the game very
// difficult in the future.
public class M2Tests {
    //John's JUnit
    // This test makes sure that the health is initialized properly for each of the difficulties
    @Test
    public void testTowerInit() {
        Tower towerEasy = new Tower(Difficulty.EASY);
        Tower towerMed = new Tower(Difficulty.MEDIUM);
        Tower towerHard = new Tower(Difficulty.HARD);

        assertEquals(towerEasy.getHealth(), 500);
        assertEquals(towerMed.getHealth(), 400);
        assertEquals(towerHard.getHealth(), 200);
    }

    //Cole's JUnit
    // This test makes sure that the Player constructor works as 
    // expected and that the name field is initialized properly
    @Test
    public void testPlayerName() {
        Player player1 = new Player(Difficulty.EASY, "Cole");
        Player player2 = new Player(Difficulty.EASY, "Dom");
        Player player3 = new Player(Difficulty.EASY, "John");
        Player player4 = new Player(Difficulty.EASY, "Keith");
        Player player5 = new Player(Difficulty.EASY, "Lizzie");

        assertEquals(player1.getName(), "Cole");
        assertEquals(player2.getName(), "Dom");
        assertEquals(player3.getName(), "John");
        assertEquals(player4.getName(), "Keith");
        assertEquals(player5.getName(), "Lizzie");
    }
    // Dom Fernando's JUnit
    // This test ensures that when the difficulty is set to EASY, 
    // the correct amount of money is given to the player
    @Test
    public void testEasy() {
        Player player6 = new Player(Difficulty.EASY, "Kyle");
        Player player7 = new Player(Difficulty.EASY, "Jen");


        assertEquals(player6.getMoney(), 1000);
        assertEquals(player7.getMoney(), 1000);
        player6.setMoney(600);
        assertEquals(player6.getMoney(), 600);
        player7.setMoney(0);
        assertEquals(player7.getMoney(), 0);
    }

    // Keith's JUnit
    // This test ensures that when the difficulty is set to MEDIUM,
    // the correct amount of money is given to the player It also tests 
    // the setMoney() method and the constructor for the Player object
    @Test
    public void testMedium() {
        Player player = new Player(Difficulty.MEDIUM, "player");
        assertEquals(player.getMoney(), 600);
        assertEquals(player.getName(), "player");
        player.setMoney(1000);
        assertEquals(player.getMoney(), 1000);

        Player player2 = new Player(Difficulty.MEDIUM, "player2");
        assertEquals(player2.getName(), "player2");
        assertEquals(player2.getMoney(), 600);
    }

    // Lizzie's JUnit
    // This test ensures that when the difficulty is set to HARD,
    // the correct amount of money is given to the player It also tests 
    // the setMoney() method and the constructor for the Player object.
    @Test
    public void testHard() {
        Player player1 = new Player(Difficulty.HARD, "player1");
        assertEquals(player1.getMoney(), 400);
        assertEquals(player1.getName(), "player1");
        player1.setMoney(1000);
        assertEquals(player1.getMoney(), 1000);

        Player player2 = new Player(Difficulty.HARD, "player2");
        assertEquals(player2.getName(), "player2");
        assertEquals(player2.getMoney(), 400);
    }
}
