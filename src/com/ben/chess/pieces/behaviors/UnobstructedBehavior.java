package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardOutOfBoundsException;

/**
 * Created by ben on 2/6/14.
 */
public class UnobstructedBehavior implements Behavior {

    /**
     * Reusable logic that checks if the cells between two cells are obstructed
     *
     * @param board The board that the piece is on
     * @param origin The cell the piece is currently at
     * @param destination The cell the piece wants to move to
     * @return True if the move is valid
     */
    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {

        int xDist = destination.getX() - origin.getX();
        int yDist = destination.getY() - origin.getY();
        int originX = origin.getX();
        int originY = origin.getY();

        // Check the cells on the path and make sure they aren't obstructed
        try {
            // Each iteration will add this to the coordinate
            int yStep = (yDist == 0 ? 0 : Math.abs(yDist) / yDist);
            int xStep = (xDist == 0 ? 0 : Math.abs(xDist) / xDist);
            int iterations = Math.abs(xDist) - 1;

            originX += xStep;
            originY += yStep;

            for(int i=0; i<iterations; ++i) {
                if(board.getCell(originX, originY).isOccupied())
                    return false;

                originX += xStep;
                originY += yStep;
            }
        }
        catch (ChessBoardOutOfBoundsException e) {
            return false;
        }

        return true;
    }
}
