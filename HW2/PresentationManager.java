/**
* A class for testing Slide, SlideListNode, and SlideList
* Creates a menu form creating a slideshow of slides with at max 5 bullet points
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #2 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 14nd, 2023
*/
import java.util.*;
public class PresentationManager{
  public static void main(String[] args){
    SlideList presentation = new SlideList();
    System.out.println("\nWelcome to PresentationManager!\n\n");
    Scanner sc = new Scanner(System.in);
    String choice;
    while (true){
      printMainMenu();
      System.out.print("\nSelect a menu option: ");
      choice = sc.nextLine();

      switch(choice.trim().toUpperCase()) {

        case "F":
        try{
          presentation.cursorForward();
          System.out.println("\n\nThe cursor moved forward to slide " + '"' + presentation.getCursorSlide().getTitle() + '"' + "\n");
        }
        catch(EndOfListException ex){
          System.out.println("\n" + ex.getMessage() + "\n");
          break;
        }
        break;


        case "B":
        try{
          presentation.cursorBackward();
          System.out.println("\n\nThe cursor moved backward to slide " + '"' + presentation.getCursorSlide().getTitle() + '"' + "\n");
        }
        catch (Exception ex){
          System.out.println("\n" + ex.getMessage() + "\n");
          break;
        }
        break;


        case "D":
        if (presentation.getCursorSlide() == null){
          System.out.println("\nEmpty slideshow\n");
        }
        else System.out.println("\n" + presentation.getCursorSlide().bulletsToString() + "\n");
        break;

        case "E":
        if (presentation.getCursorSlide() == null) {
          System.out.println("\nEmpty slideshow: cannot be edited\n");
          break;
        }
        System.out.print("Edit title, duration, or bullets? (t/d/b) ");
        String editChoice = sc.nextLine().trim().toUpperCase();
        outerIf:
        if (editChoice.equals("T")){
          System.out.print("New title: ");
          String editedTitle = sc.nextLine();
          presentation.getCursorSlide().setTitle(editedTitle);
          System.out.println("The slide title has been changed.\n");
        }
        else if (editChoice.equals("D")){
          try {
            System.out.print("New duration: ");
            Double editedDuration = sc.nextDouble();
            sc.nextLine();
            presentation.getCursorSlide().setDuration(editedDuration);
            System.out.println("The slide duration has been changed.\n");
          }
          catch(Exception ex){
            System.out.println("\n" + ex.getMessage() + "\n");
            break;
          }
        }
        else if (editChoice.equals("B")){
          System.out.print("Bullet index: ");
          int bulletIndex = sc.nextInt();
          sc.nextLine();
          if (bulletIndex > Slide.MAX_BULLETS || bulletIndex < 1) {
            System.out.println("\nInvalid index for editing\n");
            break outerIf;
          }
          System.out.print("Delete or edit? (d/e) ");
          String delOrEdit = sc.nextLine().trim().toUpperCase();
          if (delOrEdit.equals("D")){
            presentation.getCursorSlide().removeBullet(bulletIndex);
            System.out.println("\n\nBullet " + bulletIndex + " has been deleted.\n");
          }
          else if (delOrEdit.equals("E")){
            System.out.print("Bullet " + bulletIndex + ": ");
            String newBullet = sc.nextLine();
            presentation.getCursorSlide().removeBullet(bulletIndex);
            presentation.getCursorSlide().setBullet(newBullet, bulletIndex);
            System.out.println("\n");
          }
          else System.out.println("Invalid option for editing" );
          break outerIf;
        }
        else{
          System.out.println("Invalid option: Please choose an appropriate choice.\n");
          break;
        }
        break;


        case "P":
        System.out.println("\n" + presentation.toString() + "\n");
        break;


        case "A":
        Slide newSlide = new Slide();
        System.out.print("\nEnter the slide title: ");
        String slideTitle = sc.nextLine();
        newSlide.setTitle(slideTitle);
        System.out.print("Enter the slide duration: ");
        double slideDuration = sc.nextDouble();
        sc.nextLine();
        try {
          newSlide.setDuration(slideDuration);
          String[] bulletsList = new String[Slide.MAX_BULLETS];
          outerLoop:
          for (int i = 0; i < Slide.MAX_BULLETS; i++){
            System.out.print("Bullet " + (i + 1) + ": ");
            bulletsList[i] = sc.nextLine();
            newSlide.setBullet(bulletsList[i], i + 1);
            if (i == 4) {
              System.out.println("No more bullets allowed. Slide is full.");
              presentation.appendToTail(newSlide);
                System.out.println("\nSlide " + '"' +  slideTitle + '"' + " added to presentation\n");
              break outerLoop;
            }
            else {
              System.out.print("Add another bullet point? (y/n) ");
              String addBullet = sc.nextLine().trim().toUpperCase();
              if (addBullet.equals("N")) {
                presentation.appendToTail(newSlide);
                System.out.println("\nSlide " + '"' +  slideTitle + '"' + " added to presentation\n");
                break outerLoop;
              }
              else if (!addBullet.equals("Y")){
                System.out.println("Invalid option entered.");
                break outerLoop;
              }
            }
          }
        }
        catch (Exception ex){
          System.out.println("\n" + ex.getMessage() + "\n");
          break;
        }
        break;


        case "I":
        Slide newSlide2 = new Slide();
        System.out.print("\nEnter the slide title: ");
        String slideTitle2 = sc.nextLine();
        newSlide2.setTitle(slideTitle2);
        System.out.print("Enter the slide duration: ");
        double slideDuration2 = sc.nextDouble();
        sc.nextLine();
        try {
          newSlide2.setDuration(slideDuration2);
          String[] bulletsList2 = new String[Slide.MAX_BULLETS];
          outerLoop2:
          for (int i = 0; i < Slide.MAX_BULLETS; i++){
            System.out.print("Bullet " + (i + 1) + ": ");
            bulletsList2[i] = sc.nextLine();
            newSlide2.setBullet(bulletsList2[i], i + 1);
            if (i == 4) {
              System.out.println("No more bullets allowed. Slide is full.");
              presentation.insertBeforeCursor(newSlide2);
              System.out.println("\nSlide " + '"' +  slideTitle2 + '"' + " added to presentation\n");
              break outerLoop2;
            }
            else {
              System.out.print("Add another bullet point? (y/n) ");
              String addBullet2 = sc.nextLine().trim().toUpperCase();
              if (addBullet2.equals("N")) {
                presentation.insertBeforeCursor(newSlide2);
                System.out.println("\nSlide " + '"' +  slideTitle2 + '"' + " added to presentation\n");
                break outerLoop2;
              }
              else if (!addBullet2.equals("Y")){
                System.out.println("Invalid option entered.");
                break outerLoop2;
              }
            }
          }
        }
        catch (Exception ex){
          System.out.println("\n" + ex.getMessage() + "\n");
          break;
        }
        break;


        case "R":
        try {
          String removedName = presentation.removeCursor().getTitle();
          System.out.println("Slide " + '"' + removedName + '"' + " has been removed.");
          break;
        }
        catch(Exception ex){
          System.out.println("\n" + ex.getMessage() + "\n");
          break;
        }


        case "H":
        if (presentation.getCursorSlide() == null) System.out.println("\nEmpty slideshow\n");
        else {
          presentation.resetCursorToHead();
          System.out.println("\nCursor has been reset to the head.\n");
        }
        break;


        case "Q":
        System.out.println("\nProgram terminating normally...");
        System.exit(0);


        default:
        System.out.println("\nInvalid option: Please choose an appropriate choice from the menu.\n");
        break;
      }
    }
  }


  private static void printMainMenu(){
    System.out.println("Please select an option:");
    System.out.println("F) Move cursor forward");
    System.out.println("B) Move cursor backward");
    System.out.println("D) Display cursor slide");
    System.out.println("E) Edit cursor slide");
    System.out.println("P) Print presentation summary");
    System.out.println("A) Append new slide to tail");
    System.out.println("I) Insert new slide before cursor");
    System.out.println("R) Remove slide at cursor");
    System.out.println("H) Reset cursor to head");
    System.out.println("Q) Quit");
  }
}
