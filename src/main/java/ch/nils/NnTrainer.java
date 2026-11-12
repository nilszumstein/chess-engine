package ch.nils;


import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class NnTrainer {
    /*
     to-do, wenn man generation ändert:

     Modelle wechseln
     Modellwert beim score berechnen ändern
     beim System.out.println max score ändern
    */
    static void main(String[] args) throws IOException {
        //Erstes immer 773 letztes immer 1
        int score = 21;
        int[] layers = {773, 32, 32, 1};

        ArrayList<NeuralNetwork> neuralNetworksArray = new ArrayList<>();

        File[] ordners = new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/").listFiles(File::isDirectory);
        int generation = 0;
        if(!(ordners == null)){
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

            neuralNetworksArray.parallelStream().forEach(element -> {
                element.generationErhöhen();
                element.setScore(Partie.nnPartieSpielenLassen(new NNPlayer(element), new AlphaBetaV6Player(1), 40, score));
            });
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
            System.out.println("Generation: " + generation + "; Score, des besten: " + neuralNetworksArray.getFirst().getScore() + " von " + (score * 60));
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
