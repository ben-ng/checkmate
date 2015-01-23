package com.ben.chess.boards;

import com.ben.chess.pieces.ChessPiece;

/**
 * Created by ben on 2/6/14.
 */
public class EmptyStandardChessBoard extends ChessBoard {

    public EmptyStandardChessBoard () throws Exception {
        super();
    }

    public EmptyStandardChessBoard (EmptyStandardChessBoard other)
            throws ChessBoardOutOfBoundsException
            , ChessBoardCellNotOccupiedException
            , ChessBoardCellAlreadyCapturedException
            , ChessBoardCellAlreadyOccupiedException {
        super(other);
    }

    public ChessBoardCell[][] getBoard () {
        ChessBoardCell[][] board = new ChessBoardCell[8][8];

        for(int i=0; i<8; ++i) {
            for(int j=0; j<8; ++j) {
                board[i][j] = new ChessBoardCell(i, j, true);
            }
        }

        return board;
    }

    public ChessBoard copy () throws ChessBoardInvalidException {
        try {
            return new EmptyStandardChessBoard(this);
        }
        catch (Exception e) {
            throw new ChessBoardInvalidException(e.toString());
        }
    }
}
