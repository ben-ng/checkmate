package com.ben.chess.pieces;

import com.ben.chess.Player;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.StandardChessBoard;
import com.ben.chess.pieces.behaviors.KnightBehaviorTest;
import com.ben.chess.pieces.behaviors.BishopBehaviorTest;
import com.ben.chess.pieces.behaviors.RookBehaviorTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ben on 2/6/14.
 */
public class BansheeTest {
    @Test
    public void testCanMoveValid() throws Exception {

        Banshee whiteBanshee = new Banshee(Player.WHITE);
        Banshee blackBanshee = new Banshee(Player.BLACK);

        (new RookBehaviorTest()).test(whiteBanshee, blackBanshee);
        (new BishopBehaviorTest()).test(whiteBanshee, blackBanshee);
        (new KnightBehaviorTest()).test(whiteBanshee, blackBanshee);
    }

    @Test
    public void testCanMoveInvalid() throws Exception {
        Banshee whiteBanshee = new Banshee(Player.WHITE);
        Banshee blackBanshee = new Banshee(Player.BLACK);

        ChessBoard board = new StandardChessBoard();

        board.setCell(4, 4, whiteBanshee);

        // Check that moving in neither straight nor diagonal lines is invalid
        Assert.assertFalse(whiteBanshee.canMove(board, board.getCell(4, 4), board.getCell(7, 6)));

        // Check that moving to the same position is invalid
        Assert.assertFalse(whiteBanshee.canMove(board, board.getCell(4, 4), board.getCell(4, 4)));

        // Check that moving through another piece is invalid
        board.setCell(5, 5, blackBanshee);
        Assert.assertFalse(whiteBanshee.canMove(board, board.getCell(4, 4), board.getCell(6, 6)));
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Banshee", (new Banshee(Player.WHITE)).getName());
    }
}
