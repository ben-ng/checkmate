# Chess

## Package Outline

```
com.ben.chess
|
|- boards
   |- ChessBoard
      |- StandardChessBoard
|- games
   |- ChessGame
      |- StandardChessGame
|- pieces
   |- ChessPiece
      |- Rook
      |- Knight
      |- Bishop
      |- Queen
      |- King
      |- Pawn
   |- behaviors
      |- UnobstructedBehavior
         |- RookBehavior   --\
         |                    >-- QueenBehavior -> KingBehavior    
         |- BishopBehavior --/
|- Player
```

## Object Tree

```
ChessGame
|- ChessBoard
   |- ChessPiece
   |- ChessPiece
   |- ...
```

## Overview

### Pieces

ChessPieces extend from `com.ben.chess.pieces.ChessPiece` and should mostly concern themselves with overriding the `canMove` function.

#### Behaviors

Besides the Knight and Pawn, the other pieces in Chess share a lot in common. For example, a Queen is a mix of a Bishop and Rook. A King is just a Queen that can only move in a radius of 1.

Behaviors are reusable bits of logic that make overriding `canMove` simpler. For example, you can use `RookBehavior` to allow a piece to move in straight lights. Another common behavior is `UnobstructedBehavior`, which stops a piece from passing through other pieces.

As a bonus, behavior tests can be "stacked" too, which means you can assert that a Queen behaves like a Rook and Bishop instead of copying bits and pieces of both test suites over.

### Boards

ChessBoards extend from `com.ben.chess.boards.ChessBoard` and are two dimensional arrays of `ChessBoardCell`. They should override `getBoard` to define the playing field.

### Games

Games extend from `com.ben.chess.games.ChessGame`, which implements a game loop and rules common to most chess variants. The `move` method should be overridden to modify game logic.

## Tests

You can diff the GUI output with the reference images in `tests`

Start a chess game.

 * White should be on top
 * There should be a row of pawns on each side
 * There should be a rook on each corner
 * Next to the rooks should be a knight
 * Next to each knight should be a bishop
 * Between the bishops should be a Queen and a King

Move white's fourth leftmost pawn down one cell
Move black's third leftmost pawn up one cell
Move white's queen to check black's king

 * There should be a red stroke around black's king
 * Moving any piece other than the second leftmost pawn should be an invalid move
 * Moving the second leftmost pawn up one cell should end the check and clear the red stroke

Click surrender
 * Black should be given the option to accept a tie
 * If yes, the score should remain the same
 * If no, black should have one point, while white has zero