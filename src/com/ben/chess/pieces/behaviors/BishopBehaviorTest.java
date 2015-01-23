package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.EmptyStandardChessBoard;
import com.ben.chess.pieces.ChessPiece;
import org.junit.Assert;

/**
 * Created by ben on 2/6/14.
 */
public class BishopBehaviorTest implements BehaviorTest {
    public void test (ChessPiece whiteBishop, ChessPiece blackBishop) throws Exception {
        ChessBoard board = new EmptyStandardChessBoard();

        // Check that moving up left to edge is valid
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(0, 0)));

        // Check that moving up right to edge is valid
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(7, 1)));

        // Check that moving down left to edge is valid
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(1, 7)));

        // Check that moving down right to edge is valid
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(7, 7)));

        // Check that moving up left by one is valid
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(0, 0)));

        // Check that moving up right by one is valid
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(7, 1)));

        // Check that moving down left by one is valid
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(1, 7)));

        // Check that moving down right by one is valid
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(7, 7)));

        // Check that capturing an adjacent piece is valid
        board.setCell(4, 4, whiteBishop);
        board.setCell(5, 5, blackBishop);
        Assert.assertTrue(whiteBishop.canMove(board, board.getCell(4, 4), board.getCell(5, 5)));
    }
}
