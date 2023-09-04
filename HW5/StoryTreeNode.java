/**
* An object class representing a node in a story tree
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #5 for CSE 214, Spring 2023
*    Recitation 03
*
*    Mar 29th, 2023
*/
public class StoryTreeNode{
  static final String WIN_MESSAGE = "YOU WIN";
  static final String LOSE_MESSAGE = "YOU LOSE";
  String position;
  String option;
  String message;

  StoryTreeNode leftChild;
  StoryTreeNode middleChild;
  StoryTreeNode rightChild;

  /**
  * Default constructor for the StoryTreeNode
  *
  * sets default variables to empty strings, and left, middle, right child to null
  */
  public StoryTreeNode(){
    position = "";
    option = "";
    message = "";
  }

  /**
  * Constructor that takes position option, and message;
  *
  * @param1 position
  * desired position of the node
  *
  * @param2 option
  * desired option of the node
  *
  * @param3 message
  * desired message of the node
  *
  * Postconditions:
  * new StoryTreeNode is made with the given parameters
  */
  public StoryTreeNode(String position, String option, String message){
    this.position = position;
    this.option = option;
    this.message = message;
  }

  /**
  * Getter method that returns the left child of the node
  *
  * @returns leftChild
  */
  public StoryTreeNode getLeft(){
    return leftChild;
  }

  /**
  * Getter method that returns the middle child of the node
  *
  * @returns middleChild
  */
  public StoryTreeNode getMiddle(){
    return middleChild;
  }

  /**
  * Getter method that returns the right child of the node
  *
  * @returns rightChild
  */
  public StoryTreeNode getRight(){
    return rightChild;
  }

  /**
  * Setter method that sets the left child of the node
  *
  * Postconditions:
  * leftChild is changed
  */
  public void setLeft(StoryTreeNode node){
    this.leftChild = node;
  }

  /**
  * Setter method that sets the middle child of the node
  *
  * Postconditions:
  * middleChild is changed
  */
  public void setMiddle(StoryTreeNode node){
    this.middleChild = node;
  }

  /**
  * Setter method that sets the left child of the node
  *
  * Postconditions:
  * rightChild is changed
  */
  public void setRight(StoryTreeNode node){
    this.rightChild = node;
  }

  /**
  * This method returns a boolean on whether or not the node is a leaf
  *
  * @returns
  * true if the node is a leaf, false otherweise
  */
  public boolean isLeaf(){
    if (this == null) return false;
    return leftChild == null && middleChild == null && rightChild == null;
  }

  /**
  * This method returns a boolean on whether or not the node is a winning node
  *
  * @returns
  * true if the node has the winning message, false otherwise
  */
  public boolean isWinningNode(){
    if (this.isLeaf()){
      return this.message.contains(WIN_MESSAGE);
    }
    else return false;
  }

  /**
  * This method returns a boolean on whether or not the node is a losing node
  *
  * @returns
  * true if the node has the losing message, false otherwise
  */
  public boolean isLosingNode(){
    if (this.isLeaf()){
      return this.message.contains(LOSE_MESSAGE);
    }
    else return false;
  }

  /**
  * Getter method that returns the position of the node
  *
  * @returns
  * the position String
  */
  public String getPosition(){
    return position;
  }

  /**
  * Getter method that returns the option of the node
  *
  * @returns
  * the option String
  */
  public String getOption(){
    return option;
  }

  /**
  * Getter method that returns the message  of the node
  *
  * @returns
  * the message String
  */
  public String getMessage(){
    return message;
  }

  /**
  * Setter method that changes the message of the node
  *
  * @param1: desired
  * String that will be the node's new message
  *
  * Postconditions:
  * message is changed
  */
  public void setMessage(String desired) {
    message = desired;
  }

  /**
  * Setter method that changes the option of the node
  *
  * @param1: desired
  * String that will be the node's new message
  *
  * Postconditions:
  * option is changed
  */
  public void setOption(String desired) {
    option = desired;
  }

  /**
  * Helper method used in fixTree
  *
  * @param1: origDepth
  * this is the original depth used to find which part of the position string is being changed
  *
  * @param2: pos
  * the position (int value) that the new node is being changed to.
  *
  * Postconditions:
  * the node and all children of the node have their position string modified
  */
  public void fixShiftedNodes(int origDepth, int pos){
    if (this == null) return;
    if (pos == 1){
      this.position = position.substring(0, origDepth - 1) + "1" + position.substring(origDepth);
    }
    else if (pos == 2){
      this.position = position.substring(0, origDepth - 1) + "2" + position.substring(origDepth);
    }
    if (this.leftChild != null)
    this.leftChild.fixShiftedNodes(origDepth, pos);
    if (this.middleChild != null)
    this.middleChild.fixShiftedNodes(origDepth, pos);
    if (this.rightChild != null)
    this.rightChild.fixShiftedNodes(origDepth, pos);
  }
}
