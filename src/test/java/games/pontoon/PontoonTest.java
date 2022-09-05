package games.pontoon;

import components.card.Card;
import components.card.Deck;
import components.player.Player;
import org.junit.gen5.api.Test;

import static org.junit.gen5.api.Assertions.assertEquals;


public class PontoonTest {
    Pontoon test = new Pontoon(2);
    Player fiveCardTrick = new Player("5card");
    Player twentyOne = new Player("21");
    Player twenty = new Player("20");
    Player otherNums = new Player("otherval");
    Player bust = new Player("Bust");

    Player one = new Player("Joe");
    Player two = new Player("pontoon");

    Deck d = new Deck();

    @Test
    void compareHands() {
        one.dealToPlayer(d.dealRandomCard());
        one.dealToPlayer(d.dealRandomCard());

        for (Card c : one.playerHand) {
            System.out.println(c.getValue());
        }
        System.out.println();

        two.dealToPlayer(d.dealRandomCard());
        two.dealToPlayer(d.dealRandomCard());

        for (Card a : two.playerHand) {
            System.out.println(a.getValue());
        }
        System.out.println();

        assertEquals(0,  test.compareHands(one, two));


        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.JACK);
        Card c4 = new Card(Card.Suit.DIAMONDS, Card.Value.KING);
        Card c5 = new Card(Card.Suit.DIAMONDS, Card.Value.QUEEN);
        bust.playerHand.add(c3);
        bust.playerHand.add(c4);
        bust.playerHand.add(c5);

        Card c6 = new Card(Card.Suit.DIAMONDS, Card.Value.FOUR);
        Card c7 = new Card(Card.Suit.DIAMONDS, Card.Value.SEVEN);
        Card c8 = new Card(Card.Suit.HEARTS, Card.Value.TEN);
        twentyOne.playerHand.add(c6);
        twentyOne.playerHand.add(c7);
        twentyOne.playerHand.add(c8);

        Card c9 = new Card(Card.Suit.SPADES, Card.Value.ACE);
        Card c10 = new Card(Card.Suit.SPADES, Card.Value.SEVEN);
        otherNums.playerHand.add(c9);
        otherNums.playerHand.add(c10);

        Card c11 = new Card(Card.Suit.DIAMONDS, Card.Value.FOUR);
        Card c12 = new Card(Card.Suit.DIAMONDS, Card.Value.SIX);
        Card c13 = new Card(Card.Suit.SPADES, Card.Value.JACK);
        twenty.playerHand.add(c11);
        twenty.playerHand.add(c12);
        twenty.playerHand.add(c13);
//        assertEquals(0, test.compareHands(pontoon, pontoon));
//        assertEquals(-1, test.compareHands(pontoon, fiveCardTrick));
//        assertEquals(-1, test.compareHands(pontoon, twentyOne));
//        assertEquals(-1, test.compareHands(pontoon, bust));
//        assertEquals(1, test.compareHands(fiveCardTrick, pontoon));
//        assertEquals(1, test.compareHands(bust, pontoon));
//        assertEquals(1, test.compareHands(otherNums, twenty));
    }
}