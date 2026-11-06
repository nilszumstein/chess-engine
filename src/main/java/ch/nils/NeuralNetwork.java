package ch.nils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {
    private List<float[][]> weights;
    private List<float[]> bias;
    private int[] layers;

    //Wie ich das mit dem Filepath mache ist mir noch unklar.
    public NeuralNetwork() {
        //Dieser constrikrot ist für wenn cih ein Modell laden will
        //hier kommt dan acuh gleich noch der code, der die daten weights, bias und layers aus dem File lät
    }
    public NeuralNetwork(int[] layers) {
        //Dieser konstruktor ist, für wenn ich ein neues modell machen will und zwas am orx x speicerhn
        this.layers = layers;
        neuralNetworkInitialParameters();
    }








    //Mein gedanke war ncoh, dass jedes File ein eigenes file bekommt, und das einfahc in einem ordner ist, der Der ort auswählt, in der diese klasser erstellt wird.

    void loadNetworkFromFile(){
        //hier muss ich irgendwie das modell laden können.
    }
    void saveNetworkToFile(){
        //hier muss ihc irgendwie ein modell speichern können
    }



    public float howGoodIsBoard(float[] bitboard){
        List<Float> zwispeiLayer1 = new ArrayList<Float>();
        List<Float> zwispeiLayer2 = new ArrayList<Float>();
        for (int i = 0; i < bitboard.length; i++) {
            zwispeiLayer1.add(bitboard[i]);
        }
        float kleinesZwischenspeichern;
        for (int i = 0; i < weights.size(); i++) {
            for (int j = 0; j < layers[i + 1]; j++) {
                kleinesZwischenspeichern = 0f;
                for (int k = 0; k < layers[i]; k++) {
                    kleinesZwischenspeichern = kleinesZwischenspeichern + zwispeiLayer1.get(k) * weights.get(i)[k][j];
                }
                kleinesZwischenspeichern = kleinesZwischenspeichern + bias.get(i)[j];
                kleinesZwischenspeichern = (float) (1 / (1 + Math.exp(-kleinesZwischenspeichern)));
                zwispeiLayer2.add(kleinesZwischenspeichern);
            }
            zwispeiLayer1 = zwispeiLayer2;
            zwispeiLayer2 = new ArrayList<>();


        }
        return zwispeiLayer1.getFirst();
    }
    public void gewichteBiaseLeichtVerändern(){
        Random random = new Random();
        List<float[][]> initalWeights = this.weights;
        List<float[]> initalBias = this.bias;

        for (int i = 0; i < initalBias.size(); i++) {
            for (int j = 0; j < initalBias.get(i).length; j++) {
                float[] copyOfBiasOne = initalBias.get(i);
                copyOfBiasOne[j] = copyOfBiasOne[j] + (float) (random.nextGaussian() / 100);
                initalBias.set(i, copyOfBiasOne);
            }
        }
        for (int i = 0; i < initalWeights.size(); i++) {
            for (int j = 0; j < initalWeights.get(i).length; j++) {
                for (int k = 0; k < initalWeights.get(i)[j].length; k++) {
                    float[][] copyOfWeightOne = initalWeights.get(i);
                    copyOfWeightOne[j][k] = copyOfWeightOne[j][k] + (float) (random.nextGaussian() / 100);
                    initalWeights.set(i, copyOfWeightOne);
                }
            }
        }
        this.weights = initalWeights;
        this.bias = initalBias;
    }
    private void neuralNetworkInitialParameters(){
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
