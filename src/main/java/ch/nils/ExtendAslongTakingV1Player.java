package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ExtendAslongTakingV1Player extends Player{
    private final int deepness;
    public ExtendAslongTakingV1Player(int deepnes) {
        this.deepness = deepnes;
    }
    private int wieVieleHelfer = 0;
    record zugMitWertung(Move zug, int bewertung) {}


    @Override
    public Move chooseMove(Board board){
        if(deepness < 3){
            AlphaBetaV1Player TempPlayer = new AlphaBetaV1Player(deepness);
            return TempPlayer.chooseMove(board);
        }else {
            Random random = new Random();
            List<zugMitWertung> zuege = new ArrayList<zugMitWertung>();
            Move[] tempMoves = board.legalMoves().toArray(new Move[0]);
            for (int i = 0; i < tempMoves.length; i++) {
                zuege.add(new zugMitWertung(tempMoves[i], 0));
            }
            for (int i = 0; i < zuege.size(); i++) {
                board.doMove(zuege.get(i).zug);
                zuege.set(i, new zugMitWertung(zuege.get(i).zug, -meinehelfer(board, 2 - 1, -1000000, 1000000)));
                board.undoMove();
            }
            zuege.sort(Comparator.comparingInt(zugMitWertung::bewertung).reversed());
            for (int i = 0; i < zuege.size(); i++) {
                board.doMove(zuege.get(i).zug);
                zuege.set(i, new zugMitWertung(zuege.get(i).zug, -meinehelfer(board, deepness - 1, -1000000, 1000000)));
                board.undoMove();
            }
            zuege.sort(Comparator.comparingInt(zugMitWertung::bewertung).reversed());
            int i = 0;
            while(i < zuege.size() && zuege.getFirst().bewertung == zuege.get(i).bewertung){
                i++;
            }
            return zuege.get(random.nextInt(0, i)).zug;
        }
    }

    private int meinehelfer(Board board, int deepness, int untergrenze, int obergrenze) {
        wieVieleHelfer++;
        if(deepness == 0){
            return meinhelferSobaldDeepnessErreicht(board, untergrenze, obergrenze);
        }
        if(board.isMated()){
            return -100000;
        }
        if(board.isDraw()){
            return 0;
        }
        Move[] tempMoves = board.legalMoves().toArray(new Move[0]);
        List<zugMitWertung> zuege = new ArrayList<zugMitWertung>();
        for (int i = 0; i < tempMoves.length; i++) {
            zuege.add(new zugMitWertung(tempMoves[i], -1000000000));
        }
        for (int i = 0; i < zuege.size(); i++) {
            zuege.set(i, new zugMitWertung(zuege.get(i).zug, Bewertung.valueOfEndField(board, zuege.get(i).zug)));
        }
        zuege.sort(Comparator.comparingInt(zugMitWertung::bewertung).reversed());
        for (int i = 0; i < zuege.size(); i++) {
            zuege.set(i, new zugMitWertung(zuege.get(i).zug, -10000000));
        }
        for (int i = 0; i < zuege.size(); i++) {
            board.doMove(zuege.get(i).zug);
            zuege.set(i, new zugMitWertung(zuege.get(i).zug, -meinehelfer(board, deepness - 1, -obergrenze, -untergrenze)));
            board.undoMove();
            if(untergrenze < zuege.get(i).bewertung){
                untergrenze = zuege.get(i).bewertung;
            }
            if(obergrenze <= untergrenze){
                break;
            }
        }
        int besterZug = -100000000;
        for (int i = 0; i < zuege.size(); i++) {
            if(besterZug < zuege.get(i).bewertung){
                besterZug = zuege.get(i).bewertung;
            }
        }
        return besterZug;
    }
    private int meinhelferSobaldDeepnessErreicht(Board board, int untergrenze, int obergrenze){
        wieVieleHelfer++;
        List<zugMitWertung> zuegeDieFressen = new ArrayList<zugMitWertung>();
        Move[] tempMoves = board.legalMoves().toArray(new Move[0]);
        for (int i = 0; i < tempMoves.length; i++) {
            if(Bewertung.valueOfEndField(board, tempMoves[i]) != 0){
                zuegeDieFressen.add(new zugMitWertung(tempMoves[i], Bewertung.valueOfEndField(board, tempMoves[i])));
            }
        }
        if(zuegeDieFressen.isEmpty()){
            return Bewertung.material(board, board.getSideToMove());
        }
        zuegeDieFressen.sort(Comparator.comparingInt(zugMitWertung::bewertung).reversed());


        int baseline = Bewertung.material(board, board.getSideToMove());
        if(baseline > obergrenze){
            return baseline;
        }
        if(baseline > untergrenze){
            untergrenze = baseline;
        }
        for (int i = 0; i < zuegeDieFressen.size(); i++) {
            board.doMove(zuegeDieFressen.get(i).zug);
            zuegeDieFressen.set(i, new zugMitWertung(zuegeDieFressen.get(i).zug, -meinhelferSobaldDeepnessErreicht(board,  -obergrenze, -untergrenze)));
            board.undoMove();
            if(untergrenze < zuegeDieFressen.get(i).bewertung){
                untergrenze = zuegeDieFressen.get(i).bewertung;
            }
            if(obergrenze <= untergrenze){
                break;
            }
        }
        int besterZug = -100000000;
        for (int i = 0; i < zuegeDieFressen.size(); i++) {
            if(besterZug < zuegeDieFressen.get(i).bewertung){
                besterZug = zuegeDieFressen.get(i).bewertung;
            }
        }
        if(baseline > besterZug){
            return baseline;
        }
        return besterZug;



    }
    @Override
    public String name() {
        return "ExtendAslongTakingV1Player Depth: " + deepness + " Knoten: " + wieVieleHelfer;
    }
}
