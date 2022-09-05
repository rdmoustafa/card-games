package games.basra;

import components.card.Card;
import components.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class BasraGame {

    public void launch() {
        Card SevenOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.Value.SEVEN);
        Scanner userIn = new Scanner(System.in);
        System.out.println("Hello, welcome to java.game.basra.Basra.");
        Basra game = new Basra(1);
        game.setupBasra();
        ArrayList<Card> ard = game.getFloor();
        Player player = game.getPlayer(0);
        Player com = game.getPlayer(1);
        ArrayList<Card> wonCardsPlayer = player.getCardsWonByPlayer();
        int playerPoints = player.getPlayerPoints();
        ArrayList<Card> wonCardsCom = com.getCardsWonByPlayer();
        int computerPoints = com.getPlayerPoints();

        System.out.println("\n \n" + player.getPlayerName() + "'s cards are: ");
        ArrayList<Card> playerCards = player.getPlayerHand();
        StringBuilder playerCardInfo = new StringBuilder("     ");
        int i = 0;
        for (Card c : playerCards) {
            String card = game.printingSingleCardWithValue(c);
            playerCardInfo.append("(").append(++i).append(") ").append(card).append(" | ");
        }
        System.out.println(playerCardInfo);
        ArrayList<Card> comCards = com.getPlayerHand();

        System.out.println("What card will you put down?");
        int cardNum = -1;
        boolean validInt = false;
        while (!validInt) {
            String cardPutDown = userIn.nextLine();
            try {
                cardNum = Integer.parseInt(cardPutDown) - 1;
                int handSize = player.getHandSize();
                if (cardNum < handSize) {
                    Card putDown = player.getPlayerHand().get(cardNum);
                    ard.add(putDown);
                    validInt = true;
                }
                else { System.out.println("Please enter a different number that exists"); }
            }
            catch (NumberFormatException e) { System.out.println("Error. Please enter a valid number"); }
        }

        Card playerPutDownCard = playerCards.get(cardNum);
        player.removeCard(playerPutDownCard);
        if (playerPutDownCard.getValue() == Card.Value.JACK) { game.JackPlayed(player, playerPutDownCard); }
        else if (playerPutDownCard.equals(SevenOfDiamonds)) { game.specialSevenPlayed(player, playerPutDownCard); }
        else { game.regularCard(player, playerPutDownCard); }
    }
}
