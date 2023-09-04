/**
* An representation an intersection between two way roads
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #4 for CSE 214, Spring 2023
*    Recitation 03
*
*    Mar 20, 2023
*/
public class Intersection{
  public static int MAX_ROADS = 2;

  private TwoWayRoad[] roads;

  private int lightIndex;

  private int countdownTimer;

  private boolean justChanged;

  /**
  * This is the constructor for the Intersection
  *
  * @param1 initRoads
  * This is the number of stacks of cargo on the CargoShip
  *
  * Precondition:
  * initRoads is initialized and its size is not greater than MAX_ROADS
  *
  * Postcondition:
  * A new Intersection is created
  *
  * @throws IllegalArgumentException
  * if the input is too large or its null
  */
  public Intersection(TwoWayRoad[] initRoads){
    if (initRoads.length > MAX_ROADS)
    throw new IllegalArgumentException("Input array is too large");
    if (initRoads == null)
    throw new IllegalArgumentException("Input two way roads cannot be null");
    for (int i = 0; i < initRoads.length;i++){
      if (initRoads[i] == null) throw new IllegalArgumentException("Input two way roads cannot be null");
    }
    lightIndex = 0;
    roads = initRoads;
    countdownTimer = roads[lightIndex].getGreenTime();
  }

  /**
  * This method returns the light index of the intersection
  *
  * @return
  * A int that is the lightIndex
  */
  public int getLightIndex(){
    return lightIndex;
  }

  /**
  * This method returns the light of the intersection in a String
  *
  * @return
  * A String that is the current light of the road with lightIndex
  */
  public String getLightStatus(){
    if (roads[lightIndex].getLightValue() == LightValue.GREEN)
    return "Green Light";
    else if (roads[lightIndex].getLightValue() == LightValue.RED) return "Red Light";
    else return "Left Signal";
  }

  /**
  * This method simulates one step in the intersection simulator.
  * It changes the lightIndex of the Intersection and returns the dequeued vehicles
  * in an array
  *
  * @return
  * A Vehicle[] of dequeued vehicles in this timeStep
  */
  public Vehicle[] timeStep(){
    //reset timer and switch light if countdownTimer == 0 or light is about to be preempt
    if (countdownTimer == 0 ||
    (roads[lightIndex].isLaneEmpty(TwoWayRoad.FORWARD_WAY, TwoWayRoad.LEFT_LANE) && roads[lightIndex].isLaneEmpty(TwoWayRoad.BACKWARD_WAY, TwoWayRoad.LEFT_LANE)
    && roads[lightIndex].getLightValue() == LightValue.LEFT_SIGNAL)){
      roads[lightIndex].setRed();
      lightIndex = (lightIndex + 1) % roads.length;
      countdownTimer = roads[lightIndex].getGreenTime();
      roads[lightIndex].setGreen();
    }
    Vehicle[] removed = roads[lightIndex].proceed(countdownTimer);
    countdownTimer--;
    return removed;
  }

  /**
  * This method returns the countdownTimer
  *
  * @return
  * A int that is the countdownTimer
  */
  public int getCountdownTimer(){
    return countdownTimer;
  }
  /**
  * This enqueues a vehicle into a specific road index, way index, lane index, and vehicle
  *
  * @param1 roadIndex
  * index in the roads[] of the road being enqueued to
  *
  * @param2 wayIndex
  * direction the car is going
  *
  * @param3 laneIndex
  * lane the car is going
  *
  * @param4 vehicle
  * vehicle being enqueued
  *
  * preconditions:
  * road index, way index, lane index, and vehicle are appropriate and or not null
  *
  * Postconditions:
  * A new vehicle is enqueued apprpriately
  */
  public void enqueueVehicle(int roadIndex, int wayIndex, int laneIndex, Vehicle vehicle){
    if (0 > roadIndex || roadIndex >= roads.length)
    throw new IllegalArgumentException("Invalid road index");
    if (0 > wayIndex || wayIndex >= TwoWayRoad.NUM_WAYS)
    throw new IllegalArgumentException("Invalid direction index");
    if (0 > laneIndex || laneIndex >= TwoWayRoad.NUM_LANES)
    throw new IllegalArgumentException("Invalid lane index");
    if (vehicle == null)
    throw new IllegalArgumentException("Vehicle cannot be null");
    roads[roadIndex].enqueueVehicle(wayIndex, laneIndex, vehicle);
  }

  /**
  * This displays the current roads in a diagram
  *
  * Postconditions:
  * A neatly formatted diagram of the roads is printed
  */
  public void display(){
    for (int i = 0; i < MAX_ROADS; i++){
      System.out.println("\t" + roads[i].getRouteName() + ":");
      System.out.println("\t                       FORWARD               BACKWARD");
      System.out.println("\t==============================               ===============================");
      System.out.printf("\t%30s", roads[i].toStringReverse(TwoWayRoad.FORWARD_WAY, TwoWayRoad.LEFT_LANE));
      System.out.print(" [L] ");
      if (roads[i].getLightValue() != LightValue.LEFT_SIGNAL) System.out.print("x   ");
      else System.out.print("    ");
      if (roads[i].getLightValue() != LightValue.GREEN || i != lightIndex) System.out.print("x ");
      else System.out.print("  ");
      System.out.print("[R] " + roads[i].toString(TwoWayRoad.BACKWARD_WAY, TwoWayRoad.RIGHT_LANE));
      System.out.println("\n\t------------------------------               -------------------------------");
      System.out.printf("\t%30s", roads[i].toStringReverse(TwoWayRoad.FORWARD_WAY, TwoWayRoad.MIDDLE_LANE));
      System.out.print(" [M] ");
      if (roads[i].getLightValue() != LightValue.GREEN || i != lightIndex) System.out.print("x   ");
      else System.out.print("    ");
      if (roads[i].getLightValue() != LightValue.GREEN || i != lightIndex) System.out.print("x ");
      else System.out.print("  ");
      System.out.print("[M] " + roads[i].toString(TwoWayRoad.BACKWARD_WAY, TwoWayRoad.MIDDLE_LANE));
      System.out.println("\n\t------------------------------               -------------------------------");
      System.out.printf("\t%30s", roads[i].toStringReverse(TwoWayRoad.FORWARD_WAY, TwoWayRoad.RIGHT_LANE));
      System.out.print(" [R] ");
      if (roads[i].getLightValue() != LightValue.GREEN || i != lightIndex ) System.out.print("x   ");
      else System.out.print("    ");
      if (roads[i].getLightValue() != LightValue.LEFT_SIGNAL) System.out.print("x ");
      else System.out.print("  ");
      System.out.print("[L] " + roads[i].toString(TwoWayRoad.BACKWARD_WAY, TwoWayRoad.LEFT_LANE));
      System.out.println("\n\t==============================               ===============================");
      System.out.println();
    }
    justChanged = false;
  }
}
