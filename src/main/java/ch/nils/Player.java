package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

public abstract class Player {
    public abstract Move chooseMove(Board board);

    public abstract String name();
}