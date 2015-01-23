package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.StandardChessBoard;
import com.ben.chess.pieces.behaviors.RookBehaviorTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ben on 2/6/14.
 */
public class RookTest {
    @Test
    public void testCanMoveValid() throws Exception {

        Rook whiteRook = new Rook(Player.WHITE);
        Rook blackRook = new Rook(Player.BLACK);

        (new RookBehaviorTest()).test(whiteRook, blackRook);
    }

    @Test
    public void testCanMoveInvalid() throws Exception {
        Rook whiteRook = new Rook(Player.WHITE);
        Rook blackRook = new Rook(Player.BLACK);

        ChessBoard board = new StandardChessBoard();

        // Check that moving diagonally is invalid
        Assert.assertFalse(whiteRook.canMove(board, board.getCell(4, 4), board.getCell(3, 3)));

        // Check that moving to the same position is invalid
        Assert.assertFalse(whiteRook.canMove(board, board.getCell(4, 4), board.getCell(4, 4)));

        // Check that moving through another piece is invalid
        board.setCell(4, 4, whiteRook);
        board.setCell(5, 4, blackRook);
        Assert.assertFalse(whiteRook.canMove(board, board.getCell(4, 4), board.getCell(6, 4)));
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Rook", (new Rook(Player.WHITE)).getName());
    }
}
