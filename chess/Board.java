package chess;

import chess.pieces.*;
import java.util.HashMap;

/**
 * Represents the chessboard and handles initialization, display,
 * and piece movement.
 * The Board class manages an 8x8 grid of Piece objects,
 * manages starting positions for all chess pieces, and provides
 * methods for moving pieces and changing their positions.
 */
public class Board {
    /** 2D array representing the chessboard grid (8x8). */
    private Piece[][] board = new Piece[8][8];// 2D array that stores chess pieces
    /** Maps column letters ('A'–'H') to their numeric equivalent (0–7). */
    private HashMap<String, Integer> fileMap = new HashMap<>();

    /**
     * Constructs a new Board object and maps
     * columns A–H to 0–7.
     */
    public Board() {
        for (int i = 0; i < 8; i++) {
            fileMap.put(String.valueOf((char) ('A' + i)), i);
        }
    }

    /**
     * Sets up the initial starting positions of all chess pieces.
     * Black pieces are placed on rows 0–1; white pieces are placed on rows 6–7.
     * Each piece type (Pawn, Rook, Knight, Bishop, Queen, King)
     * is instantiated and positioned in its correct starting location.
     */
    public void initialize() {
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("Black", new Position(1, i));
            board[6][i] = new Pawn("White", new Position(6, i));
        }
        // Rooks
        board[0][0] = new Rook("Black", new Position(0, 0));
        board[0][7] = new Rook("Black", new Position(0, 7));
        board[7][0] = new Rook("White", new Position(7, 0));
        board[7][7] = new Rook("White", new Position(7, 7));

        // Knights
        board[0][1] = new Knight("Black", new Position(0, 1));
        board[0][6] = new Knight("Black", new Position(0, 6));
        board[7][1] = new Knight("White", new Position(7, 1));
        board[7][6] = new Knight("White", new Position(7, 6));

        // Bishops
        board[0][2] = new Bishop("Black", new Position(0, 2));
        board[0][5] = new Bishop("Black", new Position(0, 5));
        board[7][2] = new Bishop("White", new Position(7, 2));
        board[7][5] = new Bishop("White", new Position(7, 5));

        // Queens
        board[0][3] = new Queen("Black", new Position(0, 3));
        board[7][3] = new Queen("White", new Position(7, 3));

        // Kings
        board[0][4] = new King("Black", new Position(0, 4));
        board[7][4] = new King("White", new Position(7, 4));
    }

    /**
     * Displays the chessboard in a text-based format on the console.
     * This method was primarily used for phase 1 of this project before GUI development
     * and is no longer directly used.
     * Each occupied square prints its piece symbol, while empty squares
     * are displayed as "##".
     */
    public void display() {
        System.out.println("    A   B   C   D   E   F   G   H");
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + " ");
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    System.out.print(" " + piece.getSymbol() + " ");
                } else {
                    System.out.print(" ## ");
                }
            }
            System.out.println(" " + (8 - row));
        }
        System.out.println("    A   B   C   D   E   F   G   H\n");
    }

    /**
     * Returns the Piece located at the specified row and column.
     */
    public Piece getPieceAt(int row, int col) {
        return board[row][col];
    }

    /**
     * Moves a chess piece on the board using Position objects.
     * This version is used by the GUI to process click-based moves.
     * It verifies that both positions are valid, ensures that the piece
     * belongs to the current player, and performs the move (with capture
     * if an opponent’s piece occupies the target square).
     */
    public boolean movePiece(Position from, Position to, String color) {
        try {
            if (from == null || to == null) return false; // No valid origin or destination square

            // both positions exist
            if (from.row < 0 || from.row > 7 || from.col < 0 || from.col > 7) return false;
            if (to.row < 0 || to.row > 7 || to.col < 0 || to.col > 7) return false;

            Piece piece = board[from.row][from.col]; // get piece being moved
            if (piece == null) return false; // no piece at origin

            if (!piece.getColor().equalsIgnoreCase(color)) return false; // allows moves by current player only

            // move with capture
            board[to.row][to.col] = piece;
            board[from.row][from.col] = null;
            piece.setPosition(to);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Processes a move entered as chess notation text (e.g., "E2 E4"),
     * converts it to Position objects, and delegates to the
     * coordinate-based version of the movePiece(Position, Position, String) method.
     * This version is no longer used directly in the GUI.
     */
    public boolean movePiece(String move, String color) {
        try {
            String[] parts = move.split(" ");
            if (parts.length != 2) return false;
            Position from = Position.fromChessNotation(parts[0]);
            Position to = Position.fromChessNotation(parts[1]);
            return movePiece(from, to, color); // overload of movePiece to reuse logic
        } catch (Exception e) {
            return false;
        }
    }
}
