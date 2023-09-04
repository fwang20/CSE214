/**
* An class that makes a custom exception for when the node is not found
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #5 for CSE 214, Spring 2023
*    Recitation 03
*
*    Mar 29nd, 2023
*/
class NodeNotPresentException extends Exception{
  public NodeNotPresentException() {

  }
  public NodeNotPresentException(String text){
    super(text);
  }
}
