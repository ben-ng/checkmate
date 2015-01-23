package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;

/**
 * Created by ben on 2/6/14.
 */
public class KingBehavior extends QueenBehavior {

    /**
     * Takes the Queen's behavior and limits it to one step
     *
     * @param board The board that the piece is on
     * @param origin The cell the piece is currently at
     * @param destination The cell the piece wants to move to
     * @return Whether or not the movement is valid
     */
    @Override
    public boolean canMove(ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        int xDist = Math.abs(origin.getX() - destination.getX());
        int yDist = Math.abs(origin.getY() - destination.getY());

        // Return true if the movement is no more than one square away in any direction
        return xDist <= 1 && yDist <= 1 && super.canMove(board, origin, destination);
    }
}
