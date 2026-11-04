package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AlphaBetaV4Player player1 = new AlphaBetaV4Player(4);
        AlphaBetaV4Player player2 = new AlphaBetaV4Player(4);
        ArrayList<Integer> list = Partie.PartieSpielenLassen(player1, player2, 10);
        int unentschieden = 0;
        int siegePlayer1 = 0;
        int siegePlayer2 = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == 0){
                unentschieden++;
            }
            if(list.get(i) == 1){
                siegePlayer1++;
            }
            if(list.get(i) == -1){
                siegePlayer2++;
            }
        }
        System.out.println(player1.name() + ": " + siegePlayer1);
        System.out.println(player2.name() + ": " + siegePlayer2);
        System.out.println("Rémi: " + unentschieden);
        System.out.println("Millisekunden: " + (System.currentTimeMillis() - start));
    }
}
