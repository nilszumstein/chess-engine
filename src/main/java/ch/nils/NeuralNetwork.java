package ch.nils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {
    private List<float[][]> weights;
    private List<float[]> bias;



    private int[] layers;
    private double score;
    private int generation;



    public NeuralNetwork(File file) {
        loadNetworkFromFile(file);
    }
    public NeuralNetwork(int[] layers) {
        this.layers = layers;
        neuralNetworkInitialParameters();
    }


    void loadNetworkFromFile(File file){
        try (DataInputStream fileImporter = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            List<float[][]> initalWeights = new ArrayList<>();
            List<float[]> initalBias = new ArrayList<>();
            this.score = fileImporter.readDouble();
            this.generation = fileImporter.readInt();
            int wieVieleSchichtenHatLayers = fileImporter.readInt();
            this.layers = new int[wieVieleSchichtenHatLayers];
            for (int i = 0; i < wieVieleSchichtenHatLayers; i++) {
                this.layers[i] = fileImporter.readInt();
            }
            for (int i = 1; i < layers.length; i++) {
                initalBias.add(new float[layers[i]]);
            }
            for (int i = 0; i < initalBias.size(); i++) {
                for (int j = 0; j < initalBias.get(i).length; j++) {
                    float[] copyOfBiasOne = initalBias.get(i);
                    copyOfBiasOne[j] = fileImporter.readFloat();
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
                        copyOfWeightOne[j][k] = fileImporter.readFloat();
                        initalWeights.set(i, copyOfWeightOne);
                    }
                }
            }
            this.weights = initalWeights;
            this.bias = initalBias;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






    void saveNetworkToFile(File file) throws IOException {
        Files.createDirectories(Path.of(file.getParent()));
        try(DataOutputStream  stream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
            stream.writeDouble(score);
            stream.writeInt(generation);
            stream.writeInt(layers.length);
            for (int i = 0; i < layers.length; i++) {
                stream.writeInt(layers[i]);
            }
            for (int i = 0; i < bias.size(); i++) {
                for (int j = 0; j < bias.get(i).length; j++) {
                    stream.writeFloat(bias.get(i)[j]);
                }
            }
            for (int i = 0; i < weights.size(); i++) {
                for (int j = 0; j < weights.get(i).length; j++) {
                    for (int k = 0; k < weights.get(i)[j].length; k++) {
                        stream.writeFloat(weights.get(i)[j][k]);
                    }
                }
            }
        }
    }
    public void generationErhöhen(){
        this.generation++;
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

    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public int getGeneration() {
        return generation;
    }
    public void setGeneration(int generation) {
        this.generation = generation;
    }
    public int[] getLayers() {
        return layers;
    }
    public void setLayers(int[] layers) {
        this.layers = layers;
    }
    public List<float[][]> getWeights() {
        return weights;
    }
    public void setWeights(List<float[][]> weights) {
        this.weights = weights;
    }
    public List<float[]> getBias() {
        return bias;
    }
    public void setBias(List<float[]> bias) {
        this.bias = bias;
    }
}
