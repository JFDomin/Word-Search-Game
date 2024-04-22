package uta.cse3310;
import java.util.ArrayList;

public class Player{
    public int playerID;
    public String nickname;
    PlayerType playerType;
    public int playerScore;
    public String playerColor;
    boolean isReady;
    
    public Player(String nickname) {
        this.nickname = nickname;
        this.isReady = false;
    }
 
    public String getName() {
        return nickname; 
    }
    public boolean uniqueName(ArrayList<String> names){
        for(String s: names){
            if(s.equals(nickname)){
                return false;
            }
        }
        return true;
    }
    
    public boolean joinGame() {
        return true; 
    }
 
    public boolean leaveGame() {
        return true; 
    }
     
    public void chooseStartLetter() {
 
    }
 
    public void chooseEndLetter() {
 
    }
 
    public void sendChat() {
 
    }
 
    public String assignColor() {
        return playerColor; 
    }
 
    public int getPlayerScore() {
        return playerScore;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

    public boolean isReady() {
        return isReady;
    }
    public void setPlayerScore(int newScore){
        this.playerScore = newScore;
    }
}