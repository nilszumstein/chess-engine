package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

public class Partie {
    public static Integer PartieSpielenLassen(Player weiss, Player schwarz){
        Board board = new Board();
        int wessenZugIstEs = 1;
        int zähler = 0;
        while(!board.isMated() && !board.isDraw() && zähler < 500){
            if(wessenZugIstEs == 1){
                board.doMove(weiss.chooseMove(board));
                wessenZugIstEs = 2;
            }else if(wessenZugIstEs == 2){
                board.doMove(schwarz.chooseMove(board));
                wessenZugIstEs = 1;
            }
            zähler++;
        }


        if(zähler > 499 || board.isDraw()){
            return 0;
        }else if(wessenZugIstEs == 1){
            return -1;
        }else if(wessenZugIstEs == 2){
            return 1;
        }else{//Das habe ich nur gemacht, wel es sonst probleme gab, weil potenziell kein return statement
            return 0;
        }
    }
}
