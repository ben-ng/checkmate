package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.StandardChessBoard;
import com.ben.chess.pieces.behaviors.KnightBehaviorTest;
import com.ben.chess.pieces.behaviors.RookBehaviorTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ben on 2/13/14.
 */
public class MongolianTest {
    @Test
    public void testCanMoveValid() throws Exception {

        Mongolian whiteMongolian = new Mongolian(Player.WHITE);
        Mongolian blackMongolian = new Mongolian(Player.BLACK);

        (new RookBehaviorTest()).test(whiteMongolian, blackMongolian);
        (new KnightBehaviorTest()).test(whiteMongolian, blackMongolian);
    }

    @Test
    public void testCanMoveInvalid() throws Exception {
        Mongolian whiteMongolian = new Mongolian(Player.WHITE);
        Mongolian blackMongolian = new Mongolian(Player.BLACK);

        ChessBoard board = new StandardChessBoard();

        board.setCell(4, 4, whiteMongolian);

        // Check that moving in neither straight nor diagonal lines is invalid
        Assert.assertFalse(whiteMongolian.canMove(board, board.getCell(4, 4), board.getCell(7, 6)));

        // Check that moving to the same position is invalid
        Assert.assertFalse(whiteMongolian.canMove(board, board.getCell(4, 4), board.getCell(4, 4)));

        // Check that moving through another piece is invalid
        board.setCell(5, 5, blackMongolian);
        Assert.assertFalse(whiteMongolian.canMove(board, board.getCell(4, 4), board.getCell(6, 6)));
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Mongolian", (new Mongolian(Player.WHITE)).getName());
    }
}
