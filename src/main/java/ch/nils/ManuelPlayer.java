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
        System.out.println("Bitte gib dienen Zug ein");
        Move zugMussNochGetestetWerden = new Move(scanner.nextLine(), board.getSideToMove());
        while(true){
            if(board.legalMoves().contains(zugMussNochGetestetWerden)){
                return zugMussNochGetestetWerden;
            }else{
                System.out.println("Zug war illegal, bitte gib einen neuen ein: ");
                zugMussNochGetestetWerden = new Move(scanner.nextLine(), board.getSideToMove());
            }
        }
    }

    @Override
    public String name() {
        return "Manuel Player";
    }
}
