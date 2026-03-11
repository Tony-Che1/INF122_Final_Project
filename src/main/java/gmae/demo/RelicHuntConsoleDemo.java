package gmae.demo;

import gmae.adventures.RelicHuntAdventure;
import gmae.engine.GameSession;
import gmae.model.Player;
import gmae.model.PlayerProfile;

import java.util.Scanner;

public final class RelicHuntConsoleDemo {

    private RelicHuntConsoleDemo() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Relic Hunt Console Demo");

            System.out.print("Player 1 name: ");
            String playerOneName = readName(scanner, "Player 1");

            System.out.print("Player 2 name: ");
            String playerTwoName = readName(scanner, "Player 2");

            Player playerOne = new Player(new PlayerProfile(playerOneName));
            Player playerTwo = new Player(new PlayerProfile(playerTwoName));
            GameSession session = new GameSession(scanner);

            session.startSession(playerOne, playerTwo, new RelicHuntAdventure());
        }
    }

    private static String readName(Scanner scanner, String fallback) {
        String line = scanner.nextLine().trim();
        return line.isEmpty() ? fallback : line;
    }
}
