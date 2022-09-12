import java.util.Random; // Needed for the Random class.
import java.util.Scanner; // Needed for the Scanner class.

class Main {
  // array to hold cards in deck
  public static int deck [] = {1,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,11,12,13};

  // initialize arrays for user and computer hands.
  public static int [] userHand = new int [10];
  public static int [] compHand = new int [10];
  
  // create scanner for user input
  public static Scanner keyboard = new Scanner(System.in);
  
  // declare variables to be used in methods.
  public static int val, sum, compSum, aceStore;

  // method to deal user hand.
  public static void userHandDeal() {

    sum = 0;
    
    System.out.println("User hand: ");

    // iterate twice for first 2 cards.
    for(val = 0; val < 2; val++)
    {
      // assign random card from deck
      userHand [val] =  new Random().nextInt(deck.length);
      userHand[val] = deck[userHand[val]];

      // call method to determine hand
      determineUserHand();
      
      // count total hand value.
      sum = sum + userHand[val];
    }

    // after first 2 cards.
    for(val = 2; val < userHand.length;)
    {
      // does user hit? or stay.
      System.out.println("Hit? (Enter 'no' to stop): ");
      String userHit = keyboard.nextLine();
      
      // if user hits, deal another card.
      while(!userHit.equals("no"))
      {
        userHand [val] =  new Random().nextInt(deck.length);
        userHand[val] = deck[userHand[val]];
      
        determineUserHand();

        sum = sum + userHand[val];
        val++;

        System.out.println("Hit? (Enter 'no' to stop): ");
        userHit = keyboard.nextLine();
      }

     // if no then break loops and return the users total points in hand.
      val = 11;
     // call value method
      userHandValue();

    }
    return; 
    }

  // method to determine what is in users hand.
  public static void determineUserHand()
  {
    Scanner keyboard = new Scanner(System.in);
    // if 1, then ace.
    if (userHand[val] == 1)
    {
      // ask user for choice of ace value
      System.out.print("ACE " + "Choose Value: (11 or 1): ");
      int aceVal = keyboard.nextInt();
        
      if(aceVal == 1)
        userHand[val] = userHand[val];
      else if(aceVal == 11)
        userHand[val] = 11;        
      else
      {
        // validate input.
        while(aceVal != 1 && aceVal != 11)
        {
          System.out.print("Error. " + "ACE " + "Choose Value: (11 or 1): ");
          aceVal = keyboard.nextInt();
        
        }
      }
    }
    // if 11,jack
    else if (userHand[val] == 11)
    {
      System.out.println("JACK");
      userHand[val] = userHand[val] - 1;
    }
    // if 12 queen
    else if (userHand[val] == 12)
    {
      System.out.println("QUEEN");
      userHand[val] = userHand[val] - 2;
    }
    // if 13 king
    else if (userHand[val] == 13)
    {
      System.out.println("KING"); 
      userHand[val] = userHand[val] - 3;
    }
    // if another print.
    else
      System.out.println(userHand[val]);

    return; 
  }

  // method to value users hand.
  public static void userHandValue()
  {
    // ask user to count value
    System.out.println("Count Value? (Enter 'no more card' to count)");
    String userVal = keyboard.nextLine();
    
    // input validation.
    if(userVal.equals("no more card"))
    {
      //rules for bust
      if(sum > 21){
        System.out.println("User has: " + sum);
      }
      // rules for blackjack hand.
      else if (userHand[0] + userHand[1] == 21)
        System.out.println("Blackjack!");
      else
        System.out.println("User has: " + sum);//rules for all other hands.

    }
    else
      userHandValue();// continue asking.(no more card)

    return;
  }

  // method to deal computer hand
  public static void compHandDeal() {

    compSum = 0;
    
    System.out.println("\nComputer hand: ");
    
    // iterate for first 2 cards.
    for(val = 0; val < compHand.length; val++)
    {
      //Get a random card from the deck and assign it to the computer hand.
      compHand [val] =  new Random().nextInt(deck.length);
      compHand[val] = deck[compHand[val]];

      //Call functions to determine hand.
      determineCompHand();

      //Count the points in the computers hand.
      compSum = compSum + compHand[val];
      
      // call on 3rd card
      if(val >= 2)
        aceChange();
      
      //after 2nd card call function to determine value.
      if(val >= 1)
        compHandValue();

    }

      return; 
    }
    // method to determine computers hand
    public static void determineCompHand()
    { 
      // if 1, ace. set to 11.
      if (compHand[val] == 1)
      {
        System.out.println("ACE");
        compHand[val] = compHand[val] + 10;
      }
      // if 11, jack
      else if (compHand[val] == 11)
      {
        System.out.println("JACK");
        compHand[val] = compHand[val] - 1;
      }
      // if 12, queen
      else if (compHand[val] == 12)
      {
        System.out.println("QUEEN");
        compHand[val] = compHand[val] - 2;
      }
      // if 13, king
      else if (compHand[val] == 13)
      {
        System.out.println("KING"); 
        compHand[val] = compHand[val] - 3;
      }
      else
        System.out.println(compHand[val]);

      return;
    }

  // method to change the ace value if comp hand over 21.
  public static void aceChange()
  {
    // if over 21 then loop to find ace and change value to 1 and sum.
    if(compSum > 21)
    {
      for(int x = 0; x < compHand.length; x++)
      {
        if(compHand[x] == 11)
        {
          compHand[x] = 1; 
          compSum = compSum - 10;          
        }
      }
    }
    return;
  }
  
  // method to value computers hand.
  public static void compHandValue()
  { 
    if(compSum > 21)
      {
        val = 11;
        System.out.println("Computer has: " + compSum);
        // determine winner.
        winner();           
      }
    else if (compHand[0] + compHand[1] == 21)
    {
      System.out.println("Blackjack!");
      winner();
      val = 11;
    }
    // if between 17 and 21 stay.
    else if (compSum >= 17 && compSum <= 21)
    {
      System.out.println("Computer has: " + compSum);
      val = 11;
      winner();
    }
    else
      return;   
  }

  // method to determine winner.
  public static void winner()
  {
    // user wins if hand has more points and no bust
    if(sum > compSum && sum <= 21)
      System.out.println("\nUser Wins!");
    else if(sum < compSum && compSum <= 21)// computer wins if better hand and no bust.
      System.out.println("\nComputer Wins!");
    else if(compHand[0] + compHand[1] == 21 && sum == 21)// blackjack comouter wins
      System.out.println("\nComputer Wins!");
    else if(userHand[0] + userHand[1] == 21 && compSum == 21)   // blackjack user wins
      System.out.println("\nUser Wins!"); 
    else if(sum > 21)
      System.out.println("\nUser bust! Computer Wins!");
    else if(compSum > 21)
      System.out.println("\nComputer bust! User Wins!");                
    else if(sum == compSum)
      System.out.println("\nTie!");
    else{
      return;
    }
      
  }
  
  // main method
  public static void main(String[] args) {

    // ask if user wants to play.
    System.out.println("Play blackjack? (enter 'yes' to play): ");
    
    String play = keyboard.nextLine();
    do
    {
      // call user deal method and comp deal method.
      userHandDeal();
      compHandDeal();
      System.out.println("\n\nPlay blackjack? (enter 'yes' to play again): ");
      play = keyboard.nextLine();
    }
    while(play.equals("yes"));// if yes execute code. else end.

  }
}

