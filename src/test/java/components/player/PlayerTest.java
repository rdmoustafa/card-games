package components.player;

import components.card.Card;
import org.junit.gen5.api.Test;

import static org.junit.gen5.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    void getBestNumericalHandValue() {
        Player bust = new Player("P");

        Card c3 = new Card(Card.Suit.DIAMONDS, Card.Value.TWO);
        Card c4 = new Card(Card.Suit.DIAMONDS, Card.Value.KING);
        Card c5 = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        bust.playerHand.add(c3);
        bust.playerHand.add(c4);
        bust.playerHand.add(c5);

        assertEquals(12, bust.getBestNumericalHandValue());
    }
}