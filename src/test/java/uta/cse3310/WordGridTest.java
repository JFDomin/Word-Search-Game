package uta.cse3310;
import java.util.ArrayList;

public class WordGridTest{

    public void testGridMethods(){
        ArrayList<String> words = WordBank.readFileIntoArray("src/main/java/uta/cse3310/filtered_word 1.txt");
        ArrayList<String> found = new ArrayList<>();
        ArrayList<String> wordBank = WordBank.wordBank(found, words);

        WordGrid grid = new WordGrid(50, wordBank);
        long startTime = System.currentTimeMillis();
        //test to generate a 50 x 50 grid in less than 1 second with density >= 0.67
        grid.generateGrid(wordBank);
        long endTime = System.currentTimeMillis();

        //print the time it takes for a grid to be generated in ms
        System.out.println("time(ms): " + (endTime- startTime));
        //print the grid 
        grid.printGrid();
        //resetGrid();
        grid.resetGrid();
        //now that a grid has been made in < 1 second and can test each placement 
        //for the purposes of this test the grid will now be smaller (15x15) to easily see the word placement
        WordGrid grid2 = new WordGrid(15, wordBank);
        
        //to test each placement, we can choose a placement and start position then fill the grid with random characters
        System.out.println();
        int[] start = new int[]{0,0};
        String string = "VERTICAL";
        grid2.placeVertical(string,start, string.length());
        grid2.fillGrid();
        grid2.printGrid();
        grid2.resetGrid();
        System.out.println();

        //TESTING DIAGONALUP PLACEMENT WORD AT BOTTOM LEFT
        string = "DIAGONAL";
        start[0] = 14;
        start[1] = 0;
        grid2.placeDiagonalUp(string,start,string.length());
        grid2.fillGrid();
        grid2.printGrid();
        grid2.resetGrid();
        System.out.println();

        // TESTING DIAGONAL DOWN PLACEMENT WORD STARTING AT TOP RIGHT
        start[0] = 0;
        start[1] = 14;
        grid2.placeDiagonalDown(string,start,string.length());
        grid2.fillGrid();
        grid2.printGrid();
        grid2.resetGrid();
        System.out.println();

        // TESTING  PLACEMENT WORD STARTING AT TOP LEFT
        string = "HORIZONTAL";
        start[0] = 0;
        start[1] = 0;
        grid2.placeHorizontal(string,start,string.length());
        grid2.fillGrid();
        grid2.printGrid();
        grid2.resetGrid();
        System.out.println();

        // TESTING REVERSE PLACEMENT WORD ON FIRST ROW
        string = "REVERSE";
        start[0] = 0;
        start[1] = 10;
        grid2.placeReverse(string,start,string.length());
        grid2.fillGrid();
        grid2.printGrid();
        grid2.resetGrid();
        System.out.println();

        // TESTING METHOD PLACE
        // radomly selects one of the 4 placements (diagonalUp,diagonalDown,horizontal,vertical)
        //if the word does not fit in the random orientation, it will not be placed
        //normally the "start" position is filled with 2 random ints so the placement position is anywhere but here we will start at a fixed location
        //possible that it may take various runs to finally see "RADNOM" placed 
        string = "RANDOM";
        start[0] = 0;
        start[1] = 0;
        grid2.place(string,start,string.length());
        grid2.fillGrid();
        grid2.printGrid();
        grid2.resetGrid();
        System.out.println();
    }
    
}