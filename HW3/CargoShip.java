/**
* An representation of a cargo ship
* using an array of stacks for cargo.
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
public class CargoShip{
  private CargoStack[] stacks;

  private int[] stackHeights;

  private int maxHeight;

  private double maxWeight;

  private double currentWeight;

  /**
  * This is the constructor for the CargoShip
  *
  * @param1 numStacks
  * This is the number of stacks of cargo on the CargoShip
  *
  * @param2 initMaxHeight
  * This is the desired max height of the stacks of Cargo
  *
  * @param 3 initMaxWeight
  * This is the desired max weight of the CargoShip
  *
  * Precondition:
  * numStacks, initMaxHeight,initMaxHeight are greater than 0
  *
  * Postcondition:
  * A new CargoShip is created
  *
  * @throws IllegalArgumentException
  * if inputs are less than or equal to 0.
  */
  public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight) throws IllegalArgumentException{
    if (numStacks <= 0 || initMaxHeight <= 0 || initMaxWeight <= 0) throw new IllegalArgumentException("Invalid constructor parameters");
    stacks = new CargoStack[numStacks];
    stackHeights = new int[numStacks];
    for (int i = 0; i < numStacks; i++){
      stacks[i] = new CargoStack();
      stackHeights[i] = 0;
    }
    maxHeight = initMaxHeight;
    maxWeight = initMaxWeight;
    currentWeight = 0;
  }

  /**
  * This method pushes an inputted Cargo into the inputted stack index
  *
  * @param1 cargo
  * This is cargo that is being pushed onto the CargoShip
  *
  * @param2 stack
  * This is the index of the stack that the cargo is being pushed onto.
  *
  * Precondition:
  * CargoShip is initialized
  * cargo is initialized
  * 1 < stack < stacks.length
  * size of the stack is less than getMaxHeight
  * The total weight of cargo on the ship and the new cargo is less than max weight
  *
  * Postcondition:
  * The cargo has been successfully pushed to the given stack
  * or an error is thrown
  *
  * @throws IllegalArgumentException
  * if cargo is null or stack index is invalid
  *
  * @throws FullStackException
  * if Stack is already full
  *
  * @throws ShipOverWeightException
  * if Stack is already at maxWeight
  *
  * @throws CargoStrengthException
  * if cargo's strength is too high to be put on the stack
  */
  public void pushCargo(Cargo cargo, int stack) throws IllegalArgumentException, FullStackException, ShipOverWeightException, CargoStrengthException{
    if (cargo == null || stack > stacks.length || stack < 1)
    throw new IllegalArgumentException("Invalid input for pushing cargo");
    if (stackHeights[stack - 1] == maxHeight)
    throw new FullStackException("Operation failed! Cargo stack is at maximum height.");
    if (cargo.getWeight() + currentWeight > maxWeight)
    throw new ShipOverWeightException("Operation failed! Cargo would put ship overweight.");
    if (!stacks[stack - 1].empty()){
      if (cargo.getStrength().value > stacks[stack - 1].peek().getStrength().value)
      throw new CargoStrengthException("Operation failed! Cargo at top of stack cannot support weight.");
    }
    if (stacks[stack - 1] != null){
      stacks[stack - 1].push(cargo);
      currentWeight += cargo.getWeight();
      stackHeights[stack - 1]++;
    }
  }

  /**
  * This method pops a cargo from the designated stack
  *
  * @param1 stack
  * This is cargo that is being popped from the stack
  *
  * Precondition:
  * CargoShip is initialized
  * 1 < stack < stacks.length
  *
  * Postcondition:
  * The cargo has been successfully pushed to the given stack
  * or an error is thrown
  *
  * @returns
  * Cargo that was popped
  *
  * @throws IllegalArgumentException
  * if stack index is invalid
  *
  * @throws EmptyStackException
  * if Stack is already empty
  */
  public Cargo popCargo(int stack) throws EmptyStackException{
    if (stack > stacks.length || stack < 1)
    throw new IllegalArgumentException("Invalid input for popping cargo");
    if (stacks[stack - 1].empty())
    throw new EmptyStackException("Operation failed! There is no cargo to remove.");

    Cargo removed = stacks[stack - 1].pop();
    currentWeight -= removed.getWeight();
    stackHeights[stack - 1]--;
    return removed;
  }

  /**
  * This method that returns the uppermost element on the stack designated
  *
  * @param1 stack
  * This is stack index of the stack that is being peeked at
  *
  * Precondition:
  * CargoShip is initialized
  * 1 < stack < stacks.length
  *
  * @returns
  * the top Cargo of the stack designated
  *
  * @throws IllegalArgumentException
  * if stack index is invalid
  *
  * @throws EmptyStackException
  * if Stack is already empty
  */
  public Cargo peekCargo(int stack) throws EmptyStackException{
    if (stack > stacks.length || stack < 1)
    throw new IllegalArgumentException("Invalid input for popping cargo");
    if (stacks[stack - 1].empty())
    throw new EmptyStackException("Stack is already empty");
    return stacks[stack - 1].peek();
  }

  /**
  * This method finds and prints a formatted table
  * of all the cargo on the ship with a given name.
  * If not found, prints an appropriate message
  *
  * @param1 name
  * name of the cargo the method is going to search for
  *
  * Preconditions:
  * CargoShip is initialized and not null
  *
  * Postconditions:
  * Prints out a table with the given cargo name,
  * or prints a message saying that it was not found.
  * The stacks remain unchanged.
  */
  public void findAndPrint(String name){
    CargoStack temp = new CargoStack();
    int count = 0;
    int totalWeight = 0;
    boolean found = false;
    String table = " Stack   Depth   Weight   Strength" + "\n=======+=======+========+==========";
    for (int i = 0; i < stacks.length; i++){
      int counter = 0;
      while (!stacks[i].empty()){
        Cargo removed = stacks[i].pop();
        temp.push(removed);
        if (removed.getName().equals(name)){
          String added = "\n  " + String.format("%2d%4s%4d%4s%5.0f%4s%2s%1s", (i + 1), "|", counter, "|", removed.getWeight(), "|", "  ", removed.getStrength());
          table = table + added;
          totalWeight += removed.getWeight();
          count++;
          found = true;
        }
        counter ++;
      }
      while (!temp.empty()){
        stacks[i].push(temp.pop());
      }
    }
    if (found) {
      System.out.println(table);
      System.out.println("\nTotal count:  " + count);
      System.out.println("Total weight: " + totalWeight);
    }
    else System.out.println("Cargo " + "'" + name + "'" + " could not be found on the ship\n");
  }

  /**
  * This method returns the maxWeight of the CargoShip
  *
  * @return
  * A double that is the max weight of the ship
  */
  public double getMaxWeight() {
    return maxWeight;
  }

  /**
  * This method returns the currentWeight of the CargoShip
  *
  * @return
  * A double that is the current total weight of the cargo ship
  */
  public double getCurrentWeight(){
    return currentWeight;
  }

  /**
  * This method returns the maxHeight of the stacks in the CargoShip
  *
  * @return
  * An int that is the max height of the CargoShip stacks
  */
  public int getMaxHeight(){
    return maxHeight;
  }
}
