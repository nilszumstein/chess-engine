package ch.nils;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        MaterialWithDephtPlayer Player1 = new MaterialWithDephtPlayer(1);
        RandomPlayer Player2 = new RandomPlayer();
        ArrayList<Integer> list = Partie.PartieSpielenLassen(Player1, Player2, 100);
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
