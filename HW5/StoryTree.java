/**
* An object class representing a story tree for the game of
* Zork.
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #5 for CSE 214, Spring 2023
*    Recitation 03
*
*    Mar 29th, 2023
*/
import java.util.*;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
public class StoryTree{
  StoryTreeNode root;
  StoryTreeNode cursor;
  GameState state;

  /**
  * Default constructor for the StoryTree
  *
  * Sets the root to a root StoryTreeNode, and cursor to the root.
  * GameState is always not over
  */
  public StoryTree(){
    root = new StoryTreeNode("root", "root", "Hello, and welcome to Zork!");
    cursor = root;
    state = GameState.GAME_NOT_OVER;
  }

  /**
  * Reads in a text file describing a StoryTree.
  *
  * @param1 filename
  * name of the file that is to be read
  *
  * preconditions:
  * filename is a non-null, non-empty String that points to a file that exists that is readable, and is valid.
  *
  * @returns
  * storyTree with the file read in
  *
  * @throws DataFormatException
  * if the file is not formatted correctly
  *
  * @throws IllegalArgumentException
  * if the filename is null
  */
  public static StoryTree readTree(String filename) throws DataFormatException{
    if (filename == null) throw new IllegalArgumentException("File name is not valid");
    StoryTree tree = new StoryTree();
    try {
      File text = new File(filename);
      Scanner fileIn = new Scanner(text);
      while(fileIn.hasNextLine()) {
        try{
          String line = fileIn.nextLine();
          String[] temp = line.split("\\|");
          StoryTreeNode addedNode = new StoryTreeNode(temp[0].trim(), temp[1].trim(), temp[2].trim());
          tree = addNode(tree, addedNode);
        }
        catch(Exception ex){
          throw new DataFormatException("Data not inputted correctly within the file.");
        }
      }
      fileIn.close();
    }
    catch (Exception ex) {
      System.out.println("An error occured with the file inputted.");
      System.out.println("If the inputted file name could not be found, it was created.");
    }
    return tree;
  }

  /**
  * Saves a story tree into a file
  *
  * @param1 filename
  * name of the file that is to be saved to
  *
  * @param2 tree
  * tree that is being saved
  *
  * preconditions:
  * tree is non-null and filename is a non-null, non-empty String.
  *
  * @throws IllegalArgumentException
  * filename is empty or null or tree is null.
  */
  public static void saveTree(String filename, StoryTree tree){
    if (tree == null) throw new IllegalArgumentException("Tree must not be null");
    if (filename == null ||  filename.isEmpty()) throw new IllegalArgumentException("Filename is not valid.");
    try {
      FileOutputStream outputStream = new FileOutputStream(filename);
      PrintWriter fileOut = new PrintWriter(outputStream);
      saveTreeHelper(tree.root.getLeft(), fileOut);
      fileOut.flush();
    }
    catch (Exception ex) {
      System.out.println("An error occured.");
    }
  }

  /**
  * Helper method used in saveTree
  *
  * @param1: node
  * this is the original node to be added to the file
  *
  * @param2: fileOut
  * the printWriter object used for the file
  *
  * Postconditions:
  * new file is modified with the tree's contents
  */
  public static void saveTreeHelper(StoryTreeNode node, PrintWriter fileOut){
    try{
      if (node == null) return;
      String newLine = node.getPosition() + " | " + node.getOption() + " | " + node.getMessage();
      fileOut.println(newLine);
      saveTreeHelper(node.getLeft(), fileOut);
      saveTreeHelper(node.getMiddle(), fileOut);
      saveTreeHelper(node.getRight(), fileOut);
    }
    catch(Exception ex){
      System.out.println("There was a problem saving the file");
    }
  }


  /*
  * This method moves the cursor left
  *
  * @throws NodeNotPresentException
  * if there is no node left
  */
  public void moveCursorLeft() throws NodeNotPresentException{
    if (cursor.getLeft() != null){
      cursor = cursor.getLeft();
    }
    else throw new NodeNotPresentException("Error. No child 1 for the current node.");
  }

