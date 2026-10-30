package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.Scanner;

public class ManuelPlayer extends Player{
    private Scanner scanner = new Scanner(System.in);
    @Override
    public Move chooseMove(Board board) {
        System.out.println(board);
        System.out.println("Der gegner hat das gespielt:");
        if(!board.getBackup().isEmpty()){
            System.out.println(board.getBackup().getLast().getMove());
        }

        while(true){
            System.out.println("Bitte gib deinen Zug ein");
            try{
                Move zug = new Move(scanner.nextLine(), board.getSideToMove());
                if(board.legalMoves().contains(zug)){
                    return zug;
                }
            }catch (Exception e){

            }
            System.out.println("Der Zug war nicht erlaubt, bitte gib einen neuen ein.");
        }
    }

    @Override
    public String name() {
        return "Manuel Player";
    }
}
