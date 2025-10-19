package chess;

import chess.pieces.*;
import java.util.HashMap;

public class Board {
    private Piece[][] board = new Piece[8][8];// 2D array that stores chess pieces
    private HashMap<String, Integer> fileMap = new HashMap<>();

    // Maps columns A-H to rows 1-8
    public Board() {
        for (int i = 0; i < 8; i++) {
            fileMap.put(String.valueOf((char) ('A' + i)), i);
        }
    }

    // Sets piece starting positions
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

    // Shows board
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

    //Handles player moves, still need to implement chess rules
    public boolean movePiece(String move, String color) {
        try {
            String[] parts = move.split(" ");
            if (parts.length != 2) return false;
            Position from = Position.fromChessNotation(parts[0]);
            Position to = Position.fromChessNotation(parts[1]);

            Piece piece = board[from.row][from.col];
            if (piece == null || !piece.getColor().equalsIgnoreCase(color)) return false;

            board[to.row][to.col] = piece;
            board[from.row][from.col] = null;
            piece.setPosition(to);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
