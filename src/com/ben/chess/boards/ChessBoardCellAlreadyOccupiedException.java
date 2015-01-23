package com.ben.chess.boards;
import com.ben.chess.pieces.ChessPiece;

/**
 * Created by ben on 2/6/14.
 */
public class ChessBoardCellAlreadyOccupiedException extends Exception {
    public ChessBoardCellAlreadyOccupiedException (ChessPiece piece) {
        super("The piece '" + piece.getName() + " is owned by player " + piece.getPlayer() + ");" +
                " it cannot be captured by a piece from the same player");
    }
}
