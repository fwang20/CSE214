/**
* An class using Storage and StorageTable
* Allows the user to simulate storing items in a self-storage
* Has functionality to be serializable
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #6 for CSE 214, Spring 2023
*    Recitation 03
*
*    Apr 13th, 2023
*/
import java.util.*;
import java.io.Serializable;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
public class StorageManager{
  static private StorageTable storage;

  /*
  * Method that prints the menu for this program
  *
  * postconditions:
  * A menu of options is printed to the user
  */
  public static void printMenu(){
    System.out.println("P - Print all storage boxes");
    System.out.println("A - Insert into storage box");
    System.out.println("R - Remove contents from a storage box");
    System.out.println("C - Select all boxes owned by a particular client");
    System.out.println("F - Find a box by ID and display its owner and contents");
    System.out.println("Q - Quit and save workspace");
    System.out.println("X - Quit and delete workspace");
  }

  public static void main(String[] args){
    File file = new File("storage.obj");
    if(file.exists()){
      try{
        FileInputStream fileInput = new FileInputStream("storage.obj");
        ObjectInputStream inStream = new ObjectInputStream(fileInput);
        storage = (StorageTable) inStream.readObject();
        inStream.close();
      }
      catch(Exception ex){
        System.out.println(ex.getMessage());
        System.exit(0);
      }
    }
    else storage = new StorageTable();

    Scanner sc = new Scanner(System.in);
    System.out.println("Hello, and welcome to Rocky Stream Storage Manager\n");
    while(true){
      printMenu();
      System.out.println("\nPlease select an option: ");
      String choice = sc.nextLine();
      switch(choice.toUpperCase().trim()){
        case "P":
        System.out.println("\n" + storage.toString());
        System.out.println();
        break;

        case "A":
        System.out.println("Please enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter client: ");
        String client = sc.nextLine();
        System.out.println("Please Enter Contents: ");
        String contents = sc.nextLine();
        try{
          Storage added = new Storage(id, client, contents);
          storage.putStorage(id, added);
          System.out.println("\nStorage " + id + " set!\n");
        }
        catch(Exception ex){
          System.out.println("\n" + ex.getMessage());
        }
        break;

        case "R":
        System.out.println("Please enter ID: ");
        int removeId = sc.nextInt();
        sc.nextLine();
        try{
          if (storage.remove(removeId) != null)
          System.out.println("\nBox " + removeId + " is now removed.\n");
          else System.out.println("\nThe storage does not have a box with this ID\n");
        }
        catch(Exception ex){
          System.out.println("\n" + ex.getMessage());
          break;
        }
        break;

        case "C":
        System.out.println("Please enter the name of the client: ");
        String clientName = sc.nextLine();
        System.out.println(storage.clientToString(clientName));
        System.out.println();
        break;

        case "F":
        System.out.println("Please enter ID: ");
        int searchId = sc.nextInt();
        sc.nextLine();
        try{
          Storage tempBox = storage.getStorage(searchId);
          tempBox.getContents(); //just to see if it is null;
          System.out.println("Box " + searchId);
          System.out.println("Contents: " + tempBox.getContents());
          System.out.println("Owner: " + tempBox.getClient());
          System.out.println();
        }
        catch(Exception ex){
          System.out.println("\nThere is no box with this ID in the storage\n");
          break;
        }
        break;

        case "Q":
        try{
          StorageTable.saveTable(storage);
          System.out.println("Storage Manager is quitting, current storage is saved for next session.");
          System.exit(0);
        }
        catch(Exception ex){
          System.out.println(ex.getMessage());
        }

        case "X":
        if (file.exists()){
          file.delete();
        }
        System.out.println("Storage Manager is quitting, all data is being erased.");
        System.exit(0);

      }
    }
  }
}
