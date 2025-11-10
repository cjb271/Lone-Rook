package chess;

import chess.pieces.Piece;
import chess.pieces.King;

import javax.swing.*;
import java.awt.*;

/**
 * Graphical user interface for the chess game.
 * The ChessGUI class creates an 8x8 chessboard using Swing,
 * displays the initial piece layout from Board,
 * and allows players to move pieces using mouse clicks.
 * It supports basic movement, capturing, turn switching, and
 * declares a winner when a king is captured.
 */
public class ChessGUI extends JFrame {

    /** The underlying game board that stores piece positions. */
    private final Board board;
    /** 2D array of buttons representing the 8x8 chessboard squares. */
    private final JButton[][] squares = new JButton[8][8];
    /** Currently selected source position for a move; null if none is selected. */
    private Position selectedPos = null;
    /** Tracks which player's turn it is (White or Black). */
    private String currentPlayerColor = "White";

    /** Background color for light squares on the board. */
    private static final Color LIGHT_SQUARE = new Color(255, 225, 175, 255);
    /** Background color for dark squares on the board. */
    private static final Color DARK_SQUARE = new Color(150, 100, 75);
    /** Font used for piece symbols. */
    private static final Font PIECE_FONT = new Font("Arial", Font.BOLD, 20);

    /**
     * Constructs the chess GUI, initializes the board state,
     * creates the 8x8 grid of buttons, and displays the initial pieces.
     */
    public ChessGUI() {
        board = new Board();
        board.initialize();

        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Completely close
        setSize(800, 800);
        setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        add(boardPanel, BorderLayout.CENTER);

        // 8x8 board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setFont(PIECE_FONT);
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setFocusPainted(false);
                button.setOpaque(true);
                button.setBorderPainted(false);

                Color baseColor = ((row + col) % 2 == 0) ? LIGHT_SQUARE : DARK_SQUARE;
                button.setBackground(baseColor);

                final int r = row;
                final int c = col;
                button.addActionListener(e -> handleSquareClick(r, c));

                squares[row][col] = button;
                boardPanel.add(button);
            }
        }

        updateBoardUI();
    }

    /**
     * Handles a click on a given board square.
     * If no square is currently selected, selects a piece belonging
     * to the current player.
     * If a square is already selected, attempts to move the selected piece
     * to the clicked square.
     * Cancels selection if the same square is clicked again.
     * If a move captures the opposing King, a dialog is shown and the
     * application terminates.
     */
    private void handleSquareClick(int row, int col) {
        if (selectedPos == null) {
            // First click
            Piece piece = board.getPieceAt(row, col);
            if (piece != null && piece.getColor().equalsIgnoreCase(currentPlayerColor)) {
                selectedPos = new Position(row, col);
                highlightSelectedSquare();
            }
        } else {
            // Second click
            if (selectedPos.row == row && selectedPos.col == col) {
                // Clicking same square cancels selection
                selectedPos = null;
                resetHighlights();
                return;
            }

            Piece target = board.getPieceAt(row, col);
            boolean kingCaptured = target instanceof King &&
                    !target.getColor().equalsIgnoreCase(currentPlayerColor);

            boolean moved = board.movePiece(selectedPos, new Position(row, col), currentPlayerColor);

            if (moved) {
                updateBoardUI();

                if (kingCaptured) {
                    JOptionPane.showMessageDialog(
                            this,
                            currentPlayerColor + " team wins! King captured.",
                            "Game Over",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    System.exit(0);
                }

                switchTurn();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid move. Try again.");
            }

            selectedPos = null;
            resetHighlights();
            updateBoardUI();
        }
    }
    /**
     * Switches the turn to the opposite player and updates the window title
     * to reflect the current player's turn.
     */
    private void switchTurn() {
        currentPlayerColor = currentPlayerColor.equals("White") ? "Black" : "White";
        setTitle("Chess - " + currentPlayerColor + "'s turn");
    }

    /**
     * Updates the visual representation of the board.
     * For each square, this method reads the corresponding Piece
     * from the Board and sets the button's text accordingly.
     */
    private void updateBoardUI() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPieceAt(row, col);
                JButton button = squares[row][col];
                button.setText(piece == null ? "" : piece.getSymbol());
            }
        }
    }

    /**
     * Highlights the currently selected square (if any) to visually indicate
     * which piece is chosen as the move source.
     * Any previous highlights are cleared before applying the new one.
     */
    private void highlightSelectedSquare() {
        resetHighlights();
        if (selectedPos != null) {
            squares[selectedPos.row][selectedPos.col].setBackground(Color.YELLOW); // highlights origin position before move
        }
    }

    /**
     * Resets all board squares to their default light/dark colors.
     * This is used to clear any selection highlighting after a move
     * or when a selection is cancelled.
     */
    private void resetHighlights() { // restore board to original colors
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color baseColor = ((row + col) % 2 == 0) ? LIGHT_SQUARE : DARK_SQUARE;
                squares[row][col].setBackground(baseColor);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGUI gui = new ChessGUI();
            gui.setVisible(true);
        });
    }
}
