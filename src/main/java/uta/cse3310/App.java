
// This is example code provided to CSE3310 Fall 2022
// You are free to use as is, or changed, any of the code provided

// Please comply with the licensing requirements for the
// open source packages being used.

// This code is based upon, and derived from the this repository
//            https:/thub.com/TooTallNate/Java-WebSocket/tree/master/src/main/example

// http server include is a GPL licensed package from
//            http://www.freeutils.net/source/jlhttp/

/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package uta.cse3310;
import java.io.*; 
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.net.Socket;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.time.Instant;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends WebSocketServer implements Runnable{

  // All games currently underway on this server are stored in
  // the vector ActiveGames
  private Vector<WordSearchGame> ActiveGames = new Vector<WordSearchGame>();
   private WordGrid wordGrid;
  public static ArrayList<App> clientHandlers = new ArrayList<>();
  private WebSocket websocket;
  private Socket socket;
  private BufferedReader bufferedReader;
  private BufferedWriter bufferedWriter;
  private String clientUsername;


  private WordGrid Grid;

  int numPlayers = 0;
  int numPlayersReady = 0;
  ArrayList<Player> players = new ArrayList<>();
  
  //WordSearchGame[] ActiveGames = new WordSearchGame[5];
  private int GameId = 1;

  private int connectionId = 0;

  private Instant startTime;

  public App(int port) {
    super(new InetSocketAddress(port));
  }

  public App(InetSocketAddress address) {
    super(address);
  }

  public App(int port, Draft_6455 draft) {
    super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
  }
  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake) {

    connectionId++;
    System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
     ServerEvent E = new ServerEvent();
     WordSearchGame G = null;
    
     for(WordSearchGame i: ActiveGames){
      if(i.player == uta.cse3310.PlayerType.PLAYER1 || i.player == uta.cse3310.PlayerType.PLAYER2 || i.player == uta.cse3310.PlayerType.PLAYER3){
        G = i;
        System.out.println("Match found");
      }
     }
      
      //no matches? create new game
      if(G == null){
        if(ActiveGames.size() <= 5){
          G = new WordSearchGame();
          G.gameID = GameId;
          GameId++;
          G.player = PlayerType.PLAYER1;
          G.numPlayers++;
          ActiveGames.add(G);
          System.out.println("creating new game");
          G.startGame();
          System.out.println("G.players is " + G.player);
          Gson gson = new Gson();
          Character[][] wordGrid = G.getwordgrid();
          String gridJson = gson.toJson(wordGrid);
          conn.send("{\"type\": \"wordGrid\",\"data\": "+ gridJson + "}");
        }
      }
      else{
          System.out.println("not a new game");
          switch(G.numPlayers){
            case 1:
              G.player = PlayerType.PLAYER2;
              G.numPlayers++;
              break;
            case 2:
              G.player = PlayerType.PLAYER3;
              G.numPlayers++;
              break;
            case 3:
              G.player = PlayerType.PLAYER4;
              G.numPlayers++;
              break;
          }
          Gson gson = new Gson();
          Character[][] wordGrid = G.getwordgrid();
          String gridJson = gson.toJson(wordGrid);
          conn.send("{\"type\": \"wordGrid\",\"data\": "+ gridJson + "}");
      }
      E.YouAre = G.player;
      E.GameId = G.gameID;
      System.out.println(E.YouAre + " " + E.GameId);
      // allows the websocket to give us the Game when a message arrives..
    // it stores a pointer to G, and will give that pointer back to us
    // when we ask for it
      conn.setAttachment(G);

     Gson gson = new Gson();
     String jsonString = gson.toJson(E);
     conn.send(jsonString);
     // The state of the game has changed, so lets send it to everyone
    jsonString = gson.toJson(G);
    broadcast(jsonString);
    // // search for a game needing a player
     //WordSearchGame G = new WordSearchGame();
     //WordGrid grid = new Word();
    conn.setAttachment(G);
       System.out.println(" creating a new Game");
        broadcast(jsonString);
        //G.startGame();
     conn.setAttachment(G);
   // System.out
    //     .println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + connectionId + " "
     //       + escape(jsonString));
   //  System.out
   //      .println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
        // broadcast(jsonString);
        // G.startGame();
        // G.getwordgrid();
        // Character[][] wordGrid = G.getwordgrid();
        //ArrayList<Character> wordGrid = G.getwordgrid();
        // String gridJson = gson.toJson(wordGrid);
        // conn.send("{\"type\": \"wordGrid\",\"data\": "+ gridJson + "}");
        //System.out.println(gridJson);
  }
  
  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    System.out.println(conn + " has closed");
    // Retrieve the game tied to the websocket connection
     WordSearchGame G = conn.getAttachment();
     G = null;
  }

  @Override
  public void onMessage(WebSocket conn, String message) {
    
    System.out.println(conn+" "+ message);

    // // Bring in the data from the webpage
    // // A UserEvent is all that is allowed at this point
     GsonBuilder builder = new GsonBuilder();
     Gson gson = builder.create();
     UserEvent U = gson.fromJson(message, UserEvent.class);
     

     if(U.button.equals("join")){
      Player player1 = new Player(U.nickname);
      players.add(player1);
      numPlayers++;
     }
     else if(U.button.equals("readyUp")){
      for(Player p : players){
        if(p.nickname.equals(U.nickname)){
          if(p.isReady){
            p.isReady = false;
            numPlayersReady--;
          }
          else{
            p.isReady = true;
            numPlayersReady++;
          }
        }
        System.out.println(p.nickname + " " + p.isReady);
      }
      }
      else if(U.button.equals("startGame")){
        System.out.println(numPlayers + " ready: " + numPlayersReady);
        //this is where a checklobby would be called to see number of ready players
        //if >=2 start the game 
        //allPlayerReady will return true if every player is ready and then we can call startGame()
        if(numPlayersReady >=2 && numPlayers == numPlayersReady/*allPlayersReady()*/){
            System.out.println("Enough players to start game");
            //implement
        }
     }
     
    // Update the running time

    // Get our Game Object
     //Game G = conn.getAttachment();
     //G.Update(E);

    // send out the game state every time
    // to everyone
     //String jsonString;
     //jsonString = gson.toJson(G);


     //broadcast(jsonString);
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message) {
    System.out.println(conn + ": " + message);
    // GsonBuilder builder = new GsonBuilder();
    // Gson gson = builder.create();
    // UserEvent U = gson.fromJson(message, UserEvent.class);
    // System.out.println(U.Button);

    // // Get our Game Object
    // Game G = conn.getAttachment();
    // G.Update(U);

    // // send out the game state every time
    // // to everyone
    // String jsonString;
    // jsonString = gson.toJson(G);

    // System.out.println(jsonString);
    // broadcast(jsonString);
  }

  @Override
  public void onError(WebSocket conn, Exception ex) {
    ex.printStackTrace();
    if (conn != null) {
      // some errors like port binding failed may not be assignable to a specific
      // websocket
    }
  }

  @Override
  public void onStart() {
    setConnectionLostTimeout(0);
    startTime = Instant.now();
  }

  private String escape(String S) {
    // turns " into \"
    String retval = new String();
    // this routine is very slow.
    // but it is not called very often
    for (int i = 0; i < S.length(); i++) {
      Character ch = S.charAt(i);
      if (ch == '\"') {
        retval = retval + '\\';
      }
      retval = retval + ch;
    }
    return retval;
  }

  public static void main(String[] args) {

    // Set up the http server
    int port = 9016;
    HttpServer H = new HttpServer(port, "./html");
    H.start();
    System.out.println("http Server started on port: " + port);

    // create and start the websocket server

    port = 9116;
    App A = new App(port);
    A.setReuseAddr(true);
    A.start();
    System.out.println("websocket Server started on port: " + port);

    



  }
}

// professor added this part on his repo to make the websocket port 100 greater than the http port
// port = 9180;
// String WSPort = System.getenv("WEBSOCKET_PORT");
// if (WSPort!=null) {
//   port = Integer.valueOf(WSPort);