package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.util.Arrays;

import static ch.nils.BoardAsNumber.boardAsNumber;

public class FileFürTests {
    public static void main(String[] args){
        NeuralNetwork nn = new NeuralNetwork(new int[]{733, 15, 15, 1});
        Board board = new Board();
        System.out.println(nn.howGoodIsBoard(BoardAsNumber.boardAsNumber(board)));

    }
}
