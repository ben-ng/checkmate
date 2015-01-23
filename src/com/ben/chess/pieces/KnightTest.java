package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.pieces.behaviors.KnightBehaviorTest;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.EmptyStandardChessBoard;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ben on 2/6/14.
 */
public class KnightTest {
    @Test
    public void testCanMoveValid() throws Exception {

        Knight whiteKnight = new Knight(Player.WHITE);
        Knight blackKnight = new Knight(Player.BLACK);

        (new KnightBehaviorTest()).test(whiteKnight, blackKnight);
    }

    @Test
    public void testCanMoveInvalid() throws Exception {
        Knight whiteKnight = new Knight(Player.WHITE);
        Knight anotherWhiteKnight = new Knight(Player.WHITE);

        ChessBoard board = new EmptyStandardChessBoard();

        // Check that a white knight cannot move in lines
        Assert.assertFalse(whiteKnight.canMove(board, board.getCell(1, 1), board.getCell(0, 0)));
        Assert.assertFalse(whiteKnight.canMove(board, board.getCell(1, 1), board.getCell(0, 1)));
        Assert.assertFalse(whiteKnight.canMove(board, board.getCell(1, 1), board.getCell(1, 0)));

        // Check that a white knight must move
        Assert.assertFalse(whiteKnight.canMove(board, board.getCell(1, 1), board.getCell(1, 1)));

        // Check that a white knight cannot attack an allied knight
        board.setCell(1, 1, whiteKnight);
        board.setCell(3, 2, anotherWhiteKnight);
        Assert.assertFalse(whiteKnight.canMove(board, board.getCell(1, 1), board.getCell(3, 2)));
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Knight", (new Knight(Player.WHITE)).getName());
    }
}
