package com.ben.chess.boards;

import com.ben.chess.pieces.ChessPiece;

import java.awt.*;

/**
 * Created by Ben on 2/6/14.
 */
public class ChessBoardCell {
    // x and y coordinates are immutable properties declared upon initialization
    private int x;
    private int y;
    private boolean isValid;

    private ChessPiece piece;

    public ChessBoardCell (int x, int y, boolean isValid) {
        this(x, y, isValid, null);
    }

    public ChessBoardCell (int x, int y, boolean isValid, ChessPiece piece) {
        this.x = x;
        this.y = y;
        this.isValid = isValid;
        this.piece = piece;
    }

    /**
     * @return Whether or not this cell is occupied by a piece
     */
    public boolean isOccupied () {
        return this.piece != null;
    }

    /**
     * @return Whether or not this cell can be occupied by a piece
     */
    public boolean isValid () {
        return this.isValid;
    }

    /**
     * Places a piece in this cell, capturing an existing piece
     *
     * @param piece The piece to place in this cell.
     *                    Set to null to clear the cell.
     */
    public void setPiece (ChessPiece piece)
        throws ChessBoardCellAlreadyCapturedException
             , ChessBoardCellAlreadyOccupiedException {

        if(this.piece != null && piece != null) {
            // Don't allow capture by the same player
            if(piece != null && this.piece.getPlayer() == piece.getPlayer())
                throw new ChessBoardCellAlreadyOccupiedException(this.piece);

            this.piece.setIsCaptured();
        }

        this.piece = piece;
    }

    /**
     * @return The current occupant of the cell
     */
    public ChessPiece getPiece () {
        return this.piece;
    }

    /**
     * @return the x coordinate for this cell
     */
    public int getX () {
        return this.x;
    }

    /**
     * @return the y coordinate for this cell
     */
    public int getY () {
        return this.y;
    }

    public String toString () {
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    public void paintComponent (Graphics2D g, int cellWidth, boolean isChecked) {
        int leftEdge = this.getX() * cellWidth;
        int topEdge = this.getY() * cellWidth;

        if((this.getX() + this.getY()) % 2 == 0)
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.GRAY);

        g.fillRect(leftEdge, topEdge, cellWidth, cellWidth);


        g.setColor(Color.BLACK);

        if(this.isOccupied())
            g.drawChars(new char[]{this.getPiece().getIconChar()}, 0, 1, leftEdge, topEdge + cellWidth - 10);


        if(isChecked) {
            Stroke oldStroke = g.getStroke();
            int strokeWidth = 4;
            g.setStroke(new BasicStroke(strokeWidth));
            g.setColor(Color.RED);
            g.drawRect(leftEdge + strokeWidth/2 - 1, topEdge + strokeWidth/2 - 1
                    , cellWidth - strokeWidth + 1, cellWidth - strokeWidth + 1);
            g.setStroke(oldStroke);
        }
    }
}
