package com.ben.chess.games;

import com.ben.chess.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ben on 2/6/14.
 */
public class StandardChessGameTest {

    /**
     * This test executes the shortest known game of chess.
     * It is a simple integration test that ensures that
     * the game logic is sound, and pieces move as expected.
     */
    @Test
    public void testFoolsMate() throws Exception {
        ChessGame game = new StandardChessGame();
        boolean checkResolved = true;

        // White should get to start the game
        Assert.assertTrue(game.getTurn() == Player.WHITE);

        // But just for fun, it should throw if Black tries to go
        try {
            game.move(3, 6, 3, 5);
        }
        catch (MoveNotValidException e) {
            checkResolved = false;
        }
        finally {
            if(checkResolved)
                throw new Exception("It is White's turn, but Black tried to go");
        }

        // White moves second leftmost pawn down two cells
        game.move(1, 1, 1, 3);

        // No checks should have happened yet
        Assert.assertFalse(game.isBlackChecked());
        Assert.assertFalse(game.isWhiteChecked());

        // Black should get to go now
        Assert.assertTrue(game.getTurn() == Player.BLACK);

        // Black moves fourth leftmost pawn up one cell,
        // opening a diagonal for their queen
        game.move(3, 6, 3, 5);

        // No checks should have happened yet
        Assert.assertFalse(game.isBlackChecked());
        Assert.assertFalse(game.isWhiteChecked());

        // White should get to go now
        Assert.assertTrue(game.getTurn() == Player.WHITE);

        // White moves third leftmost pawn down two cells
        game.move(2, 1, 2, 3);

        // No checks should have happened yet
        Assert.assertFalse(game.isBlackChecked());
        Assert.assertFalse(game.isWhiteChecked());

        // Black should get to go now
        Assert.assertTrue(game.getTurn() == Player.BLACK);

        // Black moves queen into checking position
        game.move(4, 7, 0, 3);

        // And the board should know that a check has happened
        Assert.assertFalse(game.isBlackChecked());
        Assert.assertTrue(game.isWhiteChecked());

        // White should get to go now
        Assert.assertTrue(game.getTurn() == Player.WHITE);

        // White moves a pawn, which doesn't help. It should throw!
        try {
            game.move(3, 1, 3, 3);
        }
        catch (MoveNotValidException e) {
            checkResolved = false;
        }
        finally {
            if(checkResolved)
                throw new Exception("White is in check and this move did not help");
        }

        // White tries to retreat their king off the board
        try {
            game.move(4, 0, 4, -1);
        }
        catch (MoveNotValidException e) {
            checkResolved = false;
        }
        finally {
            if(checkResolved)
                throw new Exception("White should not be able to move their king off the board");
        }
    }

    /**
     * This test just tries to capture another pawn
     */
    @Test
    public void testPawnCapture() throws Exception {
        ChessGame game = new StandardChessGame();
        boolean checkResolved = true;

        // White should get to start the game
        Assert.assertTrue(game.getTurn() == Player.WHITE);

        // White moves leftmost pawn down two cells
        game.move(1, 1, 1, 3);

        // Black moves second leftmost pawn up two cells
        game.move(2, 6, 2, 4);

        // White captures black's pawn diagonally
        game.move(1, 3, 2, 4);

        Assert.assertTrue(game.getTurn() == Player.BLACK);

        // Undo that capture
        game.undo();

        // Black's pawn should still be there
        Assert.assertTrue(game.getBoard().getCell(2, 4).getPiece().getPlayer() == Player.BLACK);

        // White's pawn should still be there
        Assert.assertTrue(game.getBoard().getCell(1, 3).getPiece().getPlayer() == Player.WHITE);

        // It should be white's turn again
        Assert.assertTrue(game.getTurn() == Player.WHITE);
    }
}
