package com.ben.chess.boards;

/**
 * Created by ben on 2/6/14.
 */
public class ChessBoardOutOfBoundsException extends Exception {
    public ChessBoardOutOfBoundsException (int x, int y) {
        super("Cell (" + x + "," + y + ") is not on the board");
    }
}
