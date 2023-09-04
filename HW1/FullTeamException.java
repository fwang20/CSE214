/**
* An class that makes a custom exception for when a team is full.
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #1 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 2nd, 2023
*/
class FullTeamException extends Exception{
  public FullTeamException() {

  }
  public FullTeamException(String text){
    super(text);
  }
}
