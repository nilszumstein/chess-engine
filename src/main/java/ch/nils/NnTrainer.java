package ch.nils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


public class NnTrainer {
    static void main(String[] args) throws IOException {
        int score = 21;
        int[] layers = {773, 32, 32, 1};
        Player gegner = new AlphaBetaV6Player(1);
        double passabilityToMutate = 0.01;


        generateTwentyTwentyTwentyTwentyTwenty(score, layers, gegner);
        //generateWithNwarscheinlichkeit(score,layers,passabilityToMutate,gegner);
    }


    static void generateWithNwarscheinlichkeit(int score, int[] layers, double mutatePossability, Player gegner) throws IOException {
        ArrayList<NeuralNetwork> neuralNetworksArray = new ArrayList<>();
        File[] ordners = new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/").listFiles(File::isDirectory);
        int generation = 0;
        if (!(ordners == null)) {
            generation = ordners.length;
        }


        if (generation == 0) {
            neuralNetworksArray = makeNewRandom(layers);
        } else {
            for (int i = 0; i < 50; i++) {
                neuralNetworksArray.add(new NeuralNetwork(new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/" + generation + "/" + i)));
            }
        }

        while (true) {


            if (!(generation == 0)) {
                for (int i = 0; i < 50; i++) {
                    File file = new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/" + generation + "/" + i);
                    neuralNetworksArray.add(new NeuralNetwork(file));
                }
            }


            generation++;
            for (int i = 50; i < 100; i++) {
                neuralNetworksArray.get(i).gewichteBiaseLeichtVerändern(mutatePossability);
                neuralNetworksArray.get(i).setHowGeneratet("RandomChanceToMutate. Probability: " + mutatePossability);
            }

            neuralNetworksArray.parallelStream().forEach(element -> {
                element.generationErhöhen();
                element.setScore(Partie.nnPartieSpielenLassen(new NNPlayer(element), gegner, 40, score));
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

    static void generateTwentyTwentyTwentyTwentyTwenty(int score, int[] layers, Player gegner) throws IOException {
        ArrayList<NeuralNetwork> neuralNetworksArray = new ArrayList<>();
        File[] ordners = new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/").listFiles(File::isDirectory);
        int generation = 0;
        if (!(ordners == null)) {
            generation = ordners.length;
        }


        if (generation == 0) {
            neuralNetworksArray = makeNewRandom(layers);
        } else {
            for (int i = 0; i < 20; i++) {
                neuralNetworksArray.add(new NeuralNetwork(new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/" + generation + "/" + i)));
            }
        }

        while (true) {


            if (!(generation == 0)) {
                while (neuralNetworksArray.size() < 100){
                    int welcher = neuralNetworksArray.size()% 20;
                    File file = new File("Models/allDiffrentLayers/" + Arrays.toString(layers) + "/" + generation + "/" + welcher);
                    neuralNetworksArray.add(new NeuralNetwork(file));
                }
            }


            generation++;
            for (int i = 20; i < 40; i++) {
                neuralNetworksArray.get(i).gewichteBiaseLeichtVerändern(0.01);
                neuralNetworksArray.get(i).setHowGeneratet("RandomChanceToMutate. Probability: 0.01");
            }
            for (int i = 40; i < 60; i++) {
                neuralNetworksArray.get(i).gewichteBiaseLeichtVerändern(0.1);
                neuralNetworksArray.get(i).setHowGeneratet("RandomChanceToMutate. Probability: 0.1");
            }
            for (int i = 60; i < 80; i++) {
                neuralNetworksArray.get(i).gewichteBiaseLeichtVerändern(0.05);
                neuralNetworksArray.get(i).setHowGeneratet("RandomChanceToMutate. Probability: 0.05");
            }
            for (int i = 80; i < 100; i++) {
                Random random = new Random();
                NeuralNetwork parent1 = neuralNetworksArray.get(random.nextInt(0, 20));
                NeuralNetwork parent2 = neuralNetworksArray.get(random.nextInt(0, 20));
                List<float[][]> weigths = neuralNetworksArray.get(i).getWeights();
                List<float[]> biases = neuralNetworksArray.get(i).getBias();


                for (int j = 0; j < weigths.size(); j++) {
                    for (int k = 0; k < weigths.get(j).length; k++) {
                        for (int l = 0; l < weigths.get(j)[k].length; l++) {
                            int zwispei = random.nextInt(0, 2);
                            if (zwispei == 1) {
                                weigths.get(j)[k][l] = parent1.getWeights().get(j)[k][l];
                            } else {
                                weigths.get(j)[k][l] = parent2.getWeights().get(j)[k][l];
                            }
                        }
                    }
                }
                neuralNetworksArray.get(i).setWeights(weigths);
                for (int j = 0; j < biases.size(); j++) {
                    for (int k = 0; k < biases.get(j).length; k++) {
                        int zwispei = random.nextInt(0, 2);
                        if (zwispei == 1) {
                            biases.get(j)[k] = parent1.getBias().get(j)[k];
                        } else {
                            biases.get(j)[k] = parent2.getBias().get(j)[k];
                        }
                    }
                }
                neuralNetworksArray.get(i).setBias(biases);
                neuralNetworksArray.get(i).setHowGeneratet("Kreuzung der Eltern");
            }


            neuralNetworksArray.parallelStream().forEach(element -> {
                element.generationErhöhen();
                element.setScore(Partie.nnPartieSpielenLassen(new NNPlayer(element), gegner, 40, score));
            });
            neuralNetworksArray.sort(Comparator.comparingDouble(NeuralNetwork::getScore).reversed());

            for (int i = 0; i < 80; i++) {
                neuralNetworksArray.removeLast();
            }

            for (int i = 0; i < neuralNetworksArray.size(); i++) {
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
            System.out.println("Generation: " + generation + "; Score, des besten: " + neuralNetworksArray.getFirst().getScore() + "; Maximum: " + (score * 60) + "; Minimum:" + (-score * 20) + "; Mit so viel Punkten zum nächsten Bot: " + (score * 40 * 0.75) + "; Durchschnitt der besten 20: " + averideg + ";");
        }
    }

    private static ArrayList<NeuralNetwork> makeNewRandom(int[] layers) {

        ArrayList<NeuralNetwork> nNs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            nNs.add(new NeuralNetwork(layers));
            nNs.get(i).setGeneration(1);
        }
        return nNs;
    }
}
