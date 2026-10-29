package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player{
    Random random = new Random();



    public Move chooseMove(Board board) {
        List<Move> list = (List<Move>) board.legalMoves();
        return list.get(random.nextInt(0, list.size()));
    }


    public String name() {
        return "Random";
    }
}