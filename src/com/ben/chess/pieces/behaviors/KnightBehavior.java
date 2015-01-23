package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardCellNotOccupiedException;

/**
 * Created by ben on 2/13/14.
 */
public class KnightBehavior implements Behavior {
    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        int verticalDistance = Math.abs(destination.getY() - origin.getY());
        int horizontalDistance = Math.abs(destination.getX() - origin.getX());

        // The piece can move two squares vertically and one horizontally
        boolean isTypeOne = (verticalDistance == 2 && horizontalDistance == 1);

        // Or two squares horizontally, and one square vertically
        boolean isTypeTwo = (verticalDistance == 1 && horizontalDistance == 2);

        // Otherwise it is an invalid move
        if(!isTypeOne && !isTypeTwo)
            return false;

        // It can jump over pieces, but we still have to check if it can capture its landing zone

        // Is the destination is occupied, we must be able to capture it
        if(destination.isOccupied()) {
            if(!origin.getPiece().canCapture(destination.getPiece()))
                return false;
        }

        return true;
    }
}
