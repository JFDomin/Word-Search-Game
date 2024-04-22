package uta.cse3310;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Chat {
    private ArrayList<String> messages;

    public Chat() {
        messages = new ArrayList<>();
    }

    // Send a message to the chat
    public void sendMessage(String playerName, String message) {
        if (message != null && !message.trim().isEmpty()) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            messages.add(timestamp + " " + message);
            messages.add(playerName + ": " + message);
        }
    }

    // Retrieve all messages from the chat
    public ArrayList<String> receiveMessages() {
        return new ArrayList<>(messages);
    }

    // Display all messages in the chat
    public void displayMessages() {
        for (String message : messages) {
            System.out.println(message);
        }
    }
}