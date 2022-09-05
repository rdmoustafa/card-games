package components.card;

import java.util.ArrayList;
import java.util.Random;

/* This class represents a whole deck of cards, there are 52 cards, 13 cards from each suit. Each suit has one of each card value */
public class Deck {
    public ArrayList<Card> deckOfCards1 = new ArrayList<>();

    // Constructor - should create the deck of cards
    public Deck() { reset(); }

    /* This method recreates a full deck of cards in an existing deck.
     * This method will always result in the creation of 52 distinct cards in the deck. */
    public void reset() {
        deckOfCards1 = new ArrayList<>();
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Value v : Card.Value.values()) {
                Card nC = new Card(s, v);
                deckOfCards1.add(nC);
            }
        }
    }

    /* This method shuffles the deck.
     * After this method has been called the current cards that are still in the deck
     * (which might be less than 52) should be shuffled.
     * In general this means that if a card exists at a particular index in the deck,
     * it should exist at a different index after the method has been called.*/
    public void shuffle() {
        for (int i = 0; i < deckOfCards1.size(); i++) {
            int randPos = (int)(Math.random() * deckOfCards1.size());
            Card temp = deckOfCards1.get(i);
            Card atRand = deckOfCards1.get(randPos);
            deckOfCards1.set(i, atRand);
            deckOfCards1.set(randPos, temp);
        }
    }

    /*This returns the card at a given index*/
    public Card getCard(int i) { return deckOfCards1.get(i); }

    /* This method deals a random card. A random card should be selected from those
     * remaining in the deck and returned. The selected card should be removed from the deck */
    public Card dealRandomCard() {
        Random rand = new Random();
        int cardsAva = size();
        int randCardPos = rand.nextInt(cardsAva);
        Card returnCard = deckOfCards1.get(randCardPos);
        removeCard(returnCard);
        return returnCard;
    }

    /* Returns the number of cards currently in the deck*/
    public int size() { return deckOfCards1.size(); }

    // Removes the card from the deck
    public void removeCard(Card card) { deckOfCards1.remove(card); }

}
