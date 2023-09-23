/*****************************************************
* Author:	Connor Heard, TJ Bowman
* Date:		9/18/23
* Game of Life - Module 2 Live Coding
*****************************************************/

import java.io.*;
import java.util.Scanner;

class Gol {
	/**
	 * Game loop for Conway's Game of Life
	 * @param 		args - the command line arguments
	 */
    public static void main(String[] args) {
        // args[0] allows us to give input to the command line
        boolean [][] board = readFile(args[0]);
        printBoard(0, board);
        for (int gen = 1; gen < 10; gen++) {
            mySleep(1000);
            board = playGame(board);
            printBoard(gen, board);
        }
    }

    /**
     * Read an input file and return a Game of Life board
     * @param		fileName - the name of the input file
     * @return		a Game of Life board represented as a 2D array of booleans
     */
    public static boolean[][] readFile(String fileName) {
        boolean [][] board = null;
    	int i = 0, size;
        try {
            File inputFile = new File(fileName);
            Scanner fileReader = new Scanner(inputFile);
            String line;

            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                if (i == 0) {
                    size = line.length();
                    board = new boolean[size][size];
                }

                fillRow(board, line, i);
                i++;
            }
            fileReader.close();
        } catch (Exception e) {}

        return board;
    }

    /**
     * Given a board, line from the input file, and board row number,
     * fill in that line of the board
     * @param		board - a Game of Life board
     * @param		line - a single line from the input file
     * @param		row - a row number for the board
     */
    public static void fillRow(boolean[][] board, String line, int row) {
        for (int i = 0; i < line.length(); i++) {
            board[row][i] = (line.charAt(i) == '*') ?  true : false;
            // the complexity of this function is linear
        }
	}

	/**
	 * Display the current game board
	 * @param		gen - the current game generation number
	 * @param		board - the curent Game of Life board
	 */
    public static void printBoard(int gen, boolean[][] board) {
        //clears command prompt
        cls();

        System.out.println("Generation " + gen);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board [i].length; j++) {
                System.out.print((board[i][j]) ? '*' : ' ');
            }
            System.out.println();
        }
        System.out.println();
    }

	/**
	 * Given a cell on the board, return its number of neighbors
     * Assuming that no border cells are passed into function
	 * @param		board - a Game of Life board
	 * @param		row - a row number from the board
	 * @param		col - a column number from the board
	 * @return		the number of neighbors of the cell at row & col
	 */
	public static int countNeighbors(boolean[][] board, int row, int col) {
        int count = 0;

		for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i!= 0 || j!= 0) && board[row + i][col + j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Update the game board to the next generation
     * @param		board - the current Game of Life board
     * @return		the next generation Game of Life board
     */
    public static boolean[][] playGame(boolean[][] board) {
    	// create a new board
        boolean[][] newBoard = new boolean[board.length][board.length];

        // iterate through all cells that aren't on border
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board.length - 1; j ++) {
            // count the neighbors of the current cell (i, j)
                int neighbors = countNeighbors(board, i, j);

            // for a cell that is populated, if it has 2-3 neighbors, it survives
                if (board[i][j]) {
                    newBoard[i][j] = (neighbors == 2 || neighbors == 3);
                }

            // for a cell that is unpopulated, if it has 3 neighbors, it becomes populated
                else {
                    newBoard[i][j] = (neighbors == 3);
                }
            }
        }
            
        return newBoard;
    }

    /***********************************
    ********** HELPER METHODS **********
    ***********************************/

    /**
     * Clear the console (on Windows machines)
     */
    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch(Exception e) {}

    }

    /**
     * Halt program execution (sleep) for some number of milliseconds
     * @param		duration - the length of time in milliseconds to sleep
     */
    public static void mySleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch(Exception e) {}
    }
}