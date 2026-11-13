package ch.nils;


import java.io.File;
import java.io.IOException;
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
        int[] layers = {773, 16, 8, 1};

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
            double averideg = 0;
            for (int i = 0; i < neuralNetworksArray.size(); i++) {
                averideg = averideg + neuralNetworksArray.get(i).getScore();
            }
            averideg = averideg / neuralNetworksArray.size();
            System.out.println("Generation: " + generation + "; Score, des besten: " + neuralNetworksArray.getFirst().getScore() + "; Maximum: " + (score * 60) + "; Minimum:" + (-score * 20) + "; Mit so viel Punkten zum nächsten Bot: " + (score * 40 * 0.75) + "; Durchschnitt der besten 50: " + averideg + ";");
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
