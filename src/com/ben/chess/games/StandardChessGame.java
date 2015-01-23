package com.ben.chess.games;

import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.StandardChessBoard;

/**
 * Created by ben on 2/6/14.
 */
public class StandardChessGame extends ChessGame {

    public StandardChessGame () throws Exception {
        super();
    }

    public ChessBoard getNewBoard () throws Exception {
        return new StandardChessBoard();
    }
}
