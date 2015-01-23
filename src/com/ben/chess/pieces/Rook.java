package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardCellNotOccupiedException;
import com.ben.chess.boards.ChessBoardOutOfBoundsException;
import com.ben.chess.pieces.behaviors.Behavior;
import com.ben.chess.pieces.behaviors.RookBehavior;

/**
 * Created by ben on 2/6/14.
 */
public class Rook extends ChessPiece {

    private Behavior behavior;

    public Rook (Player player) throws InvalidPlayerException {
        super(player);

        this.behavior = new RookBehavior();
    }

    public Rook (Rook other) {
        super(other);

        this.behavior = new RookBehavior();
    }

    public String getName () {
        return "Rook";
    }

    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        return this.behavior.canMove(board, origin, destination);
    }

    public ChessPiece copy () {
        return new Rook(this);
    }

    public char getIconChar() {
        if(this.getPlayer() == Player.WHITE)
            return '\u2656';
        else
            return '\u265C';
    }
}
