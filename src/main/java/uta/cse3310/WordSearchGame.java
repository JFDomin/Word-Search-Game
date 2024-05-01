package uta.cse3310;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import org.java_websocket.WebSocket;

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
    WordGrid grid = new WordGrid(35 ,wordBank);
    ArrayList<WordGrid> selectGrid = new ArrayList<>();
    int numPlayersReady = 0;
    boolean isStarted = false;
    ArrayList<int[][]> foundCoords = new ArrayList<>();
    transient ArrayList<WebSocket> conns = new ArrayList<>();
    ArrayList<String> usedColors = new ArrayList<>();
    public enum GameState {
        NOT_STARTED,
        IN_PROGRESS,
        ENDED 
    }
    public void multiGrids(){
        for(int i = 0; i < 7; i++){
            WordGrid newGrid = new WordGrid(35,wordBank);
            selectGrid.add(newGrid);
        }
    }
    
    public void startGame() {
        grid.generateGrid(wordBank);
        while(!grid.checkGrid(wordBank)){
            grid.generateGrid(wordBank);
        }
    }
    public void startGame(int gridNo){
        multiGrids();
        grid = selectGrid.get(gridNo);
        grid.generateGrid(wordBank);
        // grid.checkGrid(wordBank);
    }
    public Character[][] getwordgrid(){ 
        return grid.getgrid();
    }
    //check to see if the coordinates have been found before returns true if they havent been found
    //ie the word has alreayd been awarded
    public boolean checkWordsFound(int[][] button){
        if(foundCoords.size() == 0){
            return true;
        }
        for(int i = 0; i < foundCoords.size(); i++){
            int[][] coords = foundCoords.get(i);
            if(button[0][0] == coords[0][0] && button[0][1] == coords[0][1] && button[1][0] == coords[1][0] && button[1][1] == coords[1][1]){
                return false;
            }
        }
        return true;
    }
    public String orientation(int[][] coordinates){
        int rowDiff = coordinates[1][0] - coordinates[0][0];
        int colDiff = coordinates[1][1] - coordinates[0][1];
        if(colDiff == 0 && rowDiff > 0){
            return "vertical";
        }
        else if(rowDiff == 0 && colDiff > 0 ){
            return "horizontal";
        }
        else if(rowDiff == 0 && colDiff < 0){
            return "reverse";
        }
        else if(colDiff < 0 && rowDiff > 0){
            return "DiagDown";
        }
        else if(colDiff > 0 && rowDiff < 0){
            return "DiagUp";
        }
        else{
            return "VerticalUp";
        }
    }
    public boolean checkAllReady(){
        for(Player p: players){
            if(p.isReady == false){
                return false;
            }
        }
        return true;
    }


}
