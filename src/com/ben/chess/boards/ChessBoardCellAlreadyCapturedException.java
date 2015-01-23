package com.ben.chess.boards;

import com.ben.chess.pieces.ChessPiece;

/**
 * Created by ben on 2/6/14.
 */
public class ChessBoardCellAlreadyCapturedException extends Exception {
    public ChessBoardCellAlreadyCapturedException (ChessPiece piece) {
        super("The piece '" + piece.getName() + " owned by player " + piece.getPlayer() + ") is captured;" +
                " it cannot be captured another time");
    }
}
