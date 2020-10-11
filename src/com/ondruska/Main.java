package com.ondruska;

public class Main {

    public static void main(String[] args) {
        int[][] board = {
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}
        };

        printBoard(board);
        System.out.println("====================");

        if (solve(board)) {
            System.out.println("sudoku solved with backtracking and recursion: ");
            printBoard(board);
        } else {
            System.out.println("Sudoku unsolved");
            printBoard(board);
        }

    }

    /**
     * Method used to check, if some number n can be inserted into x and y position on the sudoku board
     *
     * @param x
     * @param y
     * @param n
     * @return
     */
    public static boolean possible(int[][] board, int x, int y, int n) {
        //if any number in the same line y is equal to our n, then return false
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == n) return false;
        }
        //if any number in the same column x is equal to our n, return false
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == n) return false;
        }

        //x0 and y0 are used to set position to the start of every square in sudoku board
        int x0 = x - x % 3;
        int y0 = y - y % 3;
        //if any number in sudoku square is the same as our n, return false
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[x0 + i][y0 + j] == n) {
                    return false;
                }
            }
        }
        //if we got here, it means that n can be inserted, return true
        return true;
    }

    /**
     * Method to solve a sudoku using recursion
     *
     * @param board
     * @return
     */
    public static boolean solve(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //if the cell is empty
                if (board[i][j] == 0) {
                    //we try every possible number(1-9)
                    for (int k = 1; k < 10; k++) {
                        //if the number is valid, we add it to the board
                        if (possible(board, i, j, k)) {
                            board[i][j] = k;
                            if (solve(board)) { // we start backtracking recursively
                                return true;
                            } else {
                                board[i][j] = 0; //if its not a solution, we empty the cell and continue
                            }
                        }
                    }
                    return false; //could not find any possible number for that position
                }
            }
        }
        //sudoku solved
        return true;
    }

    /**
     * Method used to print whole sudoku board
     *
     * @param board
     */
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 8) {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
