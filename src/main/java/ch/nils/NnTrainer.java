package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static ch.nils.BoardAsNumber.boardAsNumber;

public class NnTrainer {
    /*
     to-do wenn man generation ändert:

     Modell eechseln
     modelwert beim score berechnen ändern
     beim sout max score ändern
    */
    public static void main(String[] args) throws IOException {
        //Erstes immer 773 letztes immer 1
        int[] layers = {773, 16, 16, 1};

        ArrayList<NeuralNetwork> neuralNetworksArray = new ArrayList<>();

        File[] ordners = new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/").listFiles(File::isDirectory);
        int generation = 0;
        if(ordners == null){
            generation = 0;
        }else{
            generation = ordners.length;
        }


        if(generation == 0){
            neuralNetworksArray = makeNewRandom(layers);
        }else{
            for (int i = 0; i < 50; i++) {
                neuralNetworksArray.add(new NeuralNetwork(new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/" + generation + "/" + i)));
            }
        }

        while(true) {


            if (!(generation == 0)) {
                for (int i = 0; i < 50; i++) {
                    File file = new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/" + generation + "/" + i);
                    neuralNetworksArray.add(new NeuralNetwork(file));
                }
            }


            generation++;
            for (int i = 50; i < 100; i++) {
                neuralNetworksArray.get(i).gewichteBiaseLeichtVerändern();
            }
            for (int i = 0; i < 100; i++) {
                neuralNetworksArray.get(i).generationErhöhen();
                Integer[] ergebnisse = Partie.partieSpielenLassen(new NNPlayer(neuralNetworksArray.get(i)), new RandomPlayer(), 20).toArray(new Integer[0]);
                double score = 0;
                for (int j = 0; j < ergebnisse.length; j++) {
                    if (ergebnisse[j] == -1) {
                    } else if (ergebnisse[j] == 0) {
                        score = score + 0.5;
                    } else {
                        score++;
                    }
                }
                score = score * 1;
                neuralNetworksArray.get(i).setScore(score);
            }

            neuralNetworksArray.sort(Comparator.comparingDouble(NeuralNetwork::getScore).reversed());

            for (int i = 0; i < 50; i++) {
                neuralNetworksArray.removeLast();
            }

            for (int i = 0; i < 50; i++) {
                neuralNetworksArray.get(i).saveNetworkToFile(new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/" + generation + "/" + i));
            }
            if (generation > 2) {
                for (int i = 49; 0 < i; i--) {
                    Files.deleteIfExists(new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/" + (generation - 2) + "/" + i).toPath());
                }
            }
            System.out.println("Generation: " + generation + "; Score, des besten: " + neuralNetworksArray.getFirst().getScore() + " von " + (1 * 20));
        }
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
