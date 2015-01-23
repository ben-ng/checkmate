package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardCellNotOccupiedException;

/**
 * Created by ben on 2/6/14.
 */
public class Pawn extends ChessPiece {
    public Pawn (Player player) throws InvalidPlayerException {
        super(player);
    }

    public Pawn (Pawn other) {
        super(other);
    }

    public String getName () {
        return "Pawn";
    }

    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        int yDiff = origin.getY() - destination.getY();
        int absYDiff = Math.abs(yDiff);
        boolean isTwoCellMove = absYDiff == 2;

        // White pawns can only move downwards (negative y)
        if(this.getPlayer() == Player.WHITE && yDiff >= 0)
            return false;

        // Black pawns can only move upwards (positive y)
        else if(this.getPlayer() == Player.BLACK && yDiff <= 0)
            return false;

        // There is no way to move two squares vertically
        if(absYDiff > 2)
            return false;

        // The movement can only be two squares if it is the first move
        if(isTwoCellMove && !this.isFirstMove())
            return false;

        // If the movement is diagonal
        if(origin.getX() - destination.getX() != 0) {
            // Then it cannot also be a two-square movement
            if(isTwoCellMove)
                return false;

            // Then there must be a piece at the destination
            if(!destination.isOccupied())
                return false;

            // And it must be possible to capture it
            if(!origin.getPiece().canCapture(destination.getPiece()))
                return false;
        }

        return true;
    }

    public ChessPiece copy () {
        return new Pawn(this);
    }

    public char getIconChar() {
        if(this.getPlayer() == Player.WHITE)
            return '\u2659';
        else
            return '\u265F';
    }
}
