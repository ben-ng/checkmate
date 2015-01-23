package com.ben.chess.pieces;

import java.awt.Graphics2D;
import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardCellAlreadyCapturedException;

/**
 * Created by Ben on 2/6/14.
 */
public abstract class ChessPiece {
    private boolean isCaptured = false;
    private Player player;
    private boolean firstMove;

    public ChessPiece (Player player) {
        this.player = player;
        this.firstMove = true;
    }

    public ChessPiece (ChessPiece other) {
        this.player = other.getPlayer();
        this.firstMove = other.isFirstMove();
    }

    /**
     * Does not change the state of the piece, merely lets you know if it can move to the destination
     *
     * @param board The board that the piece is on
     * @param origin The cell the piece is currently at
     * @param destination The cell the piece wants to move to
     * @return
     */
    public abstract boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination);

    /**
     * A callback function that allows subclassed piece to change their state
     *
     * @param board The board that the piece is on
     * @param origin The cell the piece is was at
     * @param destination The cell the piece has moved to
     * @return
     */
    public void didMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        this.firstMove = false;
    }

    public boolean canCapture (ChessPiece piece) {
        return piece.getPlayer() != this.getPlayer();
    }

    public void setIsCaptured () throws ChessBoardCellAlreadyCapturedException {
        if(this.isCaptured)
            throw new ChessBoardCellAlreadyCapturedException(this);

        this.isCaptured = true;
    }

    public abstract String getName ();

    public Player getPlayer () {
        return this.player;
    }

    public boolean isFirstMove () {
        return this.firstMove;
    }

    public String toString () {
        return this.getName();
    }

    public abstract ChessPiece copy ();

    public abstract char getIconChar();
}
