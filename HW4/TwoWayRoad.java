/**
* An representation of a two way road
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #4 for CSE 214, Spring 2023
*    Recitation 03
*
*    Mar 20, 2023
*/
import java.lang.Math;
public class TwoWayRoad{
  public static final int FORWARD_WAY = 0;

  public static final int BACKWARD_WAY = 1;

  public static final int NUM_WAYS = 2;

  public static final int LEFT_LANE = 0;

  public static final int MIDDLE_LANE = 1;

  public static final int RIGHT_LANE = 2;

  public static final int NUM_LANES = 3;

  private String name;

  private int greenTime;

  private int leftSignalGreenTime;

  private VehicleQueue lanes[][];

  private LightValue lightValue;

  /**
  * This is the constructor for the Two Way Road
  *
  * @param1 initName
  * This is the name of the road
  *
  * @param2 initGreenTime
  * This is the initial green time of the road
  *
  * Precondition:
  * initName isn't null and green time is greater than 0
  *
  * Postcondition:
  * A new two way road is created
  *
  * @throws IllegalArgumentException
  * if the inputs are null or initGreenTime is less than 0
  */
  public TwoWayRoad(String initName, int initGreenTime) throws IllegalArgumentException{
    if (initGreenTime <= 0) throw new IllegalArgumentException("Green light time cannot be less than or equal to 0");
    if (initName == null) throw new IllegalArgumentException("Initial name cannot be 0");
    name = initName;
    greenTime = initGreenTime;
    leftSignalGreenTime = (int) Math.floor(1.0 / (double) NUM_LANES * initGreenTime);
    lightValue = LightValue.GREEN;
    lanes = new VehicleQueue[NUM_WAYS][NUM_LANES];
    for (int i = 0; i < NUM_WAYS; i++) {
      for (int j = 0; j < NUM_LANES; j++) {
        lanes[i][j] = new VehicleQueue();
      }
    }
  }

  /**
  * This method returns the green time of the two way road
  *
  * @return
  * A int that is the green time
  */
  public int getGreenTime(){
    return greenTime;
  }

  /**
  * This method returns the light value of the two way road
  *
  * @return
  * A LightValue enum that is the current light value
  */
  public LightValue getLightValue(){
    return lightValue;
  }

  /**
  * This method returns the route name of the road
  *
  * @return
  * A String that is the name of the road
  */
  public String getRouteName(){
    return name;
  }

  /**
  * This changes the light depending on the inputted timerVal, and dequeues cars
  * appropriately
  *
  * @param1 timerVal
  * This is the value of the timer of the current road, to see if there needs to be a light change
  *
  * @returns
  * A Vehicle[] of cars dequeued from the road
  */
  public Vehicle[] proceed(int timerVal){
    Vehicle[] removed = new Vehicle[4];
    int counter = 0;

    if (timerVal <= leftSignalGreenTime ||
    (lanes[FORWARD_WAY][MIDDLE_LANE].isEmpty() && lanes[FORWARD_WAY][RIGHT_LANE].isEmpty() &&
    lanes[BACKWARD_WAY][MIDDLE_LANE].isEmpty() && lanes[BACKWARD_WAY][RIGHT_LANE].isEmpty() && lightValue == LightValue.GREEN && timerVal != greenTime)){
      lightValue = LightValue.LEFT_SIGNAL;
    }
    else if (lightValue!= LightValue.LEFT_SIGNAL)lightValue  = LightValue.GREEN;

    for (int i = 0; i < NUM_WAYS; i++){
      for (int j = 0; j < NUM_LANES; j++){
        if (!lanes[i][j].isEmpty()){
          if (j == LEFT_LANE && lightValue == LightValue.LEFT_SIGNAL) {
            removed[counter] = lanes[i][j].dequeue();
            counter++;
          }
          else if (j > LEFT_LANE && lightValue == LightValue.GREEN){
            removed[counter] = lanes[i][j].dequeue();
            counter++;
          }
        }
      }
    }
    return removed;
  }

  /**
  * This method sets the light of the road to RED
  *
  * Postonditions:
  * The lightValue is now RED
  */
  public void setRed(){
    lightValue = LightValue.RED;
  }

  /**
  * This method sets the light of the road to GREEN
  *
  * Postonditions:
  * The lightValue is now GREEN
  */
  public void setGreen(){
    lightValue = LightValue.GREEN;
  }

  /**
  * This method enqueues vehicles into a given road lane
  *
  * @param1 wayIndex
  * This is the direction the vehicle is going
  *
  * @param2 laneIndex
  * This is the lane tha car is in
  *
  * @param3 vehicle
  * this is the vehicle to be enqueued
  *
  * @throws IllegalArgumentException
  * if inputs are invalid
  *
  * Postonditions:
  * The vehicle is enqueued properly or IllegalArgumentException is thrown
  */
  public void enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle){
    if (wayIndex > 1 || wayIndex < 0 || laneIndex < 0 || laneIndex > 2)
    throw new IllegalArgumentException("Invalid input for lane or direction.");
    if (vehicle == null)
    throw new IllegalArgumentException("Vehicle must not be null");
    lanes[wayIndex][laneIndex].enqueue(vehicle);
  }

  /**
  * This method checks if a given lane is empty
  *
  * @param1 wayIndex
  * This is the direction of the lane
  *
  * @param2 laneIndex
  * This is the type of lane
  *
  * @returns
  * true if the lane is empty, false otherwise
  *
  * @throws IllegalArgumentException
  * if the inputs are invalid
  */
  public boolean isLaneEmpty(int wayIndex, int laneIndex){
    if (wayIndex > 1 || wayIndex < 0 || laneIndex < 0 || laneIndex > 2)
    throw new IllegalArgumentException("Invalid input for lane or direction.");
    return lanes[wayIndex][laneIndex].isEmpty();
  }

  /**
  * This method returns a string representation desired name1
  *
  * @param1 direction
  * direction of the desired lane
  *
  * @param2 lane
  * position index of the desired lane
  *
  * @returns
  * a String representation of the cars in the lane
  */
  public String toString(int direction, int lane){
    return lanes[direction][lane].toString();
  }

  /**
  * This method returns a reverse string representation desired name1
  *
  * @param1 direction
  * direction of the desired lane
  *
  * @param2 lane
  * position index of the desired lane
  *
  * @returns
  * a reverse String representation of the cars in the lane
  */
  public String toStringReverse(int direction, int lane){
    return lanes[direction][lane].toStringReverse();
  }
}
