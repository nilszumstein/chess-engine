package ch.nils;

import com.github.bhlangonijr.chesslib.Board;


public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
        System.out.println(board.legalMoves());
        board.doMove("d2d4");
        System.out.println(board);
        System.out.println(board.legalMoves());
        board.doMove("d7d5");
        System.out.println(board);
        System.out.println(board.legalMoves());
    }
}
