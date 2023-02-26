package com.peicheng;

import java.util.Scanner;

public class ConnectFour {

    public static char[][] createGameBoard(){
        char[][] board = new char[6][7];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                board[i][j] = '.';
            }
        }
        return board;
    }

    public static int drop(char p, int c, char[][] board){
        for(int r = 5; r >= 0; r--){
            if(board[r][c] == '.'){
                board[r][c] = p;
                return r;
            }
        }
        return -1;
    }

    public static boolean checkWin(int row, int col, char c, int p, char[][] board){
        for(int i = 0; i + 3 < 7; i++){
            if(c == board[row][i]
                    && board[row][i] == board[row][i + 1]
                    && board[row][i + 1] == board[row][i + 2]
                    && board[row][i + 2] == board[row][i + 3]){
                return true;
            }
        }

        //check cols
        for(int i = 0; i + 3 < 6; i++){
            if(c == board[i][col]
                    && board[i][col] == board[i + 1][col]
                    && board[i + 1][col] == board[i + 2][col]
                    && board[i + 2][col] == board[i + 3][col]){
                return true;
            }
        }

        //check ltrDia
        for(int i = 0; i + 3 < 6; i++){
            for(int j = 0; j + 3 < 7; j++){
                if(c == board[i][j]
                        && board[i][j] == board[i + 1][j + 1]
                        && board[i + 1][j + 1] == board[i + 2][j + 2]
                        && board[i + 2][j + 2] == board[i + 3][j + 3]){
                    return true;
                }
            }
        }

        //check rtrDia
        for(int i = 5; i - 3 >= 0; i--){
            for(int j = 0; j + 3 < 7; j++){
                if(c == board[i][j]
                        && board[i][j] == board[i - 1][j + 1]
                        && board[i - 1][j + 1] == board[i - 2][j + 2]
                        && board[i - 2][j + 2] == board[i - 3][j + 3]){
                    return true;
                }
            }
        }
        return false;
    }

    public static void printCongrats(boolean y){
        if(y){
            System.out.println();
            System.out.println("***************");
            System.out.println("* Yellow Wins *");
            System.out.println("***************");
        }else{
            System.out.println();
            System.out.println("************");
            System.out.println("* Red Wins *");
            System.out.println("************");
        }
    }

    // ***************
    // * Yellow Wins *
    // ***************

    public static void printBoard(char[][] board){

        for(int i = 0; i < 29; i++) {
            System.out.print("=");
        }
        System.out.println();

        for(int r = 0; r < 6; r++){
            System.out.print("| ");
            for(int c = 0; c < 7; c++){
                if(board[r][c] != '.') {
                    System.out.print(board[r][c]);
                }else{
                    System.out.print(" ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }

        //print bottom
        for(int i = 0; i < 29; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.print("  ");
        for(int i = 0; i < 7; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        for(int i = 0; i < 29; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char[][] board = createGameBoard();
        Boolean yellow = true;
        Boolean play = true;
        while(play){
            Scanner scan;
            if(yellow){
                System.out.println("Yellow's turn:");
                System.out.print("Please drop a disk at column 0 to 6: ");
                scan = new Scanner(System.in);
                int c = scan.nextInt();
                if(c > 6 || c < 0){
                    System.out.println("There is no column " + c +"!!");
                    continue;
                }
                int r = drop('Y', c, board);
                if(r == -1){
                    System.out.println("This column is full!");
                    continue;
                }
                printBoard(board);
                play = !checkWin(r, c, 'Y', 0, board);
            }else{
                System.out.println("Red's turn:");
                System.out.print("Please drop a disk at column 0 to 6: ");
                scan = new Scanner(System.in);
                int c = scan.nextInt();
                if(c > 6 || c < 0){
                    System.out.println("There is no column " + c + "!!");
                    continue;
                }
                int r = drop('R', c, board);
                if(r == -1){
                    System.out.println("This column is full!");
                    continue;
                }
                printBoard(board);
                play = !checkWin(r, c, 'R', 0, board);
            }
            if(!play){
                printCongrats(yellow);
            }
            yellow = !yellow;
        }
    }
}



