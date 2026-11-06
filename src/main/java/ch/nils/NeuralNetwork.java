package ch.nils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {
    private List<float[][]> weights;
    private List<float[]> bias;
    private final int[] layers;
    public NeuralNetwork(int[] layers) {
        this.layers = layers;
    }




    public float howGoodIsBoard(float[] bitboard){
        for (int i = 0; i < weights.size(); i++) {
            for (int j = 0; j < weights.get(i).length; j++) {
                for (int k = 0; k < weights.get(i)[j].length; k++) {

                }
            }
        }







        return 3.2f;
    }











    void loadNetworkFromFile(){}
    void neuralNetworkInitialParameters(){
        Random random = new Random();
        List<float[][]> initalWeights = new ArrayList<>();
        List<float[]> initalBias = new ArrayList<>();
        for (int i = 1; i < layers.length; i++) {
            initalBias.add(new float[layers[i]]);
        }
        for (int i = 0; i < initalBias.size(); i++) {
            for (int j = 0; j < initalBias.get(i).length; j++) {
                float[] copyOfBiasOne = initalBias.get(i);
                copyOfBiasOne[j] = (float) (random.nextGaussian() / 10);
                initalBias.set(i, copyOfBiasOne);
            }
        }
        for (int i = 1; i < layers.length; i++) {
            initalWeights.add(new float[layers[i - 1]][layers[i]]);
        }
        for (int i = 0; i < initalWeights.size(); i++) {
            for (int j = 0; j < initalWeights.get(i).length; j++) {
                for (int k = 0; k < initalWeights.get(i)[j].length; k++) {
                    float[][] copyOfWeightOne = initalWeights.get(i);
                    copyOfWeightOne[j][k] = (float) (random.nextGaussian() / 10);
                    initalWeights.set(i, copyOfWeightOne);
                }
            }
        }
        this.weights = initalWeights;
        this.bias = initalBias;
    }
}
