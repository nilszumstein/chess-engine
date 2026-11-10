package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static ch.nils.BoardAsNumber.boardAsNumber;

public class NnTrainer {

    public static void main(String[] args) throws IOException {
        //Erstes immer 773 letztes immer 1
        int[] layers = {773, 16, 16, 1};
        int wieVieleGenerationen = 100;

        ArrayList<NeuralNetwork> neuralNetworksArray = new ArrayList<>();



        //generation = wie viele ordner es in ordner mit LAYERS als name hat.
        int generation = 0;
        //das muss ich noch ändern

        if(generation == 0){
            neuralNetworksArray = makeNewRandom(layers);
        }else{
            neuralNetworksArray = resumeSession(layers);
        }

        while(wieVieleGenerationen > 0){
            if(!(generation == 0)){
                //Für stelle 50-99: die 50 der aktuell letzten(die aktuelle generation zahl) generation laden.(und nacher mutieren)
            }
            generation++;
            for (int i = 50; i < 100; i++) {
                neuralNetworksArray.get(i).gewichteBiaseLeichtVerändern();
            }
            for (int i = 0; i < 100; i++) {
                //neuralnetwork i die spiele spielen lassen, und danach den Score berechnen und setzten, bei score
            }

            //neuralNetworksArray.sort(Comparator.comparingInt(neuralNetworksArray.score()::score).reversed());

            for (int i = 0; i < 50; i++) {
                neuralNetworksArray.removeLast();
            }

            for (int i = 0; i < 50; i++) {
                //werte der arrays, unter ordner layers/generation/i
            }
            if(generation > 2)
            for (int i = 0; i < 49; i++) {
                //remove layers/(generation - 2)/(49 - i)
            }
            wieVieleGenerationen--;
        }


    }

    private static ArrayList<NeuralNetwork> resumeSession(int[] layers){
        /*
        hier muss ihc die 50 NNs der neusten(finde ich heraus indem ich schaue wie viele ordner hat es in ordner layers) generation
        ladenzund unten bei return, returnen.
         */
        return new ArrayList<NeuralNetwork>();
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