  /*
  * This method moves the cursor to the middle child
  *
  * @throws NodeNotPresentException
  * if there is no middle child
  */
  public void moveCursorMiddle() throws NodeNotPresentException{
    if (cursor.getMiddle() != null){
      cursor = cursor.getMiddle();
    }
    else throw new NodeNotPresentException("Error. No child 2 for the current node.");
  }

  /*
  * This method moves the cursor right
  *
  * @throws NodeNotPresentException
  * if there is no node to the right
  */
  public void moveCursorRight() throws NodeNotPresentException{
    if (cursor.getRight() != null){
      cursor = cursor.getRight();
    }
    else throw new NodeNotPresentException("Error. No child 3 for the current node.");
  }

  /*
  * Getter method that returns the gameState
  *
  * @returns
  * gameState of the tree
  */
  public GameState getGameState(){
    return state;
  }

  /*
  * This method sets the GameState to loss
  *
  * Postconditions:
  * gameState is now GAME_OVER_LOSS
  */
  public void setGameStateLoss(){
    state = GameState.GAME_OVER_LOSS;
  }

  /*
  * This method sets the GameState to win
  *
  * Postconditions:
  * gameState is now GAME_OVER_WIN
  */
  public void setGameStateWin(){
    state = GameState.GAME_OVER_WIN;
  }

  /*
  * This method sets the GameState to not over
  *
  * Postconditions:
  * gameState is now GAME_NOT_OVER
  */
  public void resetGame(){
    state = GameState.GAME_NOT_OVER;
  }

  /*
  * Getter method that returns the cursor reference
  *
  * @returns
  * cursor reference
  */
  public StoryTreeNode getCursor(){
    return cursor;
  }

  /*
  * Getter method that returns the cursor's position
  *
  * @returns
  * cursor's position String
  */
  public String getCursorPosition(){
    return cursor.getPosition();
  }

  /*
  * Getter method that returns the cursor's message
  *
  * @returns
  * cursor's message String
  */
  public String getCursorMessage(){
    return cursor.getMessage();
  }

  /*
  * Getter method that returns the cursor's option
  *
  * @returns
  * cursor's option String
  */
  public String getCursorOption(){
    return cursor.getOption();
  }

  /*
  * Returns an array of String pairs - {position, option} for each immediate child of the cursor
  *
  * @returns
  * a 2d array with position in the first element of a row, option in the second
  */
  public String[][] getOptions(){
    String[][] returned = new String[3][2];
    int counter = 0;
    if (cursor.getLeft() != null){
      returned[0][0] = cursor.getLeft().getPosition();
      returned[0][1] = cursor.getLeft().getOption();
      counter++;
    }
    if (cursor.getMiddle() != null){
      returned[1][0] = cursor.getMiddle().getPosition();
      returned[1][1] = cursor.getMiddle().getOption();
      counter++;
    }
    if (cursor.getRight() != null){
      returned[2][0] = cursor.getRight().getPosition();
      returned[2][1] = cursor.getRight().getOption();
      counter++;
    }
    return returned;
  }

  /*
  * This method sets the cursor's message
  *
  * @param1 message
  * desired message of the cursor
  *
  * Postconditions:
  * cursor's message is now changed
  */
  public void setCursorMessage(String message){
    cursor.setMessage(message);
  }

  /*
  * This method sets the cursor's option
  *
  * @param1 option
  * desired option of the cursor
  *
  * Postconditions:
  * cursor's option is now changed
  */
  public void setCursorOption(String option){
    cursor.setOption(option);
  }

  /*
  * This method sets the cursor back to the root
  *
  * Postconditions:
  * cursor now refers to the root
  */
  public void resetCursor(){
    cursor = root;
  }


  /*
  * This method sets the cursor back to the first node of the tree
  *
  * Postconditions:
  * cursor now refers to the node with position 1
  */
  public void resetCursorFirstNode(){
    this.resetCursor();
    if (cursor.getLeft() != null) cursor = cursor.getLeft();
  }

  /*
  * This method checks if the cursor is on a winning node
  *
  * @returns
  * true if the cursor is a winning node, false otherwise
  */
  public boolean cursorWinning(){
    return cursor.isWinningNode();
  }

