/**
* An object meant to represent a vehicle at an intersection
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #4 for CSE 214, Spring 2023
*    Recitation 03
*
*    Mar 9th, 2023
*/
public class Vehicle{
  private static int serialCounter = 0;

  private int serialId;

  private int timeArrived;

  /**
  * Default constructor for the vehicle class
  *
  * @param1 initTimeArrived
  * desired time of arrival of the time of the cargo
  *
  * @throws IllegalArgumentException
  * if initTimeArrived is less than or equal to 0
  */
  public Vehicle(int initTimeArrived){
    if (initTimeArrived <= 0) throw new IllegalArgumentException("Invalid initial time");
    timeArrived = initTimeArrived;
    serialId = serialCounter;
    serialCounter++;
  }
  /**
  * This method returns the serial id of the car
  *
  * @return
  * A int that is the serial id
  */
  public int getSerialId(){
    return serialId;
  }

  /**
  * This method returns the arrival time of the vehicle
  *
  * @return
  * A int that is the arrival time
  */
  public int getArrivalTime(){
    return timeArrived;
  }
}
