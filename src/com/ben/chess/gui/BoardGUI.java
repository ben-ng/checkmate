package com.ben.chess.gui;

import javax.swing.*;
import com.ben.chess.boards.ChessBoard;
import com.ben.chess.boards.ChessBoardCell;
import com.ben.chess.games.ChessGame;
import com.ben.chess.games.MoveNotValidException;

import java.awt.*;

/**
 * Created by ben on 2/13/14.
 */
public class BoardGUI extends JPanel {
    private GameGUI gameGUI;
    private ChessBoard board;
    private ChessGame game;

    private int cellWidth;

    public BoardGUI(GameGUI gameGUI, ChessGame game, ChessBoard board) {
        this.cellWidth = 50;
        this.board = board;
        this.game = game;
        this.gameGUI = gameGUI;

        this.setPreferredSize(new Dimension(this.cellWidth * board.getWidth(), this.cellWidth * board.getHeight()));
        this.setLayout(new BorderLayout());

        // A neat wrapper around the atrocious listener interface
        this.addMouseListener(new BoardListener(this));
    }

    public void onDrag(Point start, Point end) {
        try {
            this.game.move(start.x / this.cellWidth, start.y / this.cellWidth, end.x / this.cellWidth, end.y / this.cellWidth);
        }
        catch (MoveNotValidException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        finally {
            this.repaint();
        }

        this.gameGUI.onMove();
    }

    public void paintComponent(Graphics g) {
        int width = this.board.getWidth();
        int height = this.board.getHeight();

        Graphics2D graphics2D = (Graphics2D) g;
        GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphics2D.setFont(new Font("LucidaSans", Font.PLAIN, this.cellWidth));

        for(int x=0; x<width; ++x) {
            for(int y=0; y<height; ++y) {
                try {
                    ChessBoardCell cell = this.board.getCell(x, y);
                    boolean isAKing = cell.getPiece() == this.board.getWhiteKing() || cell.getPiece() == this.board.getBlackKing();
                    boolean isChecked = isChecked = isAKing && this.board.isCellThreatened(this.board.getCell(x, y));

                    cell.paintComponent(graphics2D, this.cellWidth, isChecked);
                }
                catch (Exception e) {
                    System.err.println(e.getStackTrace());
                }
            }
        }
    }

    public int getWidth() {
        return this.board.getWidth() * this.cellWidth;
    }

    public int getHeight() {
        return this.board.getHeight() * this.cellWidth;
    }
}
