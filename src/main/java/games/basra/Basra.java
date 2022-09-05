package games.basra;

import components.card.Card;
import components.card.CardGame;
import components.card.Deck;
import components.player.Player;

import java.util.ArrayList;

public class Basra extends CardGame {
    ArrayList<Card> floor = new ArrayList<>();

    public Basra(int n_players) {
        super(n_players);
        Player com = new Player("Computer");
        gamePlayers.add(com);
    }

    public ArrayList<Card> getFloor() { return floor; }
    public void setFloor(ArrayList<Card> floor) { this.floor = floor; }

    @Override
    public void dealInitialCards() {
        deck.shuffle();
        for (int i = 0; i < numPlayers; i++) {
            Player p = getPlayer(i);
            for (int x = 0; x < 4; x++) {
                Card c = deck.dealRandomCard();
                p.dealToPlayer(c);
            }
        }
    }
    @Override
    public int compareHands(Player hand1, Player hand2) { return 0; }

    public void setupBasra() {
        dealInitialCards(); // should include com and floor as players
        StringBuilder info = new StringBuilder("The cards on the floor are:\n    ");
        for (int i = 0; i < 4; i++) {
            Deck d = getDeck();
            Card c = d.dealRandomCard();
            floor.add(c);
            String card = printingSingleCardWithValue(c);
            info.append(card).append(" | ");
        }
        String printInfo = info.toString();
        System.out.print(printInfo);
    }

    public void basra(Player player) {
        int points = player.getPlayerPoints();
        points += 10;
    }

    public void JackPlayed(Player player, Card playedCard) {
        ArrayList<Card> wonCards = player.getCardsWonByPlayer();
        ArrayList<Card> ard = getFloor();
        wonCards.add(playedCard);
        if (ard.size() == 1) {
            Card onlyCard = ard.get(0);
            Card.Value valArd = onlyCard.getValue();
            Card.Value valPlayed = playedCard.getValue();
            if (valArd.equals(valPlayed)) {
                basra(player);
                wonCards.add(onlyCard);
                ard.remove(onlyCard);
            }
        }
        for (Card ardCard : ard) {
            wonCards.add(ardCard);
            ard.remove(ardCard);
        }
    }

    public void specialSevenPlayed(Player player, Card playedCard) {
        ArrayList<Card> wonCards = player.getCardsWonByPlayer();
        ArrayList<Card> ard = getFloor();
        int ardSum = 0;
        for (Card ardCard : ard) {
            int value = ardCard.getNormalNumericalValue();
            ardSum += value;
        }

        wonCards.add(playedCard);

        if (ard.size() == 1) {
            wonCards.add(ard.get(0));
            basra(player);
        }
        else if (ardSum <= 10) {
            wonCards.addAll(ard); // Uses collections to add all the objects in the array list omg I love this
            basra(player);
        }
        else { JackPlayed(player, playedCard); }
    }

    public void regularCard(Player player, Card playedCard) {
        ArrayList<Card> wonCards = player.getCardsWonByPlayer();
        ArrayList<Card> ard = getFloor();
        for (Card ardCard : ard) {
            if (ardCard.getValue().equals(playedCard.getValue())) {
                wonCards.add(ardCard);
                wonCards.add(playedCard);
                ard.remove(ardCard);
            }
        }
        // come back for the rest of this
    }

    public ArrayList<Integer> findSumCombinations() {
        ArrayList<Integer> allPossibleCombinations = new ArrayList<>();
        ArrayList<Card> ard = getFloor();
        ArrayList<Integer> ardValues = new ArrayList<>();
        for (Card ardCard : ard) {
            int value = ardCard.getNormalNumericalValue();
            ardValues.add(value);
        }

        return allPossibleCombinations;
    }
}
