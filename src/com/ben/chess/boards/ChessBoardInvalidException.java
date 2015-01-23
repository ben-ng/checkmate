package com.ben.chess.boards;

/**
 * Created by ben on 2/7/14.
 */
public class ChessBoardInvalidException extends Exception {

    public ChessBoardInvalidException () {
        super("This chess board has not been implemented correctly");
    }

    public ChessBoardInvalidException (String message) {
        super(message);
    }
}
