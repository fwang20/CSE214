/**
* An class that makes a custom exception for when the stack is empty.
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #3 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 28nd, 2023
*/
class EmptyStackException extends Exception{
  public EmptyStackException() {

  }
  public EmptyStackException(String text){
    super(text);
  }
}
