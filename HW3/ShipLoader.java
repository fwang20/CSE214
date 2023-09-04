/**
* A class for testing Cargo, CargoShip, and CargoStack
* Creates a menu for creating a ship and a dock.
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #3 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 28nd, 2023
*/
import java.util.*;
public class ShipLoader {

  public static void main(String[] args){
    CargoStack dock = new CargoStack();
    System.out.println("\nWelcome to ShipLoader!\n\n");
    Scanner sc = new Scanner(System.in);
    System.out.println("Cargo Ship Parameters \n--------------------------------------------------");
    System.out.println("Number of stacks: ");
    int numStacks = sc.nextInt();
    sc.nextLine();
    System.out.println("Maximum height of stacks: ");
    int maxHeight = sc.nextInt();
    sc.nextLine();
    System.out.println("Maximum total cargo weight: ");
    double maxWeight = sc.nextDouble();
    sc.nextLine();
    CargoShip ship = new CargoShip(numStacks, maxHeight, maxWeight);
    System.out.println("\nCargo ship created. \nPulling ship in to dock... \nCargo ship ready to be loaded.\n");
    String choice;
    boolean justPrinted = false;
    String[] shipStrings = new String[numStacks];
    for (int i = 0; i < shipStrings.length; i++){
      shipStrings[i] = "Stack " + (i + 1) + ": ";
    }
    String dockString = "Dock: ";


    while (true){
      printMainMenu();
      System.out.print("\nSelect a menu option: ");
      choice = sc.nextLine();

      switch(choice.trim().toUpperCase()) {
        case "C":
        System.out.println("Enter the name of the cargo: ");
        String name = sc.nextLine();
        System.out.println("Enter the weight of the cargo: ");
        double weight = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter the container strength (F/M/S): ");
        String strengthChoice = sc.nextLine();
        CargoStrength strength;
        if (strengthChoice.toUpperCase().equals("F")){
          strength = CargoStrength.FRAGILE;
        }
        else if (strengthChoice.toUpperCase().equals("M")) {
          strength = CargoStrength.MODERATE;
        }
        else if (strengthChoice.toUpperCase().equals("S")){
          strength = CargoStrength.STURDY;
        }
        else {
          System.out.println("Please enter a valid container strength");
          break;
        }
        try {
          Cargo newCargo = new Cargo(name, weight, strength);
          if (!dock.empty()){
            if (dock.peek().getStrength().value >= strength.value){
              dock.push(newCargo);
              System.out.println("Cargo " + "'" + name + "'" + " pushed onto the dock.\n");
              dockString = stringAddCargo(dockString, strengthChoice.toUpperCase());
            }
            else {
              System.out.println("Operation failed! Cargo at top of stack cannot support weight.\n");
            }
          }
          else {
            dock.push(newCargo);
            System.out.println("Cargo " + "'" + name + "'" + " pushed onto the dock.\n");
            dockString = stringAddCargo(dockString, strengthChoice);
          }
        }
        catch(Exception ex){
          System.out.println("\n" + ex.getMessage());
        }
        break;

        case "L":
        System.out.println("Select the load destination stack index: ");
        int loadDestination = sc.nextInt();
        sc.nextLine();
        try {
          ship.pushCargo(dock.peek(), loadDestination);
          System.out.println("\nCargo " + "'" + dock.pop().getName() +  "'" + " moved from dock to stack " + loadDestination + ".\n");
          String strLoaded = getHighestCargo(dockString);
          shipStrings[loadDestination - 1] = stringAddCargo(shipStrings[loadDestination - 1], strLoaded );
          dockString = stringRemoveCargo(dockString);
        }

        catch(Exception ex){
          System.out.println("\n" + ex.getMessage() + "\n");
        }
        break;

        case "U":
        System.out.println("Source stack index: ");
        int unloadSourceStack = sc.nextInt();
        sc.nextLine();
        try {
          dock.push(ship.peekCargo(unloadSourceStack));
          String unloadedName = ship.popCargo(unloadSourceStack).getName();
          String removedStrength = getHighestCargo(shipStrings[unloadSourceStack - 1]);
          shipStrings[unloadSourceStack - 1] =  stringRemoveCargo(shipStrings[unloadSourceStack - 1]);
          dockString = stringAddCargo(dockString, removedStrength);
          System.out.println("Cargo " + "'" + unloadedName + "'" +  " moved from stack " + unloadSourceStack + " to dock.\n");
        }
        catch (Exception ex){
          System.out.println("\n" + ex.getMessage() + "\n");
        }
        break;


        case "M":
        System.out.println("Source stack index: ");
        int sourceStack = sc.nextInt();
        sc.nextLine();
        System.out.println("Destination stack index: ");
        int destStack = sc.nextInt();
        sc.nextLine();
        try {
          ship.pushCargo(ship.peekCargo(sourceStack), destStack);
          String movedName = ship.popCargo(sourceStack).getName();
          String removedStr = getHighestCargo(shipStrings[sourceStack - 1]);
          shipStrings[sourceStack - 1] = stringRemoveCargo(shipStrings[sourceStack - 1]);
          shipStrings[destStack - 1] = stringAddCargo(shipStrings[destStack - 1], removedStr);
          System.out.println("Cargo " + "'" + movedName + "'" +  " moved from stack " + sourceStack + " to stack " + 2 + ".");
        }
        catch (Exception ex){
          System.out.println("\n" + ex.getMessage() + "\n");
        }
        break;

        case "K":
        while (!dock.empty()){
          dock.pop();
          dockString = stringRemoveCargo(dockString);
        }
        System.out.println("\nDock cleared.\n");

        break;

        case "P":
        System.out.println();
        for (int i = 0; i < numStacks; i++){
          System.out.println(shipStrings[i]);
        }
        System.out.println(dockString + "\n");
        System.out.println("Total weight = " + ship.getCurrentWeight() + "\n\n");
        justPrinted = true;
        break;

        case "S":
        System.out.println("Enter the name of the cargo: ");
        String searchName = sc.nextLine();
        ship.findAndPrint(searchName);
        justPrinted = true;
        break;

        case "Q":
        System.out.println("\nProgram terminating normally...");
        System.exit(0);
        break;

        default:
        System.out.println("\nInvalid entry for the menu. Please enter an appropriate choice.");
        break;

      }
      if (!justPrinted){
        for (int i = 0; i < numStacks; i++){
          System.out.println(shipStrings[i]);
        }
        System.out.println(dockString + "\n");
        System.out.printf("Total weight = " + "%1.0f" + " / " + "%1.0f" + "\n\n", ship.getCurrentWeight(), ship.getMaxWeight());
      }
      justPrinted = false;
    }
  }

  private static void printMainMenu(){
    System.out.println("Please select an option:");
    System.out.println("C) Create new cargo");
    System.out.println("L) Load cargo from dock");
    System.out.println("U) Unload cargo from ship");
    System.out.println("M) Move cargo between stacks");
    System.out.println("K) Clear dock");
    System.out.println("P) Print ship stacks");
    System.out.println("S) Search for cargo");
    System.out.println("Q) Quit");
  }

  //used to add a stack to the stackstring
  private static String stringAddCargo(String stackString, String strength){
    char checkChar = stackString.charAt(stackString.length() - 1);
    if (checkChar == 'F' || checkChar == 'M' || checkChar == 'S'){
      return stackString + ", " + strength ;
    }
    else return stackString + strength;
  }

  //used to remove the top cargo from a stackstring
  private static String stringRemoveCargo(String stackString){
    if (stackString.indexOf(",") > -1){
      return stackString.substring(0, stackString.length() - 3);
    }
    else return stackString.substring(0, stackString.length() - 1);
  }

  //gets the cargo strength of the top cargo
  private static String getHighestCargo(String stackString){
    return String.valueOf(stackString.charAt(stackString.length()- 1));
  }
}
