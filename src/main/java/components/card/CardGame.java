package components.card;


import components.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class CardGame {
    public int numPlayers;
    public Deck deck = new Deck();
    public ArrayList<Player> gamePlayers = new ArrayList<>();

    /* The constructor. This constructor creates the deck and sets the number of players for this java.game */
    public CardGame (int numPlayers) {
        this.numPlayers = numPlayers;
        for (int i = 0; i < numPlayers; i++) {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter the name for player " + (i+1));
            String name = userIn.nextLine();
            Player player = new Player(name);
            gamePlayers.add(player);
        }
    }

    // Getters and Setters
    /*Gets the number of players in the java.game*/
    public int getNumPlayers() { return numPlayers; }
    public void setNumPlayers(int numPlayers) { this.numPlayers = numPlayers; }
    /*returns the deck*/
    public Deck getDeck() { return deck; }
    public void setDeck(Deck deck) { this.deck = deck; }
    /*Gets and sets the array list of alll players*/
    public ArrayList<Player> getGamePlayers() { return gamePlayers; }
    public void setGamePlayers(ArrayList<Player> gamePlayers) { this.gamePlayers = gamePlayers; }


    // used in pontoon and basra
    public String printingSingleCardWithValue(Card card) {
        Card.Suit s = card.getSuit();
        String suit = String.valueOf(s);
        Card.Value v = card.getValue();
        String value = String.valueOf(v);
        return value + " of " + suit;
    }

    // used in go fish
    public String printingSingleCardWithColour(Card card) {
        Card.Suit s = card.getSuit();
        String suit = String.valueOf(s);
        Card.Value v = card.getValue();
        String value = String.valueOf(v);
        Card.Colour c = card.getColour();
        String colour = String.valueOf(c);
        return colour + " " + value + " [" + suit + "]";
    }

    /* This method gets the player at the index.
     * This method assumes you have added the correct number of players to the java.game. */
    public Player getPlayer(int i) { return gamePlayers.get(i); }

    /*This abstract method deals the number of initial cards to each player in the java.game.*/
    public abstract void dealInitialCards();

    /*This abstract method compares the hands of two players.
     * If hand1 is better than hand2 the method should return -1.
     * If hand2 is better than hand1 the method should return +1.
     * If the two hands are equal then the method should return 0.*/
    public abstract int compareHands(Player hand1, Player hand2);

}
