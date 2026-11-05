package ch.nils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {
    private List<Float[][]> weights;
    private List<Float[]> bias;
    private void neuralNetworkInitialParameters(int[] layers){
        Random random = new Random();
        List<Float[][]> weights = new ArrayList<>();
        List<Float[]> bias = new ArrayList<>();
        for (int i = 1; i < layers.length; i++) {
            bias.add(new Float[layers[i]]);
        }
        for (int i = 0; i < bias.size(); i++) {
            for (int j = 0; j < bias.get(i).length; j++) {
                Float[] copyOfBiasOne = bias.get(i);
                copyOfBiasOne[j] = (float) (random.nextGaussian() / 10);
                bias.set(i, copyOfBiasOne);
            }
        }
        for (int i = 1; i < layers.length; i++) {
            weights.add(new Float[layers[i - 1]][layers[i]]);
        }
        for (int i = 0; i < weights.size(); i++) {
            for (int j = 0; j < weights.get(i).length; j++) {
                for (int k = 0; k < weights.get(i)[j].length; k++) {
                    Float[][] copyOfWeightOne = weights.get(i);
                    copyOfWeightOne[j][k] = (float) (random.nextGaussian() / 10);
                    weights.set(i, copyOfWeightOne);
                }
            }
        }
        this.weights = weights;
        this.bias = bias;
    }
}
