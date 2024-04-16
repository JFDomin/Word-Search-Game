package uta.cse3310;
import java.util.ArrayList;

public class WordBankTest {
    //////////////////////////////////////////////////////////////////////////
    // These are unit tests, to check methods in the WordBank class.
    //////////////////////////////////////////////////////////////////////////

    public void testReadFile(){
        //this method will test the reading of a file name into an ArrayList 
        //a bad file name or path will throw an exception, otherwise an ArrayList<String> is returned
        WordBank wordMethods = new WordBank();
        ArrayList<String> words = WordBank.readFileIntoArray("src/main/java/uta/cse3310/filtered_word 1.txt");

        //we now have a list of all the words from the file, running the method wordBank() with a list of already found words (at this point none) will return a word bank in randomized order 
        ArrayList<String> found = new ArrayList<>();
        ArrayList<String> wordBank = WordBank.wordBank(found,words);
        //the wordBank shall now be randomized order of the words read in from the file, and the found list is updated to have all of the words added
        //we can print the wordBank and see that it has worked
        wordMethods.displayWordBank(wordBank);
        //the same can be done for the found list which in this case will return the same 
        //both methods are working correctly so now to show how using a bad filename/path is handled 

        words.clear(); //clear words list
        //testing bad file name
        words = WordBank.readFileIntoArray("src/main/java/uta/cse3310/filtered_word.txt");
        //this prints "File not found based on the error"
        //testing bad path with correct file name (same outcome)
        words = WordBank.readFileIntoArray("test/main/java/uta/cse3310/filtered_word 1.txt");

        //prints file not found for both cases
    }   
}
