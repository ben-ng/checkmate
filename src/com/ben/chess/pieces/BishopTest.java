package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.EmptyStandardChessBoard;
import com.ben.chess.pieces.behaviors.BishopBehaviorTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ben on 2/6/14.
 */
public class BishopTest {
    @Test
    public void testCanMoveValid() throws Exception {

        Bishop whiteBishop = new Bishop(Player.WHITE);
        Bishop blackBishop = new Bishop(Player.BLACK);

        (new BishopBehaviorTest()).test(whiteBishop, blackBishop);
    }

    @Test
    public void testCanMoveInvalid() throws Exception {
        Bishop whiteBishop = new Bishop(Player.WHITE);
        Bishop blackBishop = new Bishop(Player.BLACK);

        ChessBoard board = new EmptyStandardChessBoard();

        // Check that moving in straight lines is invalid
        Assert.assertFalse(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(4, 5)));

        // Check that moving to the same position is invalid
        Assert.assertFalse(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(4, 4)));

        // Check that moving through another piece is invalid
        board.setCell(4, 4, whiteBishop);
        board.setCell(5, 5, blackBishop);
        Assert.assertFalse(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(6, 6)));
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Bishop", (new Bishop(Player.WHITE)).getName());
    }
}
