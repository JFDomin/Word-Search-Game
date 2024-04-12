package uta.cse3310;
 
import java.util.List;
 
public class WordGrid {
    
    private static final int DEFAULT_GRID_SIZE = 50;
    private char[][] grid; // Assuming a 2D array of characters for the grid
    private List<String> wordList; // A list to hold words
    private int gridSize;
    private Random random = new Random();

    // Direction vectors for word placement
    private static final int[][] DIRS = {
        {1, 0},   // Down
        {0, 1},   // Right
        {1, 1},   // Diagonal down-right
        {1, -1},  // Diagonal down-left
        {-1, 0},  // Up
        {0, -1},  // Left
        {-1, -1}, // Diagonal up-left
        {-1, 1}   // Diagonal up-right
    };
 
    // Default constructor initializes a 50x50 grid
    public WordGrid() {
        this(DEFAULT_GRID_SIZE); }

     // Direction vectors defining movement in the grid for word placement:
    public WordGrid(int size) {
        this.gridSize = size;
        this.grid = new char[size][size]; // Initializing the grid to the specified size
        this.wordList = new ArrayList<>();
        clearGrid();
    }

    private void clearGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = '.'; // Reset the grid cell to the default empty state
             }
        }
    }
 }
 
    // Generates the grid with words and random letters
    public void generateGrid() {
        // Implementation will be added later
    }
 
    // Checks if the current grid configuration is valid
    public void checkGrid() {
        // Implementation added later
    }
 
    // Reveals the starting letter of a word on the grid
    public void revealStartLetter() {
        // Implementation  added later
    }
 
    // Places a word on the grid
    public void placeWord(String word) {
        // Implementation added later
    }
 
    // Checks if the placement of a word on the grid is valid
    public boolean checkPlacement(String word, int startX, int startY, String direction) {
        // Implementation added later
        return false;
    }
 
    // Checks if a word is found on the grid
    public boolean checkWordFound(String word) {
        // Implementation added later
        return false;
    }
 
    // Highlights a word on the grid
    public void highlightWord(String word) {
        // Implementation to be added later
    }
 
    // Awards points for a word found
    public int awardPoints(String word) {
        // Implementation to be added later
        return 0;
    }
 
    // Getters and setters
    public char[][] getGrid() {
        return grid;
    }
 
    public void setGrid(char[][] grid) {
        this.grid = grid;
    }
 
    public List<String> getWordList() {
        return wordList;
    }
 
    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }
 
    public int getGridSize() {
        return gridSize;
    }
 
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
 
	// add more methods if necessary
 
}