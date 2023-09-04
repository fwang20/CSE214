/**
* An class that makes a custom exception for when the ship is over the weight capacity.
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #3 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 28nd, 2023
*/
class ShipOverWeightException extends Exception{
  public ShipOverWeightException() {

  }
  public ShipOverWeightException(String text){
    super(text);
  }
}
