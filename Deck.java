//Programmers: Ben Diskin, Jaspreet Khatkar, David Rukashaza-Hancock, T. Jake Holmes II
//Class CS 145: Face-to-Face
//Date: 1/21/2023
//Assignment: Lab 4: Deck of Cards
//Reference: Chapters 10 and 14
//Purpose: Simulate A Game Of Black Jack

import java.util.*;

public class Deck {
      
      //arrayList of all unique cards
      ArrayList<String> cards = new ArrayList<String>();
      //Stack for card randomization
      Stack<String> deck = new Stack<String>();
      //random # generator to shuffle cards into deck
      Random rand = new Random();
      int shuffler; // created random #
      
      public Deck() {} // empty constructor
      
      public void gatherCards() { // fills cards ArrayList
      
      String [] faces = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};
      // array of card suits
      String [] suits = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"};
      
      for(int i = 0; i < suits.length; i++) { // for each suit
         for(int j = 0; j < faces.length; j++) { // for each face
         Card cardName = new Card(faces[j], suits[i]); // card object
         cards.add(cardName.cardName()); // adds unique card to cards ArrayList
         }
      } // end of for-loop
      } // end of gatherCards
      
      public void cardShuffler() { // creates stack of shuffled cards
        
      for(int i = 0; i < 52; i++) { // adds cards randomly to stack
         shuffler = rand.nextInt(cards.size());
         deck.push(cards.get(shuffler)); // adds random card from cards to stack
         cards.remove(shuffler); // removes selected card from Arraylist cards
      }// end of for loop
      } // end of card shuffler
      
      public String draw() {
         return deck.pop();
      } // end of draw
      
      public void deckChecker() { // checks amount of cards left in deck
      if(deck.size() < 10) {
      System.out.println("Hold on. Reshuffling deck...");
      gatherCards();
      cardShuffler();
      }
      } // end deck checker
} // end of deck