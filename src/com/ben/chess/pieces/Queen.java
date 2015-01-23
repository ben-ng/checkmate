package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.pieces.behaviors.Behavior;
import com.ben.chess.pieces.behaviors.QueenBehavior;

/**
 * Created by ben on 2/6/14.
 */
public class Queen extends ChessPiece {

    private Behavior behavior;

    public Queen (Player player) throws InvalidPlayerException {
        super(player);

        this.behavior = new QueenBehavior();
    }

    public Queen (Queen other) {
        super(other);

        this.behavior = new QueenBehavior();
    }

    public String getName () {
        return "Queen";
    }

    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        return this.behavior.canMove(board, origin, destination);
    }

    public ChessPiece copy () {
        return new Queen(this);
    }

    public char getIconChar() {
        if(this.getPlayer() == Player.WHITE)
            return '\u2655';
        else
            return '\u265B';
    }
}
