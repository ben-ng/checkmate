package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;

/**
 * Created by ben on 2/6/14.
 */
public interface Behavior {
    /**
     * Does not change the state of the piece, merely lets you know if it can move to the destination
     *
     * @param board The board that the piece is on
     * @param origin The cell the piece is currently at
     * @param destination The cell the piece wants to move to
     * @return Whether or not the movement is valid
     */
    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination);
}
