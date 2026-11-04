package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class AlphaBetaV2Player extends Player{
    private final int deepness;
    public AlphaBetaV2Player(int deepnes) {
        this.deepness = deepnes;
    }
    record zugMitWertung(Move zug, int bewertung) {}


    @Override
    public Move chooseMove(Board board){
        if(deepness < 3){
            AlphaBetaV1Player TempPlayer = new AlphaBetaV1Player(deepness);
            return TempPlayer.chooseMove(board);
        }else {


            Random random = new Random();
            List<zugMitWertung> züge = new ArrayList<zugMitWertung>();
            Move[] tempMoves = board.legalMoves().toArray(new Move[0]);
            for (int i = 0; i < tempMoves.length; i++) {
                züge.add(new zugMitWertung(tempMoves[i], 0));
            }
            for (int i = 0; i < züge.size(); i++) {
                board.doMove(züge.get(i).zug);
                züge.set(i, new zugMitWertung(züge.get(i).zug, -meinehelfer(board, 2 - 1, -1000000, 1000000)));
                board.undoMove();
            }
            züge.sort(Comparator.comparingInt(zugMitWertung::bewertung).reversed());
            for (int i = 0; i < züge.size(); i++) {
                board.doMove(züge.get(i).zug);
                züge.set(i, new zugMitWertung(züge.get(i).zug, -meinehelfer(board, deepness - 1, -1000000, 1000000)));
                board.undoMove();
            }
            züge.sort(Comparator.comparingInt(zugMitWertung::bewertung).reversed());
            int i = 0;
            while(i < züge.size() && züge.get(0).bewertung == züge.get(i).bewertung){
                i++;
            }
            return züge.get(random.nextInt(0, i)).zug;
        }
    }

    private int meinehelfer(Board board, int deepness, int untergrenze, int obergrenze) {
        if(board.isMated()){
            return -100000;
        }
        if(board.isDraw()){
            return 0;
        }
        if(deepness == 0){
            return Bewertung.material(board, board.getSideToMove());
        }
        int besteZug = -1000000;
        List<Move> möglicheZüge = board.legalMoves();
        for (int i = 0; i < möglicheZüge.size(); i++) {

            board.doMove(möglicheZüge.get(i));
            int zwischenspeichernVonPunkten = -meinehelfer(board, deepness - 1, -obergrenze, -untergrenze);
            board.undoMove();
            if(zwischenspeichernVonPunkten > besteZug){
                besteZug = zwischenspeichernVonPunkten;
            }
            if(untergrenze < zwischenspeichernVonPunkten){
                untergrenze = zwischenspeichernVonPunkten;
            }
            if(obergrenze <= untergrenze){
                break;
            }
        }
        return besteZug;
    }
    @Override
    public String name() {
        return "Name: AlphaBetaV2; Depth: " + deepness + "; ";
    }
}
