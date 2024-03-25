package uta.cse3310;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordSearchGame{
    public int gameID;
    public ArrayList<Player> players;
    public double gridTimer;
    public double gameTimer;
   public GameState gameState;
   public Map<Player, Integer> scores;
   public int maxPlayers;
   public List<String> currentWords;
   
    public enum GameState {
        NOT_STARTED,
        IN_PROGRESS,
        ENDED 
    }
    
    
    public boolean startGame() {
        return true;
    }
    
    public boolean endGame() {
        return true;
    }
 
    public void updateGameScores() {
 
    }
 
    public boolean selectWord() {
        return true;
    }
    public int awardWord(){
        return 0; 
}
    public boolean validateWord() {
        return true;
    }
    public void updateGameState()
    {
    }

public void displayPlayerStats()
    {
    }

}
