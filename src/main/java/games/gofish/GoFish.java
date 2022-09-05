package games.gofish;

import components.card.Card;
import components.card.CardGame;
import components.player.Player;

import java.util.ArrayList;

public class GoFish extends CardGame {

    public GoFish(int n_players) { super(n_players);
        Player com = new Player("Computer");
        gamePlayers.add(com);}

    @Override
    public void dealInitialCards() {
        deck.shuffle();
        for (int i = 0; i < numPlayers; i++) {
            Player p = getPlayer(i);
            for (int x = 0; x < 7; x++) {
                Card c = deck.dealRandomCard();
                p.dealToPlayer(c);
            }
        }
    }

    @Override
    public int compareHands(Player hand1, Player hand2) { return 0; }

    public int checkCardinHand(Card c, Player player1, Player player2) {
        ArrayList<Card> playerHand = player1.playerHand;
        ArrayList<Card> comHand = player2.playerHand;
        boolean inPlayerHand = false;
        boolean inComHand = false;
        for (Card card : playerHand) {
            if (c.getColour() == card.getColour()) {
                if (c.getValue() == card.getValue()) {
                    inPlayerHand = true;
                }
            }
        }
        for (Card card : comHand) {
            if (c.getColour() == card.getColour()) {
                if (c.getValue() == card.getValue()) {
                    inComHand = true;
                }
            }
        }
        if (inPlayerHand && !inComHand) { return 1; }
        else if (!inPlayerHand && inComHand) { return -1; }
        return 0;
    }

}
