package games.pontoon;

import components.card.Card;
import components.card.Deck;
import components.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class PontoonGame { // Only 2 players [person and computers]

    public void launch() {
        Scanner userIn = new Scanner(System.in);

        System.out.print("""
                         Hello, this is Pontoon. There are two players, the house and you.
                         While playing, press enter to progress through the game
                         Press enter to continue
                         """); // this number plus the house)

        userIn.nextLine();
        int numPlayers = 2;
        System.out.println("Please enter your name");
        Pontoon game = new Pontoon(numPlayers); // this creates a deck too
        Deck gameDeck;
        Player player = game.getPlayer(0);
        Player com = game.getPlayer(1);
        System.out.printf("Welcome %s. The game has begun", player.getPlayerName());

        game.dealInitialCards();

        // House stuff
        System.out.println("\nThe House has the following cards in their hand: ");
        ArrayList<Card> houseCards = com.getPlayerHand();
        for (Card c : houseCards) {
            String s = game.printingSingleCardWithValue(c);
            System.out.println(s);
        }
        int bestHouseVal = com.getBestNumericalHandValue();
        System.out.println("Best house values are: " + bestHouseVal);
        userIn.nextLine();

        // Player stuff
        String name = player.getPlayerName();
        System.out.printf("%s (Player) has the following cards in their hand:\n", name);
        ArrayList<Card> playerCards = player.getPlayerHand();
        for (Card x : playerCards) {
            String s = game.printingSingleCardWithValue(x);
            System.out.println(s);
        }
        ArrayList<Integer> values1 = player.getNumericalHandValue();
        System.out.print("Possible hand values are: ");
        for (int x : values1) { System.out.print(x + " "); }
        int bestPlayerVal = player.getBestNumericalHandValue();
        System.out.print("\nThe best hand value is: " + bestPlayerVal);

        while (bestPlayerVal < 16) {
            userIn.nextLine();
            gameDeck = game.getDeck();
            Card c1 = gameDeck.dealRandomCard();
            player.dealToPlayer(c1);
            System.out.println();
            String cardStuff = game.printingSingleCardWithValue(c1);
            System.out.println("Your hand has a value less 16, you have been dealt the card: " + cardStuff);
            System.out.println("You now have the following cards in their hand: ");
            for (Card x : playerCards) {
                String s = game.printingSingleCardWithValue(x);
                System.out.println(s);
            }
            values1 = player.getNumericalHandValue();
            System.out.print("Possible hand values are: ");
            for (int x : values1) { System.out.print(x + " "); }
            bestPlayerVal = player.getBestNumericalHandValue();
            System.out.println("\nThe best hand value is: " + bestPlayerVal);
        }

        while (bestHouseVal < 16) {
            userIn.nextLine();
            gameDeck = game.getDeck();
            Card c1 = gameDeck.dealRandomCard();
            com.dealToPlayer(c1);
            System.out.println();
            String cardStuff = game.printingSingleCardWithValue(c1);
            System.out.println("The house had a value less 16, this card was dealt to the house: " + cardStuff);
            System.out.println("The house now has the following cards in their hands: ");
            for (Card x : houseCards) {
                String s = game.printingSingleCardWithValue(x);
                System.out.println(s);
            }
            bestHouseVal = com.getBestNumericalHandValue();
            System.out.println("The best house hand value is: " + bestHouseVal);
        }

        // any bust check
        boolean validPlayer = game.isValid(player);
        boolean validHouse = game.isValid(com);
        if (!validPlayer || !validHouse) {
            int decision = game.compareHands(player, com);
            String pName = player.getPlayerName();
            if (decision == -1) System.out.println(pName + " wins this round!");
            else if (decision == 1) System.out.println("The house wins this game. You lose.");
            else System.out.println("It was a draw");
            System.exit(0);
        }

        boolean draw = false;
        while (!draw) {
            System.out.println("\nWould you like to twist or stick? [twist = draw and stick = don't draw]");
            String action = userIn.nextLine();
            String twist = "twist";
            String stick = "stick";
            if (action.contains(twist)) {
                Deck currentDeck1 = game.getDeck();
                Card c2 = currentDeck1.dealRandomCard();
                player.dealToPlayer(c2);
                String cardStuff = game.printingSingleCardWithValue(c2);
                System.out.println("Your chose to twist, you have been dealt the card: " + cardStuff);
                System.out.println("You now have the following cards in their hands: ");
                for (Card x : playerCards) {
                    String s = game.printingSingleCardWithValue(x);
                    System.out.println(s);
                }
                bestPlayerVal = player.getBestNumericalHandValue();
                System.out.println("Your best hand value is now: " + bestPlayerVal);

                if (bestPlayerVal >= 21) {
                    int decision = game.compareHands(player, com);
                    String pName = player.getPlayerName();
                    if (decision == -1) System.out.println(pName + " wins this round!");
                    else if (decision == 1) System.out.println("The house wins this game. You lose.");
                    else System.out.println("It was a draw");
                    System.exit(0);
                }
            }
            if (action.contains(stick)) {
                System.out.println("You chose to stick.");
                draw = true;
            }
        }
        while (bestHouseVal < bestPlayerVal) {
            Deck d = game.getDeck();
            Card c2 = d.dealRandomCard();
            com.dealToPlayer(c2);
            String houseCard = game.printingSingleCardWithValue(c2);
            System.out.println("The house has been dealt the card: " + houseCard);
            bestHouseVal = com.getBestNumericalHandValue();
            System.out.println("The house's best numerical value is " + bestHouseVal);
        }


        game.compareHands(player, com);
        int decision = game.compareHands(player, com);
        String pName = player.getPlayerName();
        if (decision == -1) System.out.println(pName + " wins this round!");
        else if (decision == 1) System.out.println("The house wins this game. You lose.");
        else System.out.println("It was a draw");
        System.exit(0);
    }

}