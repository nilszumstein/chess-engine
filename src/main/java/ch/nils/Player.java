package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

public  abstract class Player {
    abstract Move chooseMove(Board board);

    abstract String name();
}