  /*
  * This method checks if the cursor is on a losing node
  *
  * @returns
  * true if the cursor is a losing node, false otherwise
  */
  public boolean cursorLosing(){
    return cursor.isLosingNode();
  }

  /*
  * Selects the child with the name indicated by position.
  *
  * @param1 position
  * position of the child to be selected
  *
  * preconditions:
  * The child with the indicated position member variable exists as a direct child of the cursor.
  *
  * postconditions
  * Cursor references node indicated by position.
  *
  * @throws InvalidArgumentException:
  * if position is empty or null.
  *
  * @throws NodeNotPresentException:
  * if Node with indicated position variable was not found.
  */
  public void selectChild(String position) throws NodeNotPresentException{
    if (position == null) throw new IllegalArgumentException("Position entered must not be null");
    char lastDigit = position.charAt(position.length() - 1);
    if (lastDigit == 1){
      if (cursor.getLeft() != null)
      cursor = cursor.getLeft();
      else throw new NodeNotPresentException("No node with this position");
    }
    if (lastDigit == 2){
      if (cursor.getMiddle() != null)
      cursor = cursor.getMiddle();
      else throw new NodeNotPresentException("No node with this position");
    }
    if (lastDigit == 3){
      if (cursor.getRight() != null)
      cursor = cursor.getRight();
      else throw new NodeNotPresentException("No node with this position");
    }
    else throw new NodeNotPresentException("No node with this position");
  }

  /*
  * Adds the child to the cursor if there is space
  *
  * @param1 option
  * option of the new child
  *
  * @param2 message
  * message of the new child
  *
  * postconditions
  * Cursor has new child, with specified message and option.
  *
  * @throws InvalidArgumentException:
  * if either String is empty or null.
  *
  * @throws TreeFullException:
  * All three child spots are already full.
  */
  public void addChild(String option, String message) throws TreeFullException{
    if (option == null || message == null) throw new IllegalArgumentException("Strings must not be null");
    if (option.isEmpty() || message.isEmpty()) throw new IllegalArgumentException("Strings must not be empty");
    if (cursor == root){
      if (root.getLeft() == null){
        StoryTreeNode newNode = new StoryTreeNode("1", option, message);
        root.setLeft(newNode);
        return;
      }
      else throw new TreeFullException("The root already has a child");
    }
    if (cursor.getLeft() == null){
      StoryTreeNode newNode = new StoryTreeNode(cursor.getPosition() + "-1", option, message);
      cursor.setLeft(newNode);
    }
    else if (cursor.getMiddle() == null){
      StoryTreeNode newNode = new StoryTreeNode(cursor.getPosition() + "-2", option, message);
      cursor.setMiddle(newNode);
    }
    else if (cursor.getRight() == null){
      StoryTreeNode newNode = new StoryTreeNode(cursor.getPosition() + "-3", option, message);
      cursor.setRight(newNode);
    }
    else throw new TreeFullException("Error. The cursor already has 3 children.");
  }

  /*
  * Removes an immediate child under the current cursor.
  *
  * @param1 position
  * position of the node to be removed
  *
  * precondition:
  * The child with the indicated position member variable exists as a direct child of the cursor.
  *
  * postcondition:
  * The indicated child and it's entire sub-tree have been removed from the tree.
  *
  * @returns
  * A reference to the child removed
  *
  * @throws NodeNotPresentException:
  * Node with indicated position variable was not found.
  */
  public StoryTreeNode removeChild(String position) throws NodeNotPresentException{
    if (position.length() != cursor.getPosition().length() + 2)
    throw new NodeNotPresentException("Given position is not a direct child");
    char lastDigit = position.charAt(position.length() - 1);
    StoryTreeNode temp;
    if (lastDigit == '1'){
      if(cursor.getLeft() != null){
        temp = cursor.getLeft();
        cursor.setLeft(null);
      }
      else throw new NodeNotPresentException("This node is not in the tree");
    }
    else if (lastDigit == '2'){
      if(cursor.getMiddle() != null){
        temp = cursor.getMiddle();
        cursor.setMiddle(null);
      }
      else throw new NodeNotPresentException("This node is not in the tree");
    }
    else if (lastDigit == '3'){
      if(cursor.getRight() != null){
        temp = cursor.getRight();
        cursor.setRight(null);
      }
      else throw new NodeNotPresentException("This node is not in the tree");
    }
    else throw new NodeNotPresentException("Error. No child " + lastDigit + " for the current node.");
    return temp;
  }

