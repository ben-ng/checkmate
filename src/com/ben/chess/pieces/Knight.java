package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.boards.ChessBoardCellNotOccupiedException;
import com.ben.chess.pieces.behaviors.Behavior;
import com.ben.chess.pieces.behaviors.KnightBehavior;

/**
 * Created by ben on 2/6/14.
 */
public class Knight extends ChessPiece {

    Behavior behavior;

    public Knight (Player player) throws InvalidPlayerException {
        super(player);

        this.behavior = new KnightBehavior();
    }

    public Knight (Knight other) {
        super(other);
    }

    public String getName () {
        return "Knight";
    }

    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        return this.behavior.canMove(board, origin, destination);
    }

    public ChessPiece copy () {
        return new Knight(this);
    }

    public char getIconChar() {
        if(this.getPlayer() == Player.WHITE)
            return '\u2658';
        else
            return '\u265E';
    }
}
