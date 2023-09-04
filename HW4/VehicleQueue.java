/**
* A class meant to represent a queue of vehicles in traffic
* Extends java.util.LinkedList
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #4 for CSE 214, Spring 2023
*    Recitation 03
*
*    Mar 20nd, 2023
*/
import java.util.*;
public class VehicleQueue extends LinkedList{

  /**
  * Constructor for the VehicleQueue
  *
  * Is the same as java.util.LinkedList
  */
  public VehicleQueue() {
    super();
  }

  /**
  * This method enqueues a vehicle
  *
  * @param1 v
  * vehicle to be enqueued
  *
  * Postconditions:
  * v is enqueued into the vehicle queue
  */
  public void enqueue(Vehicle v){
    this.addLast(v);
  }

  /**
  * This method enqueues a vehicle
  *
  * Preconditions:
  * The queue isn't already empty
  *
  * Postconditions:
  * an element is dequeued from the vehicle queue
  */
  public Vehicle dequeue(){
    if (this.peek() == null) throw new IllegalArgumentException("Stack is already empty");
    return (Vehicle) this.removeFirst();
  }

  /**
  * This method determines whether the queue is empty or not
  *
  * @returns
  * true if the queue is empty, false otherwise
  */
  public boolean isEmpty(){
    if (this.peek() == null) return true;
    else return false;
  }

  /**
  * This method returns a String representation of vehicles in the queue in reverse
  *
  * @returns
  * String representation of vehicles in the queue in reverse
  */
  public String toStringReverse(){
    String result = "";
    Stack<Vehicle> reverse = new Stack();
    Stack<Vehicle> reverse2 = new Stack();
    int origSize = this.size();
    for (int i = 0; i < origSize; i++){
      reverse.push(this.dequeue());
    }
    for (int i = 0; i < origSize; i++){
      Vehicle insert = reverse.pop();
      String num = String.format("%03d", insert.getSerialId());
      if (i + 1 != origSize) result += "[" + num + "] ";
      else result += "[" + num + "]";
      reverse2.push(insert);
    }
    for (int i = 0; i < origSize; i++){
      this.enqueue(reverse2.pop());
    }
    return result;
  }

  /**
  * This method returns a String representation of vehicles in the queue
  *
  * @returns
  * String representation of vehicles in the queue
  */
  public String toString(){
    String result = "";
    VehicleQueue temp = new VehicleQueue();
    int origSize = this.size();
    for (int i = 0; i < origSize; i++){
      Vehicle removed = this.dequeue();
      String num = String.format("%03d", removed.getSerialId());
      result += "[" + num + "] ";
      temp.enqueue(removed);
    }
    for (int i = 0; i < origSize; i++){
      this.enqueue(temp.dequeue());
    }
    return result;
  }
}
