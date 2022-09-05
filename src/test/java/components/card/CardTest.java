package components.card;

import org.junit.gen5.api.Test;

import java.util.ArrayList;

import static org.junit.gen5.api.Assertions.assertEquals;

public class CardTest {

    @Test
    void getNumericalValue() {
        Card one = new Card(Card.Suit.DIAMONDS, Card.Value.ACE);
        ArrayList<Integer> expect = new ArrayList<>();
        expect.add(1);
        expect.add(11);

        assertEquals(expect, one.getNumericalValue());
    }

}