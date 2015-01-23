package com.ben.chess.games;

import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardCellNotOccupiedException;

/**
 * Created by ben on 2/6/14.
 */
public class MoveNotValidException extends Exception {
    public MoveNotValidException (String reason) {
        super(reason);
    }

    public MoveNotValidException (ChessBoardCell origin, ChessBoardCell destination)
        throws ChessBoardCellNotOccupiedException {
        super("Moving " + (origin.isOccupied() ? origin.getPiece() : "nothing") +
                " from " + origin + " to " + destination + " is not a valid move");
    }
}
