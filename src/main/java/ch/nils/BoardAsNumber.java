package ch.nils;

import com.github.bhlangonijr.chesslib.*;

public class BoardAsNumber {
    public static float[] boardAsNumber(Board board){
        String ergebnisAlsString = "";
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.WHITE_PAWN));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.WHITE_KNIGHT));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.WHITE_BISHOP));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.WHITE_ROOK));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.WHITE_QUEEN));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.WHITE_KING));

        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.BLACK_PAWN));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.BLACK_KNIGHT));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.BLACK_BISHOP));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.BLACK_ROOK));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.BLACK_QUEEN));
        ergebnisAlsString = ergebnisAlsString + Long.toBinaryString(board.getBitboard(Piece.BLACK_KING));

        if(board.getSideToMove() == Side.WHITE){
            ergebnisAlsString = ergebnisAlsString + "1";
        }else{
            ergebnisAlsString = ergebnisAlsString + "0";
        }

        CastleRight zwispei = board.getCastleRight(Side.WHITE);
        if(zwispei == CastleRight.KING_AND_QUEEN_SIDE){
            ergebnisAlsString = ergebnisAlsString + "11";
        } else if (zwispei == CastleRight.KING_SIDE) {
            ergebnisAlsString = ergebnisAlsString + "10";
        }else if (zwispei == CastleRight.QUEEN_SIDE) {
            ergebnisAlsString = ergebnisAlsString + "01";
        }else{
            ergebnisAlsString = ergebnisAlsString + "00";
        }
        zwispei = board.getCastleRight(Side.BLACK);
        if(zwispei == CastleRight.KING_AND_QUEEN_SIDE){
            ergebnisAlsString = ergebnisAlsString + "11";
        } else if (zwispei == CastleRight.KING_SIDE) {
            ergebnisAlsString = ergebnisAlsString + "10";
        }else if (zwispei == CastleRight.QUEEN_SIDE) {
            ergebnisAlsString = ergebnisAlsString + "01";
        }else{
            ergebnisAlsString = ergebnisAlsString + "00";
        }
        float[] turns = new float[773];
        for (int i = 0; i < 773; i++) {
            turns[i] = Float.parseFloat(ergebnisAlsString.charAt(i) + "");
        }


        return turns;

    }
}
