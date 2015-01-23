package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.*;
import com.ben.chess.pieces.behaviors.Behavior;
import com.ben.chess.pieces.behaviors.KingBehavior;

/**
 * Created by ben on 2/6/14.
 */
public class King extends ChessPiece {

    private Behavior behavior;

    public King (Player player) throws InvalidPlayerException {
        super(player);

        this.behavior = new KingBehavior();
    }

    public King (King other) {
        super(other);

        this.behavior = new KingBehavior();
    }

    public String getName () {
        return "King";
    }

    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        return this.behavior.canMove(board, origin, destination);
    }

    public ChessPiece copy () {
        return new King(this);
    }

    public char getIconChar() {
        if(this.getPlayer() == Player.WHITE)
            return '\u2654';
        else
            return '\u265A';
    }
}
