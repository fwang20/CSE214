/**
* An class that makes a custom exception for when the queue is empty
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #4 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 28nd, 2023
*/
class EmptyQueueException extends Exception{
  public EmptyQueueException() {

  }
  public EmptyQueueException(String text){
    super(text);
  }
}
