//Programmers: Ben Diskin, Jaspreet Khatkar, David Rukashaza-Hancock, T. Jake Holmes II
//Class CS 145: Face-to-Face
//Date: 1/21/2023
//Assignment: Lab 4: Deck of Cards
//Reference: Chapters 10 and 14
//Purpose: Simulate A Game Of Black Jack

import java.util.*;

public class CardGame { // uses variables for TWVars

   private static int pCount; // player hand count
   private static int dCount; // dealer hand count
   private static boolean firstGame = true; // determines instructions
   private static int chips = 10; // starting chips
   private static int bet = -1; // chips you bet
   private static Queue<String> deal = new LinkedList<String>(); // the first 4 cards delt
   private static Deck deck = new Deck();
   private static ArrayList<String> currentHand = new ArrayList<String>();
   private static ArrayList<String> dealerHand = new ArrayList<String>();
   
   public static void main(String[] args) {
   
      deck.gatherCards(); // creates an ordered list of cards
      deck.cardShuffler(); // pre-shuffles deck
      instructions();
      gameStart();
      pTurn(); // player hit/stay
      dTurn(); // dealer turn
      playAgain();
   } // end of main
   
   
   // ---------- basic game instructions ---------- //
   public static void instructions() {
      if(firstGame == true) {
         System.out.println("HOW TO PLAY BLACKJACK/21:");
         System.out.println("Each card in this game has its own point value.");
         System.out.println("Cards 2-10 are worth their face value, the Jack, Queen and King are each worth 10 points, and Aces are either worth 1 or 11 points.");
         System.out.println("You will be dealt two random cards to start, and then have the option of drawing additional cards (hitting) to increase the point value of your hand or ending your turn (staying).");
         System.out.println("The goal of the game is to finish with a higher point value in hand than the dealer without exceeding 21 points.");
         System.out.println("Exceeding 21 points during your turn is an automatic loss, as is referred to as a bust.");
         System.out.println("You win the game IMMEDIATELY if you get 21 points OR if the dealer busts, or you end the game with a higher point total than the dealer.");
         System.out.println("You lose the game IMMEDIATELY following your turn if you bust OR if the dealer ends the the game with a higher point total than the you.");
         System.out.println("Ending the game with the same points as the dealer results in a tie and is referred to as a push.");
         System.out.println("First you will take your turn, than the dealer will take theirs."); 
         System.out.println("LET'S PLAY:"); 
         firstGame = false;
      } else { System.out.println("OK, LET'S CONTINUE:"); }
      System.out.println();
   }//end instructions
   
   // ---------- method for starting cards ---------- //
   public static void gameStart() {
      Scanner input = new Scanner(System.in); // creates scanner method
   
      while(bet < 0 || bet > chips) {
         System.out.println("You have " + chips + " chips. How much would you like to bet?");
         try { bet = input.nextInt(); }
         catch(InputMismatchException e)  { String issue = input.nextLine();}
         if(bet < 0 || bet > chips) {
            System.out.println("Please choose a bid between 0 and " + chips + " chips.");
         }}
      System.out.println("Your bet has been placed.");
      
      deal.add(deck.draw()); // player 1st card
      deal.add(deck.draw()); // player 2nd card
      deal.add(deck.draw()); // player 3rd card
      deal.add(deck.draw()); // player 4th card
      
      System.out.println("The first card you are dealt is the " + deal.peek());
      wait(1000);
      currentHand.add(deal.peek());
      pCount += hit(deal.remove()); // removed first card of hand
      System.out.println("The dealer has the " + deal.peek());
      wait(1000);
      dealerHand.add(deal.peek());
      dealerHand.add("?????");
      dCount = hit(deal.remove());
      System.out.println("The second card you are dealt is the " + deal.peek());
      wait(1000);
      currentHand.add(deal.peek());
      pCount += hit(deal.remove());
      if(pCount == 21) { // if player hits 21 they win
         System.out.println("Wow you got 21! How lucky, you win!");
         payOut(true);
         playAgain(); }
      System.out.println("The dealer's second card is dealt face-down.\n");
      doubleDown();
      return;
   } // end of gameStart
   
