package com.ben.chess.games;

import com.ben.chess.boards.*;
import com.ben.chess.Player;
import com.ben.chess.pieces.ChessPiece;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by ben on 2/6/14.
 */
public abstract class ChessGame {
    private ChessBoard board;
    private ArrayList<ChessBoard> history;

    private int whiteWinCount;
    private int blackWinCount;
    private int turnCount;

    private boolean isInCheck;
    private ChessPiece checkingPiece;
    private ChessPiece checkedKing;

    public ChessGame () throws Exception {
        this.board = this.getNewBoard();
        this.turnCount = 0;
        this.history = new ArrayList<ChessBoard>();
    }

    /**
     * Returns the player whose turn it currently is
     */
    public Player getTurn () {
        if(turnCount % 2 == 0)
            return Player.WHITE;
        else
            return Player.BLACK;
    }

    /**
     * Performs a move
     *
     * @param x1 The x-coordinate of the origin cell
     * @param y1 The y-coordinate of the origin cell
     * @param x2 The x-coordinate of the destination cell
     * @param y2 The y-coordinate of the destination cell
     * @throws MoveNotValidException
     */
    public void move (int x1, int y1, int x2, int y2)
        throws MoveNotValidException {

        ChessPiece piece;
        ChessBoard boardCopy;
        ChessBoardCell origin;
        ChessBoardCell destination;
        ChessBoardCell originCopy;
        ChessBoardCell destinationCopy;

        // Make sure that the move is in bounds
        try {
            origin = this.board.getCell(x1, y1);
            destination = this.board.getCell(x2, y2);
        }
        catch (ChessBoardOutOfBoundsException e) {
            throw new MoveNotValidException("That move is out of bounds of the chess board");
        }

        // Make sure that there is a piece at the origin
        if(!origin.isOccupied()) {
            throw new MoveNotValidException("There is no piece to move away from " + origin);
        }
        else {
            piece = origin.getPiece();
        }

        // Checks to see if the piece belongs to the expected player
        if(piece.getPlayer() != this.getTurn())
            throw new MoveNotValidException("It is not that player's turn");

        // Checks to see if the piece can travel to the destination
        if(!piece.canMove(this.board, origin, destination)) {
            throw new MoveNotValidException("The piece at " + origin + " cannot move to " + destination);
        }

        // If the player is currently in check, this checks to see if their move would
        // get them out of trouble.
        try {
            if(this.getTurn() == Player.WHITE && isWhiteChecked()) {
                boardCopy = this.board.copy();
                originCopy = boardCopy.getCell(x1, y1);
                destinationCopy = boardCopy.getCell(x2, y2);

                destinationCopy.setPiece(originCopy.getPiece());
                originCopy.setPiece(null);

                if(boardCopy.isWhiteChecked())
                    throw new MoveNotValidException("White is in check, and this move does not help");
            }
            else if (this.getTurn() == Player.BLACK && isBlackChecked()) {
                boardCopy = this.board.copy();
                originCopy = boardCopy.getCell(x1, y1);
                destinationCopy = boardCopy.getCell(x2, y2);

                destinationCopy.setPiece(originCopy.getPiece());
                originCopy.setPiece(null);

                if(boardCopy.isBlackChecked())
                    throw new MoveNotValidException("Black is in check, and this move does not help");
            }
        }
        catch (Exception e) {
            throw new MoveNotValidException(e.toString());
        }

        // Looks valid! Actually perform the act of moving the piece

        try {
            // Save state for undoing later
            this.history.add(this.board.copy());

            // Move the pieces
            destination.setPiece(origin.getPiece());
            origin.getPiece().didMove(this.board, origin, destination);
            origin.setPiece(null);
        }
        catch (Exception e) {
            throw new MoveNotValidException(e.toString());
        }

        // If the turn was successful, increment the turn counter
        ++this.turnCount;
    }

    /**
     * Subclasses should override this method to initialize
     * and return a new ChessBoard
     * @return A new ChessBoard in the initial configuration
     */
    abstract ChessBoard getNewBoard () throws Exception;

    public ChessBoard getBoard () {
        return this.board;
    }

    public boolean isWhiteChecked () throws ChessBoardInvalidException {
        return this.board.isWhiteChecked();
    }

    public boolean isBlackChecked () throws ChessBoardInvalidException {
        return this.board.isBlackChecked();
    }

    public void whiteForfeits() {
        ++this.blackWinCount;

        //Start on white
        this.turnCount = 0;
    }

    public void blackForfeits() {
        ++this.whiteWinCount;

        //Start on black
        this.turnCount = 1;
    }

    public int getWhiteWinCount() {
        return this.whiteWinCount;
    }

    public int getBlackWinCount() {
        return this.blackWinCount;
    }

    public void forfeit() {
        if(this.getTurn() == Player.WHITE)
            this.whiteForfeits();
        else
            this.blackForfeits();

        this.restart();
    }

    public void restart() {
        // Replace the board
        try {
            this.board = this.getNewBoard();
        }
        catch(Exception e) {
            // This should never happen since it would have thrown on the first game
            System.err.println(e);
        }

        // Clear the undo history
        this.history = new ArrayList<ChessBoard>();
    }

    public boolean getCanUndo() {
        return this.history.size() != 0;
    }

    public void undo() {
        this.board = this.history.get(this.history.size()-1);
        this.history.remove(this.board);
        --this.turnCount;
    }
}
