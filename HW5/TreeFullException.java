/**
* An class that makes a custom exception for when the cursor is has all 3 children
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #5 for CSE 214, Spring 2023
*    Recitation 03
*
*    Mar 29nd, 2023
*/
class TreeFullException extends Exception{
  public TreeFullException() {

  }
  public TreeFullException(String text){
    super(text);
  }
}