   // ---------- method to convert card name to card value ---------- //
   public static int hit(String newCard) {
      int cardValue = 0;
      Scanner cardChecker = new Scanner(newCard); // scanner to read newCard
      switch(cardChecker.next()) { // switch case to determine value of card
      
         case "ACE":
            cardValue = 11; // ace can be converted to 1 later
            break;
         
         case "TWO":
            cardValue = 2;
            break;  
         
         case "THREE":
            cardValue = 3;
            break;
         
         case "FOUR":
            cardValue = 4;
            break;
         case "FIVE":
            cardValue = 5;
            break;
         case "SIX":
            cardValue = 6;
            break;
         case "SEVEN":
            cardValue = 7;
            break;
         case "EIGHT":
            cardValue = 8;
            break;
         case "NINE":
            cardValue = 9;
            break;
         case "TEN":
         case "JACK":
         case "QUEEN":
         case "KING":
            cardValue = 10;
            break;
      }
      return cardValue; } // end of hit
   
    // ---------- method for player turn ---------- //
   public static void pTurn() {
      Scanner input = new Scanner(System.in); // creates scanner method
      int cardValue = 0; // value unique cards
      int answer = 0;
      String card;
      System.out.println("Your Current Hand: " + currentHand.toString());
      System.out.println("Dealer's Current Hand: " + dealerHand.toString());
      System.out.println("What would you like to do? Enter 1 for hit or 2 for stay.");
      while(answer != 1 && answer != 2){
         try {
            answer = input.nextInt();
         }
         catch(InputMismatchException e)  { String issue = input.nextLine();}
         if(answer != 1 && answer != 2) {
            System.out.println("Error: not a valid response.");
            System.out.println("Please choose either 1 for hit or 2 for stay.\n");
         }} // end of while
      while(answer == 1) { // player draws another card
         card = deck.draw();
         currentHand.add(card);
         cardValue = hit(card);
         pCount += cardValue;
         if(pCount > 21 && cardValue == 11) { // code to convert Ace from 1 to 11
            pCount = pCount - 10;
         } 
         System.out.println("You tap the table. The dealer deals you the " + card);
         wait(1000);
         if(pCount == 21) { // if player hits 21 they win
            System.out.println("You got 21! You win!");
            payOut(true);
            playAgain();
         }
         else if(pCount > 21) { // if player exceeds 21 they lose
            System.out.println("Oops. With " + pCount + " points, you bust... and the dealer wins.");
            payOut(false);
            playAgain();
         } else { // if player is under 21 they can draw another card
            answer = 0;
            System.out.println("Your Current Hand: " + currentHand.toString()); 
            System.out.println("What would you like to do? hit = 1 stay = 2");
            wait(1000);
            while(answer != 1 && answer != 2){
               try {
                  answer = input.nextInt();
               }
               catch(InputMismatchException e)  { String issue = input.nextLine(); }
               if(answer != 1 && answer != 2) {
                  System.out.println("Error: not a valid response.");
                  System.out.println("Please enter 1 for hit or 2 for stay.\n");
               }}}}// end of while
      System.out.println("You wave your hand over the table and the dealer begins their turn\n");
      wait(1000);
      return;
   } // end of pTurn
   
