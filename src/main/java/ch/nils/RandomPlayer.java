package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.Random;

public class RandomPlayer implements Player{
    Random random = new Random();



    public Move chooseMove(Board board) {
        return board.legalMoves().get(random.nextInt(0, board.legalMoves().size()));
    }


    public String name() {
        return "Random";
    }
}