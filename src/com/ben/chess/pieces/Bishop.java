package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.pieces.behaviors.Behavior;
import com.ben.chess.pieces.behaviors.BishopBehavior;

/**
 * Created by ben on 2/6/14.
 */
public class Bishop extends ChessPiece {

    private Behavior behavior;

    public Bishop (Player player) throws InvalidPlayerException {
        super(player);

        this.behavior = new BishopBehavior();
    }

    public Bishop (Bishop other) {
        super(other);

        this.behavior = new BishopBehavior();
    }

    public String getName () {
        return "Bishop";
    }

    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        return this.behavior.canMove(board, origin, destination);
    }

    public ChessPiece copy () {
        return new Bishop(this);
    }

    public char getIconChar() {
        if(this.getPlayer() == Player.WHITE)
            return '\u2657';
        else
            return '\u265D';
    }
}
