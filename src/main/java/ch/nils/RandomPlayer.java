package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player{
    Random random = new Random();


    @Override
    public Move chooseMove(Board board) {
        List<Move> list = (List<Move>) board.legalMoves();
        return list.get(random.nextInt(0, list.size()));
    }

    @Override
    public String name() {
        return "Random";
    }
}