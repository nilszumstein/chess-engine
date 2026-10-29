package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.util.ArrayList;

public class Partie {
    public static ArrayList<Integer> PartieSpielenLassen(Player player1, Player player2, int howManyGames){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < howManyGames / 2; i++) {
            Board board = new Board();
            int wessenZugIstEs = 1;
            int zähler = 0;
            while (!board.isMated() && !board.isDraw() && zähler < 500) {
                if (wessenZugIstEs == 1) {
                    board.doMove(player1.chooseMove(board));
                    wessenZugIstEs = 2;
                } else if (wessenZugIstEs == 2) {
                    board.doMove(player2.chooseMove(board));
                    wessenZugIstEs = 1;
                }
                zähler++;
            }
            if (zähler > 499 || board.isDraw()) {
                list.add(0);
            } else if (wessenZugIstEs == 1) {
                list.add(-1);
            } else if (wessenZugIstEs == 2) {
                list.add(1);
            }
        }
        for (int i = 0; i < howManyGames / 2; i++) {
            Board board = new Board();
            int wessenZugIstEs = 2;
            int zähler = 0;
            while (!board.isMated() && !board.isDraw() && zähler < 500) {
                if (wessenZugIstEs == 1) {
                    board.doMove(player1.chooseMove(board));
                    wessenZugIstEs = 2;
                } else if (wessenZugIstEs == 2) {
                    board.doMove(player2.chooseMove(board));
                    wessenZugIstEs = 1;
                }
                zähler++;
            }
            if (zähler > 499 || board.isDraw()) {
                list.add(0);
            } else if (wessenZugIstEs == 1) {
                list.add(-1);
            } else if (wessenZugIstEs == 2) {
                list.add(1);
            }
        }
        return list;
    }
}
