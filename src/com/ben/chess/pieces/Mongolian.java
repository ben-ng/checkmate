package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.pieces.behaviors.Behavior;
import com.ben.chess.pieces.behaviors.KnightBehavior;
import com.ben.chess.pieces.behaviors.RookBehavior;

/**
 * Created by ben on 2/13/14.
 */
public class Mongolian extends ChessPiece {

    private Behavior knightBehavior;
    private Behavior rookBehavior;

    public Mongolian (Player player) throws InvalidPlayerException {
        super(player);

        this.rookBehavior = new RookBehavior();
        this.knightBehavior = new KnightBehavior();
    }

    public Mongolian (Mongolian other) {
        super(other);

        this.rookBehavior = new RookBehavior();
        this.knightBehavior = new KnightBehavior();
    }

    public String getName () {
        return "Mongolian";
    }

    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        return this.rookBehavior.canMove(board, origin, destination) ||
                this.knightBehavior.canMove(board, origin, destination);
    }

    public ChessPiece copy () {
        return new Mongolian(this);
    }

    public char getIconChar() {
        if(this.getPlayer() == Player.WHITE)
            return 'M';
        else
            return 'm';
    }
}
