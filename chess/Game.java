package chess;

import chess.util.InputHandler;

public class Game {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    // Constructor
    public Game() {
        board = new Board();
        whitePlayer = new Player("White");
        blackPlayer = new Player("Black");
        currentPlayer = whitePlayer;
    }

    public void start() {
        System.out.println("Welcome to chess!");
        board.initialize();
        play();
    }

    public void play() {
        while (true) {
            board.display();
            System.out.println(currentPlayer.getColor() + "'s move:");
            String move = InputHandler.getMoveInput();
            if (move.equalsIgnoreCase("quit")) {
                System.out.println("Game Over.");
                break;
            }
            if (board.movePiece(move, currentPlayer.getColor())) {
                switchTurn();
            } else {
                System.out.println("Invalid move, try again.");// Can add exception handling later
            }
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
