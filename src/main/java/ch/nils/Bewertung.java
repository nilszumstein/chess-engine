package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;

public class Bewertung {
    public static int material(Board board, Side side){
        int points = 0;
        points = points + 100 * Long.bitCount(board.getBitboard(Piece.WHITE_PAWN));
        points = points - 100 * Long.bitCount(board.getBitboard(Piece.BLACK_PAWN));
        points = points + 320 * Long.bitCount(board.getBitboard(Piece.WHITE_KNIGHT));
        points = points - 320 * Long.bitCount(board.getBitboard(Piece.BLACK_KNIGHT));
        points = points + 330 * Long.bitCount(board.getBitboard(Piece.WHITE_BISHOP));
        points = points - 330 * Long.bitCount(board.getBitboard(Piece.BLACK_BISHOP));
        points = points + 500 * Long.bitCount(board.getBitboard(Piece.WHITE_ROOK));
        points = points - 500 * Long.bitCount(board.getBitboard(Piece.BLACK_ROOK));
        points = points + 900 * Long.bitCount(board.getBitboard(Piece.WHITE_QUEEN));
        points = points - 900 * Long.bitCount(board.getBitboard(Piece.BLACK_QUEEN));
        if(side == Side.WHITE){
            return points;
        }else{
            return -points;
        }
    }

}