   // ---------- method for dealer turn ---------- // 
   public static void dTurn() {
      int cardValue = 0; // value unique cards
      String card;
      
      card = deal.remove();
      dealerHand.set(1, card);
      dCount += hit(card);
      System.out.println("The dealer reveals their second card, the " + card);
      wait(1000);
      while(dCount < 17) { // dealer keeps drawing until they exceed 16
         card = deck.draw();
         dealerHand.add(card);
         cardValue = hit(card);
         dCount += cardValue;
         if(dCount > 21 && cardValue == 11) { // code to convert Ace from 1 to 11
            dCount = dCount - 10;
         }
         System.out.println("The dealer draws a " + card);
         wait(1000);
      }// end of while
      System.out.println("Your Current Hand: " + currentHand.toString());
      System.out.println("Dealer's Current Hand: " + dealerHand.toString()); 
      if(dCount > 21) { System.out.println("Dealer's card count is " + dCount + ". The dealer busts, and you win."); // 21 < dealer
         payOut(true);
         wait(1000); }
      else if(dCount > pCount) { // player < dealer < 21
         System.out.println("the dealer beat your card count of " + pCount + " with their card count of " + dCount + ". You lose.");
         payOut(false);
         wait(1000); }
      else if(dCount == pCount) { // player == dealer 
         System.out.println("You and the dealer both have " + pCount + " . Your game ended in a push. You recieve your bet back.");
         wait(1000); } 
      else { // dealer < player < 21
         System.out.println("You beat the dealer's card count of " + dCount + ". You win.");
         payOut(true); 
         wait(1000);}
      return;
   }// end of dturn
   
   // ---------- method to restart the game ---------- //
   public static void playAgain() {
      Scanner input = new Scanner(System.in); // creates scanner method
      int answer = 0;
      
      if(chips <= 0) { 
         System.out.println("Oh no, you've lost all your chips. GAME OVER.");
         System.exit(0);
      }
      else {
         System.out.println("\nPlay Again? yes = 1 no = 2");
         wait(1000);
         while(answer != 1 && answer != 2){
            try {
               answer = input.nextInt();
            }
            catch(InputMismatchException e)  { String issue = input.nextLine(); }
            if(answer != 1 && answer != 2) {
               System.out.println("Please enter 1 for yes or 2 for no.\n");
            }} // end of while
         if(answer == 1) {
            deck.deckChecker();
            pCount = 0;
            dCount = 0;
            bet = -1;
            currentHand.clear();
            dealerHand.clear();
         
            instructions();
            gameStart();
            pTurn(); // player hit/stay
            dTurn(); // dealer turn
            playAgain(); } // restarts the program
         else { 
            System.out.println("Thank you for playing!");
            wait(1000);
            System.exit(0);} // ends program
      }
      return;
   } // end of play again
   
   // method for time-delayed text
   public static void wait(int ms) {
      Random time = new Random();
      int delay = time.nextInt(ms);
      try
      {
         Thread.sleep(ms);
      }
      catch(InterruptedException e)
      {
         Thread.currentThread().interrupt();
      }
      return;
   } // end of wait
   
   // determines if you earn or lose chips
   public static void payOut(boolean win) {
      if(win == true) {
         System.out.println("You've earned " + bet + " chips!.");
         chips += bet;
      }
      else {
         System.out.println("You've lost " + bet + " chips...");
         chips -= bet;
      }
   }
   public static void doubleDown() {
      Scanner input = new Scanner(System.in); // creates scanner method
      int answer = 0;
      int cardValue;
      String card;
      if(pCount == 10 || pCount == 11) {
         System.out.println("Would you like to double down? Choose 1 for yes or 2 for no.");
         while(answer != 1 && answer != 2){
            try {
               answer = input.nextInt();
            }
            catch(InputMismatchException e)  { String issue = input.nextLine();}
            if(answer != 1 && answer != 2) {
               System.out.println("Error: not a valid response.");
               System.out.println("Please choose either 1 for yes or 2 for no.\n");
            } // if
         } // while
             
         if(answer == 1) {
            bet += bet;
            card = deck.draw();
            currentHand.add(card);
            cardValue = hit(card);
            pCount += cardValue;
               
            System.out.println("Your bet has been increased to " + bet + ".");
            System.out.println("The dealer deals you the Your final card for the round. The " + card);
            if(pCount == 21) { System.out.println("Wow you got 21! How lucky, you win!"); }
            dTurn(); // dealer turn
            playAgain(); // restarts the program
         }
      }
   } // end double down
    
} // end of class Blackjack

class Card {
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

class Deck {
      
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
} // end of deck class

