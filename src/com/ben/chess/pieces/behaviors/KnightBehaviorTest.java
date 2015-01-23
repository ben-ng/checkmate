package com.ben.chess.pieces.behaviors;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.EmptyStandardChessBoard;
import com.ben.chess.pieces.ChessPiece;
import org.junit.Assert;

/**
 * Created by ben on 2/6/14.
 */
public class KnightBehaviorTest implements BehaviorTest {
    public void test (ChessPiece whiteKnight, ChessPiece blackKnight) throws Exception {
        ChessBoard board = new EmptyStandardChessBoard();

        // Check that a white knight can move upwards in the four L shapes
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(2, 3)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(6, 3)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(3, 2)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(5, 2)));

        // Check that a white knight can move downwards in the four L shapes
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(2, 5)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(6, 5)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(3, 6)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(5, 6)));

        // Check that a white knight can jump over opposing pieces
        board.setCell(4, 4, whiteKnight);

        // Surround it on top
        board.setCell(3, 3, blackKnight);
        board.setCell(3, 4, blackKnight);
        board.setCell(3, 5, blackKnight);

        // Surround its sides
        board.setCell(4, 4, blackKnight);
        board.setCell(4, 5, blackKnight);

        // Surround its bottom
        board.setCell(5, 3, blackKnight);
        board.setCell(5, 4, blackKnight);
        board.setCell(5, 5, blackKnight);

        // Check that a white knight can still move upwards in the four L shapes
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(2, 3)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(6, 3)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(3, 2)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(5, 2)));

        // Check that a white knight can still move downwards in the four L shapes
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(2, 5)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(6, 5)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(3, 6)));
        Assert.assertTrue(whiteKnight.canMove(board, board.getCell(4, 4), board.getCell(5, 6)));
    }
}