  /*
  * Method to shift positions after removal of a subtree
  *
  * @param1 depth
  * where the position substring is being changed
  *
  * postcondition:
  * All nodes shifted left if possible, position strings modified
  */
  public void fixTree(int depth){
    if (cursor.getLeft() == null){
      if (cursor.getMiddle() != null){
        cursor.setLeft(cursor.getMiddle());
        cursor.setMiddle(null);
        cursor.getLeft().fixShiftedNodes(depth ,1);
      }
      if (cursor.getRight() != null){
        cursor.setMiddle(cursor.getRight());
        cursor.setRight(null);
        cursor.getMiddle().fixShiftedNodes(depth,2);
      }
    }
    else if (cursor.getMiddle() == null){
      if (cursor.getRight() != null){
        cursor.setMiddle(cursor.getRight());
        cursor.setRight(null);
        cursor.getMiddle().fixShiftedNodes(depth,2);
      }
    }
  }

  /*
  * Method to add nodes to a storyTree recursively
  *
  * @param1 tree
  * tree to add to
  *
  * @param2 added
  * node to be added
  *
  * postcondition:
  * tree has a new node put in appropriately
  */
  public static StoryTree addNode(StoryTree tree, StoryTreeNode added){
    StoryTreeNode curr = tree.root;
    String[] commands = added.getPosition().split("-");
    int counter = 0;
    boolean done = false;
    while(!done){
      if (commands[counter].equals("1")) {
        if (curr.getLeft() == null) {
          curr.setLeft(added);
          done = true;
        }
        else {
          curr = curr.getLeft();
          counter++;
        }
      }
      if (commands[counter].equals("2")) {
        if (curr.getMiddle() == null) {
          curr.setMiddle(added);
          done = true;
        }
        else {
          curr = curr.getMiddle();
          counter++;
        }
      }
      if (commands[counter].equals("3")) {
        if (curr.getRight() == null) {
          curr.setRight(added);
          done = true;
        }
        else {
          curr = curr.getRight();
          counter++;
        }
      }
    }
    return tree;
  }

  /*
  * Method to add nodes to return to the parent
  *
  * postcondition:
  * cursor set to parent node if it exists
  *
  * @throws NodeNotPresentException
  * if the cursor is on the root
  */
  public void returnToParent() throws NodeNotPresentException{
    if (cursor == root){
      throw new NodeNotPresentException("This node has no parent");
    }
    //case where parent is root
    if (cursor.getPosition().equals("1")){
      cursor = root;
      return;
    }
    String parentPosition = getCursorPosition().substring(0, getCursorPosition().length()-2);
    String[] commands = parentPosition.split("-");
    StoryTreeNode curr = root;
    int counter = 0;
    boolean done = false;
    while(!done){
      if (commands[counter].equals("1")) {
        if (commands.length -1 == counter) {
          cursor = curr.getLeft();
          return;
        }
        else {
          curr = curr.getLeft();
          counter++;
        }
      }
      if (commands[counter].equals("2")) {
        if (commands.length -1 == counter) {
          cursor = curr.getMiddle();
          return;
        }
        else {
          curr = curr.getMiddle();
          counter++;
        }
      }
      if (commands[counter].equals("3")) {
        if (commands.length -1 == counter) {
          cursor = curr.getRight();
          return;
        }
        else {
          curr = curr.getRight();
          counter++;
        }
      }
    }
  }

  public static int countLeaves(StoryTreeNode node){
    if (node == null) return 0;
    if (node.isLeaf()){
      return 1;
    }
    else return countLeaves(node.getLeft()) + countLeaves(node.getRight()) + countLeaves(node.getMiddle());
  }

  public static int countWinningNodes(StoryTreeNode node){
    if (node == null) return 0;
    if (node.isWinningNode()){
      return 1;
    }
    else return countWinningNodes(node.getLeft()) + countWinningNodes(node.getRight()) + countWinningNodes(node.getMiddle());
  }
}
