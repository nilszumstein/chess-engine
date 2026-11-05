package ch.nils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tournier {
    public static void main(String[] args) throws IOException {
        int howManyGames = 50;

        List<Player> mitspieler = new ArrayList<Player>();
        mitspieler.add(new RandomPlayer());
        mitspieler.add(new AlphaBetaV6Player(4));
        mitspieler.add(new MaterialPlayer());
        mitspieler.add(new ExtendAslongTakingV1Player(3));
        mitspieler.add(new ExtendAslongTakingV1Player(4));
        mitspieler.add(new TimeV1Player(1000));
        for (int i = 0; i < mitspieler.size(); i++) {
            for (int j = 0; j < mitspieler.size(); j++) {
                if(i == j){

                }else{
                    long start = System.currentTimeMillis();
                    ArrayList<Integer> list = Partie.PartieSpielenLassen(mitspieler.get(i), mitspieler.get(j), howManyGames);
                    int unentschieden = 0;
                    int siegePlayer1 = 0;
                    int siegePlayer2 = 0;
                    for (int k = 0; k < list.size(); k++) {
                        if(list.get(k) == 0){
                            unentschieden++;
                        }
                        if(list.get(k) == 1){
                            siegePlayer1++;
                        }
                        if(list.get(k) == -1){
                            siegePlayer2++;
                        }
                    }
                    String zeile = "Uhrzeit: " + LocalDateTime.now() + "; Computername: " + System.getenv("COMPUTERNAME") + "; " + mitspieler.get(i).name() + mitspieler.get(j).name() + "Player1siege: " + siegePlayer1 + "; Player2siege: " + siegePlayer2 + "; Rémi: " + unentschieden + "; ZeitMillisekunden: " + (System.currentTimeMillis() - start) + ";";
                    System.out.println("etwas so viel % fertig: " + ((mitspieler.size() * mitspieler.size() - i * 5 - j) * 100 / (mitspieler.size() * mitspieler.size())));
                    Files.writeString(Path.of("AutoTestergebnisse.csv"), zeile + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                }
            }
        }
    }
}
