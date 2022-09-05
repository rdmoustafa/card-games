package components.card;

import java.util.ArrayList;

public class Card {

    public enum Suit{HEARTS, DIAMONDS, CLUBS, SPADES}
    public enum Value{ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
    public enum Colour{RED, BLACK}
    public Value cardValue;
    public Suit cardSuit;
    public Colour cardColour;

    // Constructor for object of card class --> single card
    public Card(Suit cardSuit, Value cardValue) {
        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
        if (cardSuit == Suit.HEARTS || cardSuit == Suit.DIAMONDS) { this.cardColour = Colour.RED; }
        else { this.cardColour = Colour.BLACK; }
    }

    // Getters and Setters
    public Suit getSuit() { return cardSuit; }
    public void setSuit(Suit cardSuit) { this.cardSuit = cardSuit; }
    public Value getValue() { return cardValue; }
    public void setValue(Value cardValue) { this.cardValue = cardValue; }
    public Colour getColour() { return cardColour; }
    /*ask about this one*/
    public void setColour(Colour cardColour) {
        if (cardSuit == Suit.HEARTS || cardSuit == Suit.DIAMONDS) { this.cardColour = Colour.RED; }
        else { this.cardColour = Colour.BLACK; }
    }

    // Used when picture cards are all value of 10 and Ace can have more than one value - java.game.pontoon.Pontoon
    public ArrayList<Integer> getNumericalValue() {
        ArrayList<Integer> numVal = new ArrayList<>();
        if (getValue() == Value.ACE) { numVal.add(1); numVal.add(11); }
        else if (getValue() == Value.TWO) { numVal.add(2); }
        else if (getValue() == Value.THREE) { numVal.add(3); }
        else if (getValue() == Value.FOUR) { numVal.add(4); }
        else if (getValue() == Value.FIVE) { numVal.add(5); }
        else if (getValue() == Value.SIX) { numVal.add(6); }
        else if (getValue() == Value.SEVEN) { numVal.add(7);}
        else if (getValue() == Value.EIGHT) { numVal.add(8); }
        else if (getValue() == Value.NINE) { numVal.add(9); }
        else if (getValue() == Value.TEN || getValue() == Value.JACK
                || getValue() == Value.QUEEN || getValue() == Value.KING) {
            numVal.add(10);
        }
        return numVal;
    }

    // Used in java.game.basra.Basra, each card has a single value and so returns the int value
    public Integer getNormalNumericalValue() {
        int numVal = 0;
        if (getValue() == Value.ACE) { numVal = 1; }
        else if (getValue() == Value.TWO) { numVal = 2; }
        else if (getValue() == Value.THREE) { numVal = 3; }
        else if (getValue() == Value.FOUR) { numVal = 4; }
        else if (getValue() == Value.FIVE) { numVal = 5; }
        else if (getValue() == Value.SIX) { numVal = 6; }
        else if (getValue() == Value.SEVEN) { numVal = 7;}
        else if (getValue() == Value.EIGHT) { numVal = 8; }
        else if (getValue() == Value.NINE) { numVal = 9; }
        else if (getValue() == Value.TEN) { numVal = 10; }
        else if (getValue() == Value.JACK) { numVal = 11; }
        else if (getValue() == Value.QUEEN) { numVal = 12; }
        else if (getValue() == Value.KING) { numVal = 13; }
        return numVal;
    }

}
