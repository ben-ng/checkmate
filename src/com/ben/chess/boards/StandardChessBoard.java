package com.ben.chess.boards;

import com.ben.chess.Player;
import com.ben.chess.gui.BoardGUI;
import com.ben.chess.pieces.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ben on 2/6/14.
 */
public class StandardChessBoard extends EmptyStandardChessBoard {

    public StandardChessBoard () throws Exception {
        super();

        this.setCell(0, 0, new Rook(Player.WHITE));
        this.setCell(1, 0, new Knight(Player.WHITE));
        this.setCell(2, 0, new Bishop(Player.WHITE));
        this.setCell(3, 0, this.getWhiteKing());
        this.setCell(4, 0, new Queen(Player.WHITE));
        this.setCell(5, 0, new Bishop(Player.WHITE));
        this.setCell(6, 0, new Knight(Player.WHITE));
        this.setCell(7, 0, new Rook(Player.WHITE));

        this.setCell(0, 1, new Pawn(Player.WHITE));
        this.setCell(1, 1, new Pawn(Player.WHITE));
        this.setCell(2, 1, new Pawn(Player.WHITE));
        this.setCell(3, 1, new Pawn(Player.WHITE));
        this.setCell(4, 1, new Pawn(Player.WHITE));
        this.setCell(5, 1, new Pawn(Player.WHITE));
        this.setCell(6, 1, new Pawn(Player.WHITE));
        this.setCell(7, 1, new Pawn(Player.WHITE));

        this.setCell(0, 6, new Pawn(Player.BLACK));
        this.setCell(1, 6, new Pawn(Player.BLACK));
        this.setCell(2, 6, new Pawn(Player.BLACK));
        this.setCell(3, 6, new Pawn(Player.BLACK));
        this.setCell(4, 6, new Pawn(Player.BLACK));
        this.setCell(5, 6, new Pawn(Player.BLACK));
        this.setCell(6, 6, new Pawn(Player.BLACK));
        this.setCell(7, 6, new Pawn(Player.BLACK));

        this.setCell(0, 7, new Rook(Player.BLACK));
        this.setCell(1, 7, new Knight(Player.BLACK));
        this.setCell(2, 7, new Bishop(Player.BLACK));
        this.setCell(3, 7, this.getBlackKing());
        this.setCell(4, 7, new Queen(Player.BLACK));
        this.setCell(5, 7, new Bishop(Player.BLACK));
        this.setCell(6, 7, new Knight(Player.BLACK));
        this.setCell(7, 7, new Rook(Player.BLACK));
    }
}
