package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;

/**
 * Created by ben on 2/6/14.
 */
public class QueenBehavior implements Behavior {

    BishopBehavior bishopBehavior;
    RookBehavior rookBehavior;

    public QueenBehavior () {
        this.bishopBehavior = new BishopBehavior();
        this.rookBehavior = new RookBehavior();
    }

    /**
     * Mashes the Bishop and Rook behaviors together to produce a Queen
     *
     * @param board The board that the piece is on
     * @param origin The cell the piece is currently at
     * @param destination The cell the piece wants to move to
     * @return Whether or not the movement is valid
     */
    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        return this.bishopBehavior.canMove(board, origin, destination)
            || this.rookBehavior.canMove(board, origin, destination);
    }
}
