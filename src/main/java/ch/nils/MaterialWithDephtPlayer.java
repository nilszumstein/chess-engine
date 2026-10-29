package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.List;

public class MaterialWithDephtPlayer implements Player{
    private int deepness;
    public MaterialWithDephtPlayer(int deepnes) {
        this.deepness = deepnes;
    }

    public Move chooseMove(Board board){
        List<Move> möglicheZüge = board.legalMoves();
        List<Integer> wieGutIstWas = new ArrayList<Integer>();
        for (int i = 0; i < möglicheZüge.size(); i++) {
            board.doMove(möglicheZüge.get(i));
            wieGutIstWas.add(-meinehelfer(board, deepness - 1));
            board.undoMove();
        }
        int maxPunkte = -1000000;
        int anWelcherStelle = 0;
        for (int i = 0; i < wieGutIstWas.size(); i++) {
            if(wieGutIstWas.get(i) > maxPunkte){
                maxPunkte = wieGutIstWas.get(i);
                anWelcherStelle = i;
            }
        }
        return möglicheZüge.get(anWelcherStelle);
    }

    private int meinehelfer(Board board, int deepness) {
        if(board.isMated()){
            return -100000;
        }
        if(board.isDraw()){
            return 0;
        }
        if(deepness == 0){
            return YourPointsCompardeToEnemy(board);
        }
        
        int besteZug = -1000000;
        List<Move> möglicheZüge = board.legalMoves();
        for (int i = 0; i < möglicheZüge.size(); i++) {
            board.doMove(möglicheZüge.get(i));
            int zwischenspeichernVonPunkten = -meinehelfer(board, deepness - 1);
            board.undoMove();
            if(zwischenspeichernVonPunkten > besteZug){
                besteZug = zwischenspeichernVonPunkten;
            }
        }
        return besteZug;
    }

    private int YourPointsCompardeToEnemy(Board board){
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
        if(board.getSideToMove() == Side.WHITE){
            return points;
        }else{
            return -points;
        }
    }
    public String name() {
        return "MaterialWithDepht";
    }
}
