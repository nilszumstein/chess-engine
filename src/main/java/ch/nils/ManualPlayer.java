package ch.nils;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.Scanner;

public class ManualPlayer extends Player{
    private Scanner scanner = new Scanner(System.in);
    @Override
    public Move chooseMove(Board board) {
        System.out.println(board);
        System.out.println("Der gegner hat das gespielt:");
        if(!board.getBackup().isEmpty()){
            System.out.println(board.getBackup().getLast().getMove());
        }
        System.out.println("Bitte gib dienen Zug ein");
        return new Move(scanner.nextLine(), board.getSideToMove());
    }

    @Override
    public String name() {
        return "Manuel Player";
    }
}
