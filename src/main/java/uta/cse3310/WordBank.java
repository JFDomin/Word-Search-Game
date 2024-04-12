package uta.cse3310;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class WordBank {
    private int startPosition;
    private int endPosition;
    private boolean found;

    public static ArrayList<String> readFileIntoArray(String FileName){
        ArrayList<String>  words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FileName))){
            String line;
            while((line = reader.readLine()) != null){
                if(line.length() > 3)
                {
                    words.add(line.toLowerCase());
                }
            }
        }
        catch(Exception e){
            System.out.println("File not found");
        }
        return words;
    }
    public boolean markFound(){
        return true;
    }
    public void displayWordBank (){

    }
}