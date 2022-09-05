package components.player;

import components.card.Card;

import java.util.ArrayList;

/*The main responsibility of this class, however, is to represent the player’s ‘hand’ of cards.
 * A ‘hand’ is a collection of cards held by a player.
 * The number of cards per player varies depending on the java.game and the current state of the java.game.
 * That detail is not dealt with by this class, however.
 */
public class Player {
    public String playerName;
    public ArrayList<Card> playerHand = new ArrayList<>();
    public ArrayList<Card> cardsWonByPlayer = new ArrayList<>();
    public int playerPoints = 0;

    /* Constructor - to store player name */
    public Player(String playerName) {
        this.playerName = playerName;
    }

    /*Getters and Setters*/
    // Returns player name
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    // Returns and gets player hand
    public ArrayList<Card> getPlayerHand() { return playerHand; }
    public void setPlayerHand(ArrayList<Card> playerHand) { this.playerHand = playerHand; }

    // Used in java.game.basra.Basra
    public ArrayList<Card> getCardsWonByPlayer() { return cardsWonByPlayer; }
    public void setCardsWonByPlayer(ArrayList<Card> cardsWonByPlayer) { this.cardsWonByPlayer = cardsWonByPlayer; }
    public int getPlayerPoints() { return playerPoints; }
    public void setPlayerPoints(int playerPoints) { this.playerPoints = playerPoints; }

    // Deals card to player -- it adds a card to a player's hand
    public void dealToPlayer(Card card) { playerHand.add(card); }
    // This method removes a card from the players hand
    public void removeCard(Card card) { playerHand.remove(card); }

    /*Returns the number of cards in the player's hand*/
    public int getHandSize() { return playerHand.size(); }

    /* This method returns all the possible numerical values of a hand as an ArrayList since there are multiple possible values
     * if the hand contains ACE cards since each ACE card can have the value 1 or 11. Returned from lowest to highest (i.e. the lowest value is returned at index 0). */
    // You need to consider if there is an ACE and if there isn't - If there is an ACE there will be two different sums bc 1 or 11 for each ACE
    public ArrayList<Integer> getNumericalHandValue() {
       ArrayList<Integer> possibleHandValues = new ArrayList<>();
       int handSum = 0;
       int count = 0;
       int handSize = getHandSize();
       // Getting the array list of the num value without the ACE
       for (int i = 0; i < handSize; i++) {
           Card pH = playerHand.get(i);
           ArrayList<Integer> posValues = pH.getNumericalValue();
           int value = posValues.get(0);
           handSum += value;
           if (pH.getValue().equals(Card.Value.ACE)) { count++; }
       }
       // this adds the value with all aces as 1
       possibleHandValues.add(handSum);
       for (int x = 0; x < count; x++) {
           handSum += 10;
           possibleHandValues.add(handSum);
       }
       return possibleHandValues;
   }
   /*This method returns the maximum numerical value of the player's hand of cards*/
    public int getBestNumericalHandValue() {
        ArrayList<Integer> possibleHandValues1 = getNumericalHandValue();
        int bestNumVal = 0;
        int size = possibleHandValues1.size();
        for (int position = size - 1; position >= 0; position--) {
            bestNumVal = possibleHandValues1.get(position);
            if (position == 0 || bestNumVal <= 21) { return bestNumVal; }
        }
        return bestNumVal;
    }

}
