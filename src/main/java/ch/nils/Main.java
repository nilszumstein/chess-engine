package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        String zug;
        RandomPlayer gegner = new RandomPlayer();
        while(true){
            System.out.println(board);
            System.out.println("Was ist dein Zug?");
            zug = scanner.nextLine();
            board.doMove(zug);
            System.out.println(board);
            board.doMove(gegner.chooseMove(board));
        }
    }
}
