package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NNPlayer extends Player {
    record zugMitWertung(Move zug, float bewertung) {}

    public NNPlayer(NeuralNetwork nn) {
        this.nn = nn;
    }

    NeuralNetwork nn;


    @Override
    public Move chooseMove(Board board) {
        List<Move> alleZmöglichenZüge = (List<Move>) board.legalMoves();
        List<zugMitWertung> wieGutIstWasMitZug = new ArrayList<>();
        Side werBinIch = board.getSideToMove();
        for (int i = 0; i < alleZmöglichenZüge.size(); i++) {
            board.doMove(alleZmöglichenZüge.get(i));
            wieGutIstWasMitZug.add(new zugMitWertung(alleZmöglichenZüge.get(i), nn.howGoodIsBoard(BoardAsNumber.boardAsNumber(board))));
            board.undoMove();
        }
        if(werBinIch == Side.WHITE){
            wieGutIstWasMitZug.sort(Comparator.comparingDouble(zugMitWertung::bewertung).reversed());
        }else{
            wieGutIstWasMitZug.sort(Comparator.comparingDouble(zugMitWertung::bewertung));
        }
        return wieGutIstWasMitZug.getFirst().zug;
    }

    @Override
    public String name() {
        return "Name: NN; Gen: " + nn.getGeneration() + "; Layers: " + Arrays.toString(nn.getLayers()) + "; ";
    }
}
