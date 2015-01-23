package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardCellNotOccupiedException;
import com.ben.chess.boards.ChessBoardOutOfBoundsException;

/**
 * Created by ben on 2/6/14.
 */
public class RookBehavior implements Behavior {

    private UnobstructedBehavior unobstructedBehavior;

    public RookBehavior () {
        this.unobstructedBehavior = new UnobstructedBehavior();
    }

    /**
     * Reusable logic that checks if straight line movement is valid
     *
     * @param board The board that the piece is on
     * @param origin The cell the piece is currently at
     * @param destination The cell the piece wants to move to
     * @return Whether or not the movement is valid
     */
    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {

        int xDist = origin.getX() - destination.getX();
        int yDist = origin.getY() - destination.getY();

        // Rooks can only move in straight lines
        if(xDist != 0 && yDist != 0)
            return false;

        // They still have to move, though!
        if(xDist == 0 && yDist == 0)
            return false;

        // Is the destination is occupied, we must be able to capture it
        if(destination.isOccupied()) {
            if(!origin.getPiece().canCapture(destination.getPiece()))
                return false;
        }

        return this.unobstructedBehavior.canMove(board, origin, destination);
    }

}
