package uta.cse3310;

public class Player{
    public int playerID;
    public string nickname;
    public int playerScore;
    public string playerColor;
 
    public string getName() {
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
 
    public string assignColor() {
        return color; 
    }
 
    public int getPlayerScore() {
        return score;
    }
}