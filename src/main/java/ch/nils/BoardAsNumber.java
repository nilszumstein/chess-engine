package ch.nils;

import com.github.bhlangonijr.chesslib.*;

public class BoardAsNumber {

    public static float[] boardAsNumber(Board board){
        Piece[] alleFigurenInRichtigerReienfolge = {Piece.WHITE_PAWN, Piece.WHITE_KNIGHT, Piece.WHITE_BISHOP, Piece.WHITE_ROOK, Piece.WHITE_QUEEN, Piece.WHITE_KING, Piece.BLACK_PAWN, Piece.BLACK_KNIGHT, Piece.BLACK_BISHOP, Piece.BLACK_ROOK, Piece.BLACK_QUEEN, Piece.BLACK_KING};
        String ergebnisAlsString = "";
        float[] grossesBitboard = new float[773];
        int wo = 0;

        for (int i = 0; i < alleFigurenInRichtigerReienfolge.length; i++) {
            long zwispei = board.getBitboard(alleFigurenInRichtigerReienfolge[i]);
            for (int j = 63; 0 <= j; j--) {
                grossesBitboard[wo] = (zwispei >>> j) & 1L;
                wo++;
            }
        }


        if(board.getSideToMove() == Side.WHITE){
            grossesBitboard[wo] = 1;
        }else{
            grossesBitboard[wo] = 0;
        }
        wo++;

        CastleRight zwispei = board.getCastleRight(Side.WHITE);
        if(zwispei == CastleRight.KING_AND_QUEEN_SIDE){
            grossesBitboard[wo] = 1;
        } else if (zwispei == CastleRight.KING_SIDE) {
            grossesBitboard[wo] = 1;
        }else if (zwispei == CastleRight.QUEEN_SIDE) {
            grossesBitboard[wo] = 0;
        }else{
            grossesBitboard[wo] = 0;
        }
        wo++;
        if(zwispei == CastleRight.KING_AND_QUEEN_SIDE){
            grossesBitboard[wo] = 1;
        } else if (zwispei == CastleRight.KING_SIDE) {
            grossesBitboard[wo] = 0;
        }else if (zwispei == CastleRight.QUEEN_SIDE) {
            grossesBitboard[wo] = 1;
        }else{
            grossesBitboard[wo] = 0;
        }
        wo++;
        zwispei = board.getCastleRight(Side.BLACK);
        if(zwispei == CastleRight.KING_AND_QUEEN_SIDE){
            grossesBitboard[wo] = 1;
        } else if (zwispei == CastleRight.KING_SIDE) {
            grossesBitboard[wo] = 1;
        }else if (zwispei == CastleRight.QUEEN_SIDE) {
            grossesBitboard[wo] = 0;
        }else{
            grossesBitboard[wo] = 0;
        }
        wo++;
        if(zwispei == CastleRight.KING_AND_QUEEN_SIDE){
            grossesBitboard[wo] = 1;
        } else if (zwispei == CastleRight.KING_SIDE) {
            grossesBitboard[wo] = 0;
        }else if (zwispei == CastleRight.QUEEN_SIDE) {
            grossesBitboard[wo] = 1;
        }else{
            grossesBitboard[wo] = 0;
        }


        return grossesBitboard;

    }
}
