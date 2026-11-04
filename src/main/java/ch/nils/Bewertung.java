package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;

public class Bewertung {
    public static int valueOfEndField(Board board, Move move){
        if(board.getPiece(move.getTo()) == Piece.WHITE_PAWN){
            return 100;
        }else if(board.getPiece(move.getTo()) == Piece.WHITE_KNIGHT) {
            return 320;
        }else if(board.getPiece(move.getTo()) == Piece.WHITE_BISHOP) {
            return 330;
        }else if(board.getPiece(move.getTo()) == Piece.WHITE_ROOK) {
            return 500;
        }else if(board.getPiece(move.getTo()) == Piece.WHITE_QUEEN) {
            return 900;
        }else if(board.getPiece(move.getTo()) == Piece.WHITE_KING) {
            return 1000000;
        }else if(board.getPiece(move.getTo()) == Piece.BLACK_PAWN){
            return 100;
        }else if(board.getPiece(move.getTo()) == Piece.BLACK_KNIGHT) {
            return 320;
        }else if(board.getPiece(move.getTo()) == Piece.BLACK_BISHOP) {
            return 330;
        }else if(board.getPiece(move.getTo()) == Piece.BLACK_ROOK) {
            return 500;
        }else if(board.getPiece(move.getTo()) == Piece.BLACK_QUEEN) {
            return 900;
        }else if(board.getPiece(move.getTo()) == Piece.BLACK_KING) {
            return 1000000;
        }else{
            return 0;
        }
    }
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
