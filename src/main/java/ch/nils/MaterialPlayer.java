package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.List;

public class MaterialPlayer implements Player{
    public Move chooseMove(Board board){
        List<Move> alleZmöglichenZüge = (List<Move>) board.legalMoves();
        Side werBinIch = board.getSideToMove();
        int maxPunkte = -1000000;
        int undWo = 0;
        for (int i = 0; i < alleZmöglichenZüge.size(); i++) {
            board.doMove(alleZmöglichenZüge.get(i));
            int punkte = YourPointsCompardeToEnemy(board, werBinIch);
            if(punkte > maxPunkte){
                maxPunkte = punkte;
                undWo = i;
            }

            board.undoMove();
        }
        return alleZmöglichenZüge.get(undWo);
    }
    private int YourPointsCompardeToEnemy(Board board, Side side){
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
        if(board.isMated()){
            if(side == Side.WHITE){
                points = 100000000;
            }
            if(side == Side.BLACK){
                points = -100000000;
            }
        }
        if(side == Side.WHITE){
            return points;
        }else{
            return -points;
        }
    }
    public String name() {
        return "Material";
    }
}
