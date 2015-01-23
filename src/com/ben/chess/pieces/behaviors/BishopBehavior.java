package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardCellNotOccupiedException;
import com.ben.chess.boards.ChessBoardOutOfBoundsException;

/**
 * Created by ben on 2/6/14.
 */
public class BishopBehavior implements Behavior {

    private UnobstructedBehavior unobstructedBehavior;

    public BishopBehavior () {
        this.unobstructedBehavior = new UnobstructedBehavior();
    }

    /**
     * Reusable logic that checks if diagonal movement is valid
     *
     * @param board The board that the piece is on
     * @param origin The cell the piece is currently at
     * @param destination The cell the piece wants to move to
     * @return Whether or not the movement is valid
     */
    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {

        int xDist = destination.getX() - origin.getX();
        int yDist = destination.getY() - origin.getY();

        // Bishops can only move in perfect diagonals
        if(Math.abs(xDist) != Math.abs(yDist))
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
