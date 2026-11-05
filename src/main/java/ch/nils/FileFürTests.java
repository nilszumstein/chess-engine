package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import static ch.nils.BoardAsNumber.boardAsNumber;

public class FileFürTests {
    public static void main(String[] args){
        Board board = new Board();
        System.out.println(String.valueOf(boardAsNumber(board)));
    }
}
