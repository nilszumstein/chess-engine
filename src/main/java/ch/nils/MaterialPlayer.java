package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialPlayer extends Player{
    @Override
    public Move chooseMove(Board board){
        Random random = new Random();
        List<Move> alleZmöglichenZüge = (List<Move>) board.legalMoves();
        List<Integer> wieGutIstWas = new ArrayList<Integer>();
        Side werBinIch = board.getSideToMove();
        int maxPunkte = -1000000;
        int undWo = 0;
        for (int i = 0; i < alleZmöglichenZüge.size(); i++) {
            board.doMove(alleZmöglichenZüge.get(i));
            int punkte;
            if (board.isMated()){
                punkte = 100000000;
            }else{
                punkte = Bewertung.yourPointsCompardeToEnemy(board, werBinIch);
            }
            wieGutIstWas.add(punkte);
            if(punkte > maxPunkte){
                maxPunkte = punkte;
                undWo = i;
            }

            board.undoMove();
        }


        List<Integer> wasAllesMax = new ArrayList<Integer>();
        for (int i = 0; i < alleZmöglichenZüge.size(); i++) {
            if(wieGutIstWas.get(i) == maxPunkte){
                wasAllesMax.add(i);
            }
        }
        return alleZmöglichenZüge.get(wasAllesMax.get(random.nextInt(0, wasAllesMax.size())));
    }
    @Override
    public String name() {
        return "Material";
    }
}
