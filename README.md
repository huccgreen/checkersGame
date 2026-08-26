# Checkers

A Swing-based desktop implementation of Checkers (English draughts) for two players sharing one keyboard/mouse.

## Requirements

A JDK (not just a JRE) is required to compile. Java 8+ works.

## Build & run

```
javac Checkers.java ExitConfirmDialog.java
java Checkers
```

Run these commands from the project root, since the piece images (`player1.png`, `player2.png`, `P1KING.png`, `P2KING.png`) are loaded from the current working directory.

## How to play

- Click a piece, then click a diagonal destination square to move it.
- Move two squares diagonally over an opponent's piece to capture it.
- Reaching the far row crowns a piece "king," letting it move diagonally in any direction.
- Live scores (captures made) are shown at the top of the board.
- The game ends when one player has no pieces left.
