package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        RandomPlayer weiss = new RandomPlayer();
        RandomPlayer schwarz = new RandomPlayer();

        System.out.println(Partie.PartieSpielenLassen(weiss, schwarz));
    }
}
