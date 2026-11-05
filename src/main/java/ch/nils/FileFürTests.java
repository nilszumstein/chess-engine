package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.util.Arrays;

import static ch.nils.BoardAsNumber.boardAsNumber;

public class FileFürTests {
    public static void main(String[] args){
        Board board = new Board();
        System.out.println(Arrays.toString(boardAsNumber(board)));
    }
}
