package uta.cse3310;
import java.util.ArrayList;

public class Player{
    public int playerID;
    public String nickname;
    public int playerScore;
    public String playerColor;
 
    public String getName() {
        return nickname; 
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
        return color; 
    }
 
    public int getPlayerScore() {
        return score;
    }
}