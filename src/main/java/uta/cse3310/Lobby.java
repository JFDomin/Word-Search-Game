package uta.cse3310;

public class Lobby {
    private int numPlayers;
    private int playersWaiting;
    private boolean ready;

    public Lobby() {
        numPlayers = 0;
        playersWaiting = 0;
        ready = false;
    }

    public void addPlayer() {
        numPlayers++;
        if (!ready) {
            playersWaiting++;
        }
    }

    public void checkLobby() {
        System.out.println("Number of Players: " + numPlayers);
        System.out.println("Players Waiting: " + playersWaiting);
        System.out.println("Lobby Ready: " + ready);
    }

    public void generateLobby() {
       
    }

    public void removePlayer() {
        numPlayers--;
        if (!ready) {
            playersWaiting--;
        }
    }

    public void joinGame() {
       
    }

    public void exitGame() {
       
    }

    public void setPlayerReady() {
        ready = true;
    }
}
