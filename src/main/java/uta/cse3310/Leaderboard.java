package uta.cse3310;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.Map;
import java.util.HashMap;

public class leaderboardtest extends TestCase {
    public leaderboardtest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(leaderboardtest.class);
    }

    public void testScoreUpdatesAndRetrieval() {
      
        Leaderboard leaderboard = new Leaderboard();

        // Test updating scores
        leaderboard.updateScore(1, "PLayer1", 150, "Red");
        leaderboard.updateScore(2, "PLayer2", 200, "Blue");

        
        assertEquals(150, leaderboard.getScore("Player1"));
        assertEquals(200, leaderboard.getScore("PLayer2"));
        assertEquals(0, leaderboard.getScore("PLayer"));

        // Test getting all scores
        Map<String, Integer> allScores = leaderboard.getAllScores();
        assertEquals(2, allScores.size());
        assertTrue(allScores.containsKey("PLayer1"));
        assertTrue(allScores.containsKey("PLayer2"));
        assertEquals(Integer.valueOf(150), allScores.get("Player1"));
        assertEquals(Integer.valueOf(200), allScores.get("Player2"));

     
        System.out.println("----------------LEADERBOARD TESTING----------------");
        System.out.println("Scores in the leaderboard: ");
        for (Map.Entry<String, Integer> entry : allScores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("----------------TESTING COMPLETE-----------------");
    }
}
