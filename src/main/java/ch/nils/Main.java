package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Player player1 = new RandomPlayer();
        Player player2 = new ch.nils.MaterialPlayer();
        ArrayList<Integer> list = Partie.PartieSpielenLassen(player1, player2, 1000);
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
        String zeile = "Uhrzeit: " + LocalDateTime.now() + "; Computername: " + System.getenv("COMPUTERNAME") + "; " + player1.name() + player2.name() + "Player1siege: " + siegePlayer1 + "; Player2siege: " + siegePlayer2 + "; Rémi: " + unentschieden + "; ZeitMillisekunden: " + (System.currentTimeMillis() - start) + ";";
        System.out.println(zeile);
        Files.writeString(Path.of("AutoTestergebnisse.csv"), zeile + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
