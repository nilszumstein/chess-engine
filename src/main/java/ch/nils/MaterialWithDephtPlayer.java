package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialWithDephtPlayer extends Player{
    private final int deepness;
    public MaterialWithDephtPlayer(int deepnes) {
        this.deepness = deepnes;
    }
    @Override
    public Move chooseMove(Board board){
        Random random = new Random();
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

        List<Integer> wasAllesMax = new ArrayList<Integer>();
        for (int i = 0; i < möglicheZüge.size(); i++) {
            if(wieGutIstWas.get(i) == maxPunkte){
                wasAllesMax.add(i);
            }
        }
        return möglicheZüge.get(wasAllesMax.get(random.nextInt(0, wasAllesMax.size())));
    }

    private int meinehelfer(Board board, int deepness) {
        if(board.isMated()){
            return -100000;
        }
        if(board.isDraw()){
            return 0;
        }
        if(deepness == 0){
            return Bewertung.yourPointsCompardeToEnemy(board, board.getSideToMove());
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

    @Override
    public String name() {
        return "MaterialWithDepht Depth: " + deepness;
    }
}
