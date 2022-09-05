package games.gofish;

import components.card.Card;
import components.card.Deck;
import components.player.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GoFishGame {

    public void launch() {
        Scanner userIn = new Scanner(System.in);
        // Starting the java.game and setting it up
        System.out.print("Welcome to Go Fish. \nPlease enter your name. Name: ");
        GoFish goFish = new GoFish(2);
        Player player = goFish.getPlayer(0);
        Player com = goFish.getPlayer(1);
        int playerPoints = 0;
        int comPoints = 0;
        int totalPoints = 0;
        ArrayList<Card> cardsKnownByCom = new ArrayList<>();
        final int MAX_POINTS = 26;
        Deck deck;
        System.out.printf("Welcome %s. The java.game has begun", player.getPlayerName());
        ArrayList<Card> pHand;
        ArrayList<Card> comHand;
        goFish.dealInitialCards();
        int comHandSize;
        // Taking turns
        while (totalPoints < MAX_POINTS) {
            pHand = player.getPlayerHand();

            // Display the players hand
            System.out.println("\nYour hand of cards consists of: ");
            int i = 0;
            for (Card c : pHand) {
                String s = "(" + ++i + ") " + goFish.printingSingleCardWithColour(c) + " | ";
                System.out.print(s);
            }

            comHandSize = com.getHandSize();
            System.out.println("\nThe computer currently has " + comHandSize + " cards");

            deck = goFish.getDeck();
            int pile = deck.size();
            System.out.println("The size of the pile is " + pile);

            // java.game.Player asks for a card -- checking card is in player hand
            boolean playerAsk = false;
            int cardNum = 0;
            while (!playerAsk) {
                System.out.println("What card of yours would you like to ask for? [Enter the corresponding number]");
                String cardNumber = userIn.nextLine();
                try {
                    cardNum = Integer.parseInt(cardNumber);
                    int handSize = player.getHandSize();
                    if (cardNum <= handSize && cardNum > 0) {
                        playerAsk = true;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid Input. Try again.");
                }
            }

            // checking if card is in com hand to take the card or not
            pHand = player.getPlayerHand();
            Card playerAskedForCard = pHand.get(cardNum - 1);
            boolean cardFound = false;
            comHand = com.getPlayerHand();
            for (Card c : comHand) {
                if (c.getValue() == playerAskedForCard.getValue() && c.getColour() == playerAskedForCard.getColour()) {
                    player.removeCard(playerAskedForCard);
                    com.removeCard(c);
                    // checking the coms memory for the card being removed
                    /*Learn this thing, uses collections*/
                    cardsKnownByCom.removeIf(a -> a.getColour() == playerAskedForCard.getColour() && a.getValue() == playerAskedForCard.getValue());
                    playerPoints++;
                    cardFound = true;
                }
            }
            // if card was found draw a card (making sure pile was not empty)
            if (!cardFound) {
                System.out.println("The computer did not have the card you asked for. Go fish.");
                deck = goFish.getDeck();
                pile = deck.size();
                if (pile != 0) {
                    Card topCard = deck.getCard(0);
                    cardsKnownByCom.add(playerAskedForCard);
                    player.dealToPlayer(topCard);
                    deck.removeCard(topCard);
                }
                else System.out.println("The pile is empty, you were not given a card");
            }

            // INSERT COMPUTER ASKED FOR A CARD
            // this is based on the com memory
            comHand = com.getPlayerHand();
            Card computerAskedForCard = null;
            for (Card b : cardsKnownByCom) {
                for (Card d : comHand) {
                    if (d.getValue() == b.getValue() && b.getColour() == d.getColour()) {
                        computerAskedForCard = d;
                        break;
                    }
                }
            }
            // if the card was not assigned a card from mem then the computer will pick a random card from its hand to ask the player about
            comHand = com.getPlayerHand();
            if (computerAskedForCard == null) {
                Random rand = new Random();
                comHandSize = comHand.size();
                int randCardFromComHand = rand.nextInt(comHandSize);
                deck = goFish.getDeck();
                computerAskedForCard = deck.getCard(randCardFromComHand);
            }

            // Displaying what card the com is asking the player for
            String comAskedCard = goFish.printingSingleCardWithColour(computerAskedForCard);
            // the possible inputs in the com
            String expectedResponseFound = "yes";
            String expectedResponseNotFound1 = "no";
            String expectedResponseNotFound2 = "go fish";

            // checking if the card exists in the players hand, seeing if the com should expect a yes or no
            boolean cardExists = false;
            boolean validResponse = false;
            pHand = player.getPlayerHand();
            for (Card e : pHand) {
                if (computerAskedForCard.getColour() == e.getColour() && computerAskedForCard.getValue() == e.getValue()) {
                    cardExists = true;
                    break;
                }
            }
            // taking the user input
            String playerResponseInput;
            while (!validResponse) {
                System.out.println("The computer has asked for: " + comAskedCard + "\nDo you have this card?");
                playerResponseInput = userIn.nextLine();
                String playerResponse = playerResponseInput.toLowerCase();
                if (!(playerResponse.contains(expectedResponseFound) && playerResponse.contains(expectedResponseNotFound1) || playerResponse.contains(expectedResponseFound) && playerResponse.contains(expectedResponseNotFound2))) {
                    if (cardExists) { if (playerResponse.contains(expectedResponseFound)) { validResponse = true; } }
                    else { if (playerResponse.contains(expectedResponseNotFound1) || playerResponse.contains(expectedResponseNotFound2)) { validResponse = true; } }
                    if (!validResponse) System.out.println("Try again");
                }
                else { System.out.println("Invalid Input. Try again."); }
            }

            // If player does have the card in their hand
            pHand = player.getPlayerHand();
            if (cardExists) {
                for (Card f : pHand) {
                    if (f.getValue() == computerAskedForCard.getValue() && f.getColour() == computerAskedForCard.getColour()) {
                        player.removeCard(f);
                        com.removeCard(computerAskedForCard);
                        comPoints++;
                    }
                }
            }
            // if the player doesn't have the card in the hand
            else {
                System.out.println("You did not have the card the computer asked for. The computer drew a card. ");
                deck = goFish.getDeck();
                pile = deck.size();
                if (pile != 0) {
                    Card topCard = deck.getCard(0);
                    cardsKnownByCom.add(computerAskedForCard);
                    com.dealToPlayer(topCard);
                    deck.removeCard(topCard);
                }
                else { System.out.println("The pile is empty, you were not given a card"); }
            }
            // summing up total points
            totalPoints = comPoints + playerPoints;
        }

        System.out.println("All the pairs have put down");
        if (comPoints > playerPoints) System.out.println("You lost. The com had " + comPoints + " and you had " + playerPoints);
        else if (comPoints < playerPoints) System.out.println("You won! The had " + comPoints + " and you had " + playerPoints);
        else System.out.println("It's a draw, good java.game");
    }
}

