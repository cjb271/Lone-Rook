package chess.util;

import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static String getMoveInput() {
        System.out.print("Enter current position first, then desired position (e.g., E2 E4). Enter 'quit' to exit game: ");
        return scanner.nextLine().trim();
    }
}
