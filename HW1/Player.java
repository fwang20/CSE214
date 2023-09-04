/**
 * An abstract data type meant to serve as a representation of players
 * with names, hits, and errors
 *
 * @author
 *    Frank Wang., USB ID 115037306
 *
 *    Homework #1 for CSE 214, Spring 2023
 *    Recitation 03
 *
 *    Feb 2nd, 2023
 */
public class Player{

  private String name;
  private int numHits, numErrors;
  /**
  * This is the constructor for a Player.
  * It creates a Player object with variables for a name, number of hits, and number of errors
  */
  public Player () {
    String name;
    int numHits;
    int numErrors;
  }

  /**
  * This method returns the name of this Player object.
  *
  * @return
  * The name of this player object in the form of a String
  *
  */
  public String getName() {
    return this.name;
  }

  /**
  * This method returns the number of hits that this Player has.
  *
  * @return
  * An int with the number of hits this Player has
  *
  */
  public int getNumHits() {
    return this.numHits;
  }

  /**
  * This method returns the number of errors that this Player has.
  *
  * @return
  * An int with the number of errors this Player has
  */
  public int getNumErrors() {
    return this.numErrors;
  }

  /**
  * This mutator method changes the name of the Player.
  *
  * @param1 name
  * Desired name of the Player
  *
  * Precondition:
  * This Player object has been instantiated
  *
  * Postcondition:
  * This player object now has the given name
  */
  public void setName(String name) {
    this.name = name;
  }

  /**
  * This mutator method changes the number of hits the Player has.
  *
  * @param1 numHits
  * Desired number of hits to give the Player
  *
  * Precondition:
  * This Player object has been instantiated
  *
  * Postcondition:
  * This player object now has the inputted number of hits.
  */
  public void setNumHits(int numHits) {
    if (numHits < 0) {
      throw new IllegalArgumentException("Invalid number of hits: Entered number of hits must be greater than or equal to 0");
    }
    else this.numHits = numHits;
  }

  /**
  * This mutator method changes the number of hits the Player has.
  *
  * @param1 numHits
  * Desired number of hits to give the Player
  *
  * Precondition:
  * This Player object has been instantiated
  *
  * Postcondition:
  * This player object now has the inputted number of hits.
  */
  public void setNumErrors(int numErrors) {
    if (numErrors < 0) {
      throw new IllegalArgumentException("Invalid number of errors: Entered number of errors must be greater than or equal to 0");
    }
    this.numErrors = numErrors;
  }

  /**
  * This method makes a clone of the Player object
  *
  * Precondition:
  * This Player object has been instantiated
  *
  * @return
  * Returns a deep copy of the Player object.
  */
  public Object clone(){
    Player clonePlayer = new Player();
    clonePlayer.name = this.name;
    clonePlayer.numHits = this.numHits;
    clonePlayer.numErrors = this.numErrors;
    return clonePlayer;
  }

  /**
  * This method returns the String representation of a Player
  * with their name, number of hits, and number of errors
  *
  * Precondition:
  * Team object has been instantiated
  *
  * @return
  * Returns the string representation of the Player object.
  */
  @Override
  public String toString() {
    return String.format("%-20s%-12s%-6s", name, numHits, numErrors);
  }
}
