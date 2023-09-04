/**
* An class using StoryTree and StoryTreeNode, simulating the game of Zork
* by reading in a file given by the user.
* Has functionality for going back to parent and cheat code for probability of win.
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

public class Zork{
  static Scanner sc = new Scanner(System.in);
  public static void main(String[] args){
    System.out.println("Hello and Welcome to Zork!");
    System.out.print("\nPlease enter the file name: ");
    String fileName = sc.nextLine();
    System.out.println("\nLoading game from file...");
    try{
      StoryTree story = StoryTree.readTree(fileName);
      System.out.println("\nFile loaded!");
      while (true){
        System.out.println("\nWould you like to edit (E), play (P) or quit (Q)?  ");
        String choice = sc.nextLine();
        switch(choice.toUpperCase()){
          case "E":
          editTree(story);
          break;
          case "P":
          playTree(story);
          break;
          case "Q":
          System.out.println("\nGame being saved to " + fileName +"...");
          StoryTree.saveTree(fileName, story);
          System.out.println("\nSave successful!");
          System.out.println("\nProgram terminating normally.");
          System.exit(0);
          default:
          System.out.println("Please enter an appropriate choice.");
          break;
        }
      }
    }
    catch(Exception ex){
      ex.printStackTrace();
      System.out.println("\n" + ex.getMessage());
      System.out.println("Please run the program again.");
      System.exit(0);
    }
  }

  /*
  * Method to edit the StoryTree in the game of Zork
  *
  * @param1 tree
  * tree that is being edited
  *
  * postconditions
  * tree is modified
  */
  public static void editTree(StoryTree tree){
    System.out.println("\nZork editor:");
    outerWhile:
    while (true){
      System.out.println();
      printMenu();
      System.out.println("\nPlease select an option: ");
      String choice = sc.nextLine();
      switch(choice.toUpperCase().trim()){
        case "V":
        System.out.println("\nPosition: " + tree.getCursorPosition());
        System.out.println("Option: " + tree.getCursorOption());
        System.out.println("Message: " + tree.getCursorMessage());
        break;

        case "S":
        String[][] options = tree.getOptions();
        int counter = 0;
        for(int i = 0; i < 3; i++){
          if (options[i][1] != null) counter++;
        }
        String choices = "";
        switch(counter){
          case 0:
          choices = "None";
          break;
          case 1:
          choices = "[1]";
          break;
          case 2:
          choices = "[1,2]";
          break;
          case 3:
          choices = "[1,2,3]";
          break;
        }
        if (choices.equals("None")){
          System.out.println("This node has no children to select.");
          break;
        }
        System.out.println("\nPlease select a child: " + choices + " ");
        String childNum = sc.nextLine().trim();
        try{
          if (childNum.equals("1")){
            tree.moveCursorLeft();
          }
          else if (childNum.equals("2")){
            tree.moveCursorMiddle();
          }
          else if(childNum.equals("3")){
            tree.moveCursorRight();
          }
          else System.out.println("Error. No child " + childNum + " for the current node.");
        }
        catch(Exception ex){
          System.out.println("\n" + ex.getMessage());
          break;
        }
        break;

        case "P":
        try{
          tree.returnToParent();
          System.out.println("\nCursor was moved to this node's parent");
        }
        catch(Exception ex){
          ex.printStackTrace();
          System.out.println("\n" + ex.getMessage());
          break;
        }
        break;

        case "O":
        System.out.println("Please enter a new option: ");
        tree.setCursorOption(sc.nextLine());
        System.out.println("\nOption set.");
        break;

        case "M":
        System.out.println("Please enter a new Message: ");
        tree.setCursorMessage(sc.nextLine());
        System.out.println("\nMessage set.");
        break;

        case "A":
        System.out.println("Enter an option: ");
        String newOption = sc.nextLine();
        System.out.println("Enter a message: ");
        String newMessage = sc.nextLine();
        try{
          tree.addChild(newOption, newMessage);
          System.out.println("\nChild added.");
        }
        catch(Exception ex){
          System.out.println(ex.getMessage());
        }
        break;

        case "D":
        String[][] optionsDel = tree.getOptions();
        int counterDel = 0;
        for(int i = 0; i < 3; i++){
          if (optionsDel[i][0] != null)
          counterDel++;
        }
        String choicesDel = "";
        switch(counterDel){
          case 0:
          choicesDel = "None";
          case 1:
          choicesDel = "[1]";
          break;
          case 2:
          choicesDel = "[1,2]";
          break;
          case 3:
          choicesDel = "[1,2,3]";
          break;
        }
        if (choicesDel.equals("None")){
          System.out.println("This node has no children to delete.");
          break;
        }
        System.out.println("Please select a child: " + choicesDel);
        String removedNum = sc.nextLine();
        try{
          String newPos = tree.getCursorPosition() + "-" + removedNum.trim();
          int depth = newPos.length();
          tree.removeChild(newPos);
          tree.fixTree(depth);
          System.out.println("Subtree deleted.");
        }
        catch(Exception ex){
          System.out.println("\n" + ex.getMessage());
        }
        break;

        case "R":
        try {
          tree.resetCursorFirstNode();
          System.out.println("Cursor moved to root.");
        }
        catch(Exception ex){
          System.out.println("\n" + ex.getMessage());
          break;
        }
        break;

        case "Q":
        break outerWhile;

        default:
        System.out.println("Please choose an appropriate choice");
        break;

      }
    }
  }

  /*
  * Method to play the StoryTree in the game of Zork
  *
  * @param1 tree
  * tree that is being played
  *
  * postconditions
  * game is reset after a win or loss
  */
  public static void playTree(StoryTree tree){
    tree.resetCursor();
    try{
      tree.moveCursorLeft(); // move to position 1
    }
    catch(Exception ex){
      System.out.println("This tree is empty, please add some nodes or read in an appropriate text file to play.");
      return;
    }
    System.out.println("\n" + tree.getCursorOption());
    while(tree.getGameState() == GameState.GAME_NOT_OVER){
      System.out.println("\n" + tree.getCursorMessage());
      String[][] options = tree.getOptions();
      // print options;
      for (int i = 0; i < 3; i++){
        if (options[i][1] != null){
          System.out.println((i + 1) + ") " + options[i][1]);
        }
      }
      System.out.print("Please make a choice. ");
      String choice = sc.nextLine();
      System.out.println();
      try{
        switch(choice){
          case "1":
          tree.moveCursorLeft();
          break;
          case "2":
          tree.moveCursorMiddle();
          break;
          case "3":
          tree.moveCursorRight();
          break;
          case "C":
          double prob = (double) StoryTree.countWinningNodes(tree.getCursor()) / (double) StoryTree.countLeaves(tree.getCursor());
          System.out.println("Probability of a win at this point: " + (prob * 100) + "%");
          break;

        }
        if (tree.cursorWinning()){
          tree.setGameStateWin();
          System.out.println(tree.getCursorMessage());
        }
        else if(tree.cursorLosing()){
          tree.setGameStateLoss();
          System.out.println(tree.getCursorMessage());
        }
      }
      catch(Exception ex){
        System.out.println("\n" + ex.getMessage());
      }
    }
    tree.resetGame();
    tree.resetCursor();
    System.out.println("\nThanks for playing.");
  }

  /*
  * Static method to the print the menu for the editor
  */
  public static void printMenu(){
    System.out.println("\tV: View the cursor's position, option and message.");
    System.out.println("\tS: Select a child of this cursor (options are 1, 2, and 3)");
    System.out.println("\tP: Return to the parent of this child");
    System.out.println("\tO: Set the option of the cursor.");
    System.out.println("\tM: Set the message of the cursor.");
    System.out.println("\tA: Add a child StoryNode to the cursor.");
    System.out.println("\tD: Delete one of the cursor's children and all its descendants.");
    System.out.println("\tR: Move the cursor to the root of the tree.");
    System.out.println("\tQ: Quit editing and return to main menu.");
  }

}
