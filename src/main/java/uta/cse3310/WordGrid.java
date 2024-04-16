package uta.cse3310;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class WordGrid {
    int size;
    Character [][] grid = new Character[size][size];
    double validCharCount = 0;
    double totalCharCount = 0;
    double density = 0;
    ArrayList<String> usedWords = new ArrayList<>();
    ArrayList<String> wordBank = new ArrayList<>();
    int[] startPos = new int[]{0,0};
    Random rand = new Random();
    ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));

    public WordGrid(int size, ArrayList<String> wordBank){
        grid = new Character[size][size];
        rand = new Random();
        this.size = size;
        this.wordBank = wordBank;
    }

    public void generateGrid(ArrayList<String> word_bank){
        for(String word: word_bank){
            int length = word.length();
            startPos[0] = rand.nextInt(size);
            startPos[1] = rand.nextInt(size);
            place(word, startPos, length);
        }
        fillGrid();
        totalCharCount += validCharCount;
        density = (validCharCount)/(totalCharCount);
        System.out.println(density);
        while(density < 0.67){
            resetGrid();
            generateGrid(word_bank);
        }
    }
    public Character[][] getgrid(){       
        return grid;
    }
    public void place(String word,int[] start, int length){
        int allocation = rand.nextInt(5);
        switch(allocation){
            case 1: 
            placeHorizontal(word,start,length);
            break;
            case 2:
            placeVertical(word,start,length);
            break;
            case 3:
            placeDiagonalDown(word,start,length);
            break;
            case 4:
            placeDiagonalUp(word,start,length);
            break;
            case 5:
            placeReverse(word,start,length);
            break;
        }
    }

    public void placeHorizontal(String word, int[] start, int length){
        int row = start[0];
        int col = start[1];
        boolean isEmpty = true;
        if(col + length < size){
            for(int i = 0; i < length; i++){
                if(grid[row][col] != null){
                    isEmpty = false;
                }
                col++;
            }
            row = start[0];
            col = start[1];
            if(isEmpty){
                for(int i = 0; i < length; i++){
                    grid[row][col] = word.charAt(i);
                    col++;
                    validCharCount++;
                }
                usedWords.add(word);
            }
        }
    }
    public void placeVertical(String word, int[] start, int length){
        int row = start[0];
        int col = start[1];
        boolean isEmpty = true;
        if(row + length < size) {
            for(int i = 0; i < length; i++){
                if(grid[row][col] != null){
                    isEmpty = false;
                }
                row++;
            }
            if(isEmpty){
                row = start[0];
                col = start[1];
                for(int i = 0; i < length; i++){
                    grid[row][col] = word.charAt(i);
                    row++;
                    validCharCount++;
                }
                usedWords.add(word);
            }
        }
    }
    public void placeDiagonalUp(String word, int[] start, int length){
        int row = start[0];
        int col = start[1];
        boolean isEmpty = true;
        if(row - length >= 0 && col + length < size){
            for(int i = 0; i < length; i++){
                if (grid[row][col] != null){
                    isEmpty = false;
                }
                row--;
                col++;
            }
            if(isEmpty){
                row = start[0];
                col = start[1];
                for(int i = 0; i < length; i++){
                    grid[row][col] = word.charAt(i);
                    row--;
                    col++;
                    validCharCount++;
                }
                usedWords.add(word);
            }
        }
    }

    public void placeDiagonalDown(String word, int[] start, int length){
        int row = start[0];
        int col = start[1];
        boolean isEmpty = true;
        if(row + length < size && col - length >= 0){
            for(int i = 0; i < length; i++){
                if (grid[row][col] != null){
                    isEmpty = false;
                }
                row++;
                col--;
            }
            if(isEmpty){
                row = start[0];
                col = start[1];
                for(int i = 0; i < length; i++){
                    grid[row][col] = word.charAt(i);
                    row++;
                    col--;
                    validCharCount++;
                }
                usedWords.add(word);
            }
        }
    }
    public void placeReverse(String word, int[] start, int length){
        int row = start[0];
        int col = start[1];
        boolean isEmpty = true;
        if(col - length  >= 0){
            for(int i = 0; i < length; i++){
                if (grid[row][col] != null){
                    isEmpty = false;
                }
                col--;
            }
            if(isEmpty){
                row = start[0]; col = start[1];
                for(int i = 0; i < length; i++){
                    grid[row][col] = word.charAt(i);
                    col--;
                    validCharCount++;
                }
            }
            usedWords.add(word);
        }
    }

    public void fillGrid(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j] == null){
                    grid[i][j] = alphabet.get(rand.nextInt(26));
                    totalCharCount++;
                }
            }
        }
    }

    public void printGrid(){
        for(Character[] row : grid){
            for(Character c : row ){
                System.out.print(c + " ");
            }
            System.out.println();
        } 
    }

    public void resetGrid(){
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j++){
                grid[i][j] = null;
            }
        }
        totalCharCount = 0;
        validCharCount = 0;
        usedWords.clear();
    }
}