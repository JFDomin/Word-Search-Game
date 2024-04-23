package uta.cse3310;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordSearchGame{
    public int gameID;
    public ArrayList<Player> players = new ArrayList<>();
    PlayerType player;
    public double gridTimer;
    public double gameTimer;
   public GameState gameState;
   public Map<Player, Integer> scores;
   public int maxPlayers;
   public List<String> currentWords;
   int numPlayers = 0;
    ArrayList<String> words = WordBank.readFileIntoArray("src/main/java/uta/cse3310/filtered_word 1.txt");
    ArrayList<String> found = new ArrayList<>();
    ArrayList<String> wordBank = WordBank.wordBank(found, words);
    WordGrid grid = new WordGrid(35,wordBank);
    int numPlayersReady = 0;
    boolean isStarted = false;

    public enum GameState {
        NOT_STARTED,
        IN_PROGRESS,
        ENDED 
    }

    
    public void startGame() {
        grid.generateGrid(wordBank);
    }
    public Character[][] getwordgrid(){ 
        //WordGrid grid = new WordGrid(50,wordBank);      
        return grid.getgrid();
    }
    public boolean checkAllReady(){
        for(Player p: players){
            if(p.isReady == false){
                return false;
            }
        }
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
    // public void addPlayer(Player player){
    //     players
    // }

}
