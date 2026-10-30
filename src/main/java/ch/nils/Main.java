package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ManualPlayer Player1 = new ManualPlayer();
        MaterialWithDephtPlayer Player2 = new MaterialWithDephtPlayer(5);
        ArrayList<Integer> list = Partie.PartieSpielenLassen(Player1, Player2, 2);
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
        System.out.println(Player1.name() + ": " + siegePlayer1);
        System.out.println(Player2.name() + ": " + siegePlayer2);
        System.out.println("Rémi: " + unentschieden);
    }
}
