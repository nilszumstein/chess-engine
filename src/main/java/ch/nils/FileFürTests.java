package ch.nils;

import com.github.bhlangonijr.chesslib.Board;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static ch.nils.BoardAsNumber.boardAsNumber;

public class FileFürTests {
    public static void main(String[] args) throws IOException {
        NeuralNetwork nn = new NeuralNetwork(new int[]{773, 15, 15, 1});
        Board board = new Board();
        nn.saveNetworkToFile(new File("Models/model/wasas"));

    }
}
//"Models/allDiffrentLayers/LayerXYZ/genXXXX/ModelXX(1beste 50 schlechtest, der besseren hälfte)"