
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;

public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count;

   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0;
   }

   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
	  count = 0;
   }

   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {

	  for(int i=0 ; i<hand.length; i++){
		if (hand[i] == null){
			hand[i] = c;
			count = count + 1;
			break;
		}
	 }


   }

   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in
    * the hand, then nothing is done.
    */
   public void removeCard(Card c) {

	for(int i=0 ; i<hand.length; i++){
		if (hand[i].equals(c)){
			hand[i] = null;
			count = count-1;
		}
	}

   }

   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
      hand[position] = null;
      count = count - 1;
   }

   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }

   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return hand[position];
   }

   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;

      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.

         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }

   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;

      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.

         for (int i = nonnull+1; i < hand.length; i++) {

			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }

   public void printHand(){

	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }


   public int numPairs() {
     int x=0;
     for(int i=0;i<4; i++) {

       if(hand[i].getValue() == hand[i+1].getValue()) {
         x++;
       }


     }
     for (int i=0;i<5;i++) {
         for (int r=i+1;r<5;r++) {
           if (r!=i && hand[r].getValue() == hand[i].getValue()) {


             x =1;
           }

             }
     }
     return x;

   }

   //Returns true if this hand has 3 cards that are of the same value
   //good
   public boolean hasTriplet() {
     for (int i=0;i<5;i++) {
         for (int r=i+1;r<5;r++) {
           if (r!=i && hand[r].getValue() == hand[i].getValue()) {


             return true;
           }

             }}
     return false;
   }

   //Returns true if this hand has all cards that are of the same suit
   //good
   public boolean hasFlush() {
     for (int r=0; r<4; r++){
           if ( hand[r].getSuit() != hand[r+1].getSuit() )
                     return false;
         }

     return true;





   }

   //Returns true if this hand has 5 consecutive cards of any suit
   //good
   public boolean hasStraight() {

     int x =1;
     for (int i = 0; i < 4; i++)
     {
       if (hand[i].getValue() == hand[i + 1].getValue()+1)


       {
         x++;

       }

     }
     if(x == 5) {
       return true;
     }
       else {
         return false;
       }

   }

   //Returns true if this hand has a triplet and a pair of different //values
   public boolean hasFullHouse() {
     int x = 0;
     for (int i = 1; i < 5; i++)
     {
       if (hand[i - 1].getValue() == hand[i].getValue())
       {
         x++;
       }
     }
     int l=0;
     for (int i=0; i<4; i++) {
       if(hand[i+1].getValue() ==hand[i].getValue()) {
         l++;
       }
     }
     if (x == 3 && l==2)
     {
       return true;
     }
     else
     {
       return false;
     }

   }

   //Returns true if this hand has 4 cards that are of the same value
   //good
   public boolean hasFourOfAKind() {
     for(int i=0; i<1; i++) {
       if (hand[i].getValue()== hand[i+1].getValue()&&hand[i+1].getValue()==
           hand[i+2].getValue()&&hand[i+2].getValue()==hand[i+3].getValue()) {
         return true;

       }
       else if (hand[i].getValue()== hand[i+2].getValue()&&hand[i+2].getValue()==
           hand[i+3].getValue()&&hand[i+3].getValue()==hand[i+4].getValue()){
         return true;
       }
       else if(hand[i].getValue()== hand[i+1].getValue()&&hand[i+1].getValue()==
           hand[i+3].getValue()&&hand[i+3].getValue()==hand[i+4].getValue()) {
         return true;
       }
       else if(hand[i].getValue()== hand[i+1].getValue()&&hand[i+1].getValue()==
           hand[i+2].getValue()&&hand[i+2].getValue()==hand[i+4].getValue()) {
         return true;
       }
       else if(hand[i+1].getValue()== hand[i+2].getValue()&&hand[i+2].getValue()==
           hand[i+3].getValue()&&hand[i+3].getValue()==hand[i+4].getValue()) {
         return true;
       }
     }
     return false;

   }

   public Card highestValue() {
         Card card = null;
         if(hand.length > 0) {
             card = hand[0];
             for(int i = 1; i < hand.length; ++i) {
                 if(hand[i].getValue() == 1 || hand[i].getValue() > card.getValue()) {   // if card is an ace or higher than current highCard
                     card = hand[i];
                 }
             }
         }
         return card;
     }
   //Returns the card with the highest value in the hand. When there is
   //more than one highest value card, you may return any one of them
   //code from A
   //public Card highestValue() {


   //int max=0;
   //for (int x = 1; x < 5; x++)
   //   {
   //    if (hand[x].getValue() > max)
   //    {
   //     max = hand[x].getValue();
   //     System.out.println( max);
   //    }

   //   }
   //return max;

                 // if (card.getNumber() == largest.getNumber() ) {
           //          if (largest.getSuit().equals("Diamonds"))
             //    largest = largest.getNumber;
               //  }
               //  else if (card.getNumber() == largest.getNumber() ) {
                 //    if (largest.getSuit().equals("Clubs"))
                  //       largest = largest.getNumber;
                 //}
                 //else if (card.getNumber() == largest.getNumber() ) {
                   //  if (largest.getSuit().equals("Hearts"))
                     //    largest = largest.getNumber;
                 //}
   //return max;

                 //else return;
   //}
   //A
   //Returns the highest valued Card of any pair or triplet found, null if
   // none. When values are equal, you may return either
   //public Card highestDuplicate() {

   //}

   //Returns -1 if the instance hand loses to the parameter hand,0 if
   //the hands are equal, and +1 if the instance hand wins over the
   //parameter hand. Hint: you might want to use some of the methods
   //above
   //parameter =h2
   //instance =h1
   //return +1
   public int compareTo(Hand h) {

     int rank=-1;
     if(h.hasFlush()&& h.hasStraight()) {
       rank= 1;
     }
     if (h.hasFourOfAKind()) {
       rank= 2;
     }
     if(h.hasFullHouse()) {
       rank= 3;
     }
     if(h.hasFlush()) {
       rank= 4;
     }

     if(h.hasStraight()) {
       rank= 5;
     }
     if (h.hasTriplet()){
       rank=6;
     }
     if(h.numPairs()==1) {
       rank=7;
     }
     if(h.numPairs()==2) {
       rank=7;
     }
     if(h.numPairs()==1) {
       rank=8;
     }
     int rank2=-1;
     if(this.hasFlush()&& h.hasStraight()) {
       rank2= 1;
     }
     if (this.hasFourOfAKind()) {
       rank2= 2;
     }
     if(this.hasFullHouse()) {
       rank2= 3;
     }
     if(this.hasFlush()) {
       rank2= 4;
     }

     if(this.hasStraight()) {
       rank2= 5;
     }
     if (this.hasTriplet()){
       rank2=6;
     }
     if(this.numPairs()==2) {
       rank2=7;
     }
     if(this.numPairs()==1) {
       rank2=8;
     }


     if(rank==rank2) {

     }
     if(rank<rank2) {
       return 1;
     }
     else if(rank>rank2) {
       return -1;
     }





     return 0;
     /*for (int x=0; x<=5; x++){
       if (h.hand[x].getValue() < this.hand[x].getValue()) {
                     return 1;
       }
         else if (h.hand[x].getValue() > this.hand[x].getValue()) {
                     return -1;
         }

         else if(h.hand[x].getValue() == this.hand[x].getValue()) {
         return 0;
         }

         }

     return 2;//shouldnt happen*/



   }

   /******************************** Implement your methods here ****************************************/
}
