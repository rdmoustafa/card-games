import games.basra.BasraGame;
import games.gofish.GoFishGame;
import games.pontoon.PontoonGame;

import java.util.Scanner;

public class GameLaunch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                """
                    Which Card Game do you want to play
                    1) Basra
                    2) Pontoon
                    3) Go Fish
                """);

        String input = scanner.nextLine();

        if (input.equals("1") || input.equalsIgnoreCase("basra")) {
            System.out.println("Welcome to Basra");
            BasraGame basraGame = new BasraGame();
            basraGame.launch();
        }
        else if (input.equals("2") || input.equalsIgnoreCase("pontoon")) {
            System.out.println("Welcome to Pontoon");
            PontoonGame pontoonGame = new PontoonGame();
            pontoonGame.launch();
        }
        else {
            GoFishGame goFishGame = new GoFishGame();
            goFishGame.launch();

        }

    }
}
