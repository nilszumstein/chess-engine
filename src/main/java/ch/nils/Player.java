package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

public interface Player {
    Move chooseMove(Board board);
    String name();
}