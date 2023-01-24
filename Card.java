//Programmers: Ben Diskin, Jaspreet Khatkar, David Rukashaza-Hancock, T. Jake Holmes II
//Class CS 145: Face-to-Face
//Date: 1/21/2023
//Assignment: Lab 4: Deck of Cards
//Reference: Chapters 10 and 14
//Purpose: Simulate A Game Of Black Jack

public class Card {
    private final String face; 
    private final String suit;
    
    // constructor
    public Card(String cardFace, String cardSuit) {
        this.face = cardFace; // initialize face of card
        this.suit = cardSuit; // initialize suit of card
    } // end of Card Constructor 
    
    // full name of card
    public String cardName() {
        return face + " of " + suit; 
    }
 
} // end class Card 