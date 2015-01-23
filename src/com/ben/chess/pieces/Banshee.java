package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.pieces.behaviors.Behavior;
import com.ben.chess.pieces.behaviors.KnightBehavior;
import com.ben.chess.pieces.behaviors.QueenBehavior;

/**
 * Created by ben on 2/6/14.
 */
public class Banshee extends ChessPiece {

    private Behavior knightBehavior;
    private Behavior queenBehavior;

    public Banshee (Player player) throws InvalidPlayerException {
        super(player);

        this.queenBehavior = new QueenBehavior();
        this.knightBehavior = new KnightBehavior();
    }

    public Banshee (Banshee other) {
        super(other);

        this.queenBehavior = new QueenBehavior();
        this.knightBehavior = new KnightBehavior();
    }

    public String getName () {
        return "Banshee";
    }

    public boolean canMove (ChessBoard board, ChessBoardCell origin, ChessBoardCell destination) {
        return this.queenBehavior.canMove(board, origin, destination) ||
                this.knightBehavior.canMove(board, origin, destination);
    }

    public ChessPiece copy () {
        return new Banshee(this);
    }

    public char getIconChar() {
        if(this.getPlayer() == Player.WHITE)
            return 'B';
        else
            return 'b';
    }
}
