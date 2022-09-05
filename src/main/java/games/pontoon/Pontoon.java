package games.pontoon;

import components.card.Card;
import components.card.CardGame;
import components.player.Player;

// Reem Moustafa 2138948
public class Pontoon extends CardGame {

    public Pontoon(int n_players) {
        super(n_players);
        Player com = new Player("House");
        gamePlayers.add(com);
    }

    @Override
    /*This abstract method deals the number of initial cards to each player in the java.game*/
    public void dealInitialCards() {
        deck.shuffle();
        // For each player deal 2 random cards
        for (int i = 0; i < numPlayers; i++) {
            Player person = gamePlayers.get(i);
            Card one = deck.dealRandomCard();
            person.dealToPlayer(one);
            Card two = deck.dealRandomCard();
            person.dealToPlayer(two);
        }
    }

    @Override
    /* This abstract method compares the hands of two players.
     * If hand1 is better than hand2 the method should return -1.
     * If hand2 is better than hand1 the method should return +1.
     * If the two hands are equal then the method should return 0.*/
    public int compareHands(Player hand1, Player hand2) {
        int valueOne = hand1.getBestNumericalHandValue();
        int valueTwo = hand2.getBestNumericalHandValue();
        // If any busts exist
        if (isValid(hand1) && !isValid(hand2))  { return -1; }
        else if (!isValid(hand1) && isValid(hand2)) { return 1; }
        else if (!isValid(hand1) && !isValid(hand2)){ return 0; }
        // If pontoon exists between the two
        if (isPontoon(hand1) && isPontoon(hand2)) { return 0; }
        else if (isPontoon(hand1) && !isPontoon(hand2)) { return -1; }
        else if (!isPontoon(hand1) && isPontoon(hand2)) { return 1; }
        // Now no java.game.pontoon.Pontoon exists - looking at 5 card trick
        else if (isFiveCardTrick(hand1) && isFiveCardTrick(hand2)) { return 0; }
        else if (isFiveCardTrick(hand1) && !isFiveCardTrick(hand2)) { return -1; }
        else if (!isFiveCardTrick(hand1) && isFiveCardTrick(hand2)) { return 1; }
        //Now no 5 card trick exists - looking at numerical values
        else return Integer.compare(valueTwo, valueOne);
    }

    public boolean isPontoon(Player playerHand) {
        int bestVal = playerHand.getBestNumericalHandValue();
        return playerHand.getHandSize() == 2 && bestVal == 21;
    }

    public boolean isFiveCardTrick(Player playerHand) {
        int bestVal = playerHand.getBestNumericalHandValue();
        return playerHand.getHandSize() == 5 && bestVal <= 21;
    }

    public boolean isValid(Player playerHand) {
        int bestVal = playerHand.getBestNumericalHandValue();
        return bestVal <= 21;
    }

}
