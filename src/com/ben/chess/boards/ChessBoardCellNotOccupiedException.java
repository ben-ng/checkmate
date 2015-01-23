package com.ben.chess.boards;

/**
 * Created by ben on 2/6/14.
 */
public class ChessBoardCellNotOccupiedException extends Exception {
    public ChessBoardCellNotOccupiedException (ChessBoardCell cell) {
        super("Cell (" + cell.getX() + "," + cell.getY() + ") is not occupied;" +
                " there is no piece to get");
    }
}
