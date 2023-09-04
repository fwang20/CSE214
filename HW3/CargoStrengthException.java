/**
* An class that makes a custom exception for when the given strength is higher than the strength allowed.
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #3 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 28nd, 2023
*/
class CargoStrengthException extends Exception{
  public CargoStrengthException() {

  }
  public CargoStrengthException(String text){
    super(text);
  }
}
