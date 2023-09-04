/**
* A class meant to represent a stack of cargo as a stack data structure
* This is a wrapper for the java.util.Stack class
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
public class CargoStack extends Stack{

  /**
  * Constructor for the Cargo Stack
  *
  * Is the same as java.util.Stack
  */
  public CargoStack(){
      super();
  }

  /**
  * This getter method returns the Cargo top of the Cargo stack
  *
  * @return the Cargo at the top of the stack
  */
  public Cargo peek(){
    return (Cargo) super.peek();
  }

  /**
  * This method pushes a Cargo onto the CargoStack
  *
  * @param1 cargo
  * desired cargo on the stack
  *
  * Postconditions
  * New cargo is at the top of the stack
  */
  public void push(Cargo cargo){
    super.push(cargo);
  }

  /**
  * This method pops a Cargo from  the CargoStack
  *
  * Postconditions
  * The top cargo is removed from the stack
  *
  * @returns
  * Cargo that was popped from the stack
  */
  public Cargo pop(){
    return (Cargo) super.pop();
  }
}
