package com.ben.chess.boards;

import com.ben.chess.Player;
import com.ben.chess.gui.BoardGUI;
import com.ben.chess.pieces.ChessPiece;
import com.ben.chess.pieces.King;

import javax.swing.*;

/**
 * Created by Ben on 2/6/14.
 */
public abstract class ChessBoard {
    protected ChessBoardCell[][] cells;

    private King whiteKing;
    private King blackKing;
    protected JPanel panel;

    public ChessBoard () throws Exception {
        this.cells = this.getBoard();
        this.whiteKing = new King(Player.WHITE);
        this.blackKing = new King(Player.BLACK);
    }

    public ChessBoard (ChessBoard other)
        throws ChessBoardOutOfBoundsException
            , ChessBoardCellNotOccupiedException
            , ChessBoardCellAlreadyCapturedException
            , ChessBoardCellAlreadyOccupiedException {

        int width = other.getWidth();
        int height = other.getHeight();

        this.cells = other.getBoard();

        for(int i=0; i<width; ++i) {
            for(int j=0; j<height; ++j) {
                if(other.getCell(i, j).isOccupied()) {
                    ChessPiece temp = other.getCell(i, j).getPiece();

                    if(temp == other.getWhiteKing()) {
                        this.whiteKing = (King) temp;
                    }
                    else if (temp == other.getBlackKing()) {
                        this.blackKing = (King) temp;
                    }

                    this.setCell(i, j, temp);

                }
            }
        }
    }

    /**
     * Subclasses should override this method to return the starting
     * configuration of the chessboard. Since during the course of a
     * games some cells might become valid or invalid (some variants
     * like Cheshire Chess allow this), the starting configuration is
     * not necessarily the final or intermediate configuration. Using
     * valid and invalid cells also makes it easier to support strange
     * board shapes like a hexagon.
     *
     * @return A two-dimensional array of ChessBoardCells
     */
    abstract ChessBoardCell[][] getBoard ();

    /**
     * Get a cell on the board. An exception will be thrown if the
     * requested cell is not on the board.
     *
     * @param x The x-coordinate of the cell to get
     * @param y The y-coordinate of the cell to get
     * @return The cell at that position on the board
     * @throws ChessBoardOutOfBoundsException
     */
    public ChessBoardCell getCell (int x, int y) throws ChessBoardOutOfBoundsException {
        for(int i=0, ii=this.cells.length; i<ii; ++i) {
            for(int j=0, jj=this.cells[i].length; j<jj; ++j) {
                if(x == i && y == j && this.cells[i][j].isValid()) {
                    return this.cells[i][j];
                }
            }
        }

        throw new ChessBoardOutOfBoundsException(x, y);
    }

    /**
     * Places a piece on the board
     *
     * @param x The x-coordinate of the cell to set
     * @param y The y-coordinate of the cell to set
     * @param piece The ChessPiece to place at the cell
     */
    public void setCell (int x, int y, ChessPiece piece)
        throws ChessBoardOutOfBoundsException
             , ChessBoardCellAlreadyCapturedException
             , ChessBoardCellAlreadyOccupiedException {

        for(int i=0, ii=this.cells.length; i<ii; ++i) {
            for(int j=0, jj=this.cells[i].length; j<jj; ++j) {
                if(x == i && y == j && this.cells[i][j].isValid()) {
                    this.cells[i][j].setPiece(piece);
                    return;
                }
            }
        }

        throw new ChessBoardOutOfBoundsException(x, y);
    }

    /**
     * Subclasses should override this to be more performant for their cell configuration
     * This is a really stupid way of doing it that should Just Work™ as long as you
     * subclass getCell properly.
     *
     * @param cell The cell to check
     * @return Whether or not the cell is in the board
     */
    public boolean containsCell (ChessBoardCell cell) {
        try {
            this.getCell(cell.getX(), cell.getY());
        }
        catch (ChessBoardOutOfBoundsException e) {
            return false;
        }

        return true;
    }

    public King getWhiteKing () {
        return this.whiteKing;
    }

    public King getBlackKing () {
        return this.blackKing;
    }

    /**
     * Finds a piece on the board and returns its coordinates
     *
     * @param piece
     * @return The ChessBoardCell the piece is on, null if it is
     *         not found
     */
    public ChessBoardCell find (ChessPiece piece) throws ChessBoardInvalidException {
        try {
            for(int i=0, ii=this.cells.length; i<ii; ++i) {
                for(int j=0, jj=this.cells[i].length; j<jj; ++j) {
                    if(this.cells[i][j].isValid() &&
                            this.cells[i][j].isOccupied() &&
                            this.cells[i][j].getPiece() == piece)
                        return this.cells[i][j];
                }
            }
        }
        catch (Exception e) {
            throw new ChessBoardInvalidException("A cell said it was occupied, but getPiece failed");
        }

        return null;
    }

    public boolean isWhiteChecked () throws ChessBoardInvalidException {
        ChessBoardCell whiteKingPosition = this.find(this.getWhiteKing());

        return isCellThreatened(whiteKingPosition);
    }

    public boolean isBlackChecked () throws ChessBoardInvalidException {
        ChessBoardCell blackKingPosition = this.find(this.getBlackKing());

        return isCellThreatened(blackKingPosition);
    }

    public boolean isCellThreatened (ChessBoardCell cell) throws ChessBoardInvalidException {
        for(int i=0, ii=this.cells.length; i<ii; ++i) {
            for(int j=0, jj=this.cells[i].length; j<jj; ++j) {
                if(this.cells[i][j].isValid() &&
                        this.cells[i][j].isOccupied() &&
                        this.cells[i][j].getPiece().canMove(this, this.cells[i][j], cell)
                        ) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getWidth () {
        return this.cells.length;
    }

    public int getHeight () {
        return this.cells[0].length;
    }

    public int getCellWidth () { return 20; }

    public abstract ChessBoard copy () throws ChessBoardInvalidException;
}
