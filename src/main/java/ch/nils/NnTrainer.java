package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static ch.nils.BoardAsNumber.boardAsNumber;

public class NnTrainer {

    public static void main(String[] args) throws IOException {
        ArrayList<NeuralNetwork> neuralNetworksArray = new ArrayList<>();
        //Erstes immer 773 letztes immer 1
        int[] layers = {1, 2};


    }

    private static ArrayList<NeuralNetwork> resumeSession(int[] layers){

    }
    private static ArrayList<NeuralNetwork> makeNewRandom(int[] layers){

        ArrayList<NeuralNetwork> nNs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            nNs.add(new NeuralNetwork(layers));
            nNs.get(i).setGeneration(1);
        }
        return nNs;
    }
}
//"Models/allDiffrentLayers/LayerXYZ/genXXXX/ModelXX(1beste 50 schlechtest, der besseren hälfte)"





