/**
* A class for testing Vehicle, VehicleQueue, TwoWayRoad, and Intersection
* Simulates an intersection of TwoWayRoads
* Takes command line prompts as Input
* Run by java IntersectionSimulator simTime arrivalProbability numRoads name1 name2... greenTime1 greenTime2...
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #4 for CSE 214, Spring 2023
*    Recitation 03
*
*    March 20th, 2023
*/
public class IntersectionSimulator{
  public static void main(String[] args){
    if (args.length > 1) {
      int simTime    = Integer.parseInt(args[0]);
      double prob    = Double.parseDouble(args[1]);
      int numRoads   = Integer.parseInt(args[2]);
      String[] names = new String[Intersection.MAX_ROADS];
      int[] times = new int[Intersection.MAX_ROADS];

      for (int i = 0; i < numRoads; ++i) {
        names[i] = args[3 + i];
        times[i] = Integer.parseInt(args[3 + numRoads + i]);
      }
      System.out.println("Welcome to IntersectionSimulator 2023");
      System.out.println("\nStarting Simulation...\n");
      System.out.println("################################################################################\n");
      simulate(simTime, prob, names, times);
      System.out.println("\nEnd Simulation.");
    }
    else {
      System.out.println("Inputs must be correct.");
      System.exit(0);
    }
  }

  public static void simulate(int simulationTime, double arrivalProbability, String[] roadNames, int[] maxGreenTimes){
    int carsWaiting = 0;
    int carsPassed = 0;
    double totalWait = 0;
    int longestWait = 0;
    int totalWaitTime = 0;
    int passedTime = 1;
    try{
      TwoWayRoad[] roads = new TwoWayRoad[Intersection.MAX_ROADS];
      for (int i = 0; i < Intersection.MAX_ROADS; i++){
        roads[i] = new TwoWayRoad(roadNames[i], maxGreenTimes[i]);
      }
      Intersection intersection = new Intersection(roads);
      BooleanSourceHW4 arrival = new BooleanSourceHW4(arrivalProbability);

      while (passedTime <= simulationTime || carsWaiting > 0){
        String[] enqueueMessages = new String[Intersection.MAX_ROADS * TwoWayRoad.NUM_WAYS * TwoWayRoad.NUM_LANES];
        boolean timesUp = false;
        int messageCounter = 0;
        if (passedTime > simulationTime){
          timesUp = true;
        }
        else {
          for (int i = 0; i < Intersection.MAX_ROADS; i++){
            for(int j = 0; j < TwoWayRoad.NUM_WAYS; j++){
              for(int k = 0; k < TwoWayRoad.NUM_LANES; k++){
                if (arrival.occursHW4()) {
                  Vehicle arrivedVehicle = new Vehicle(passedTime);
                  intersection.enqueueVehicle(i, j, k, arrivedVehicle);
                  carsWaiting++;
                  String num = String.format("%03d", arrivedVehicle.getSerialId());
                  enqueueMessages[messageCounter] = "\t\tCar[" + num + "] has entered " +
                  roadNames[i] + ", going " + getDirection(j) + " in " + getLane(k) + " lane.";
                  messageCounter++;
                }
              }
            }
          }
        }
        System.out.println("Time Step: " + passedTime);
        Vehicle[] removed = intersection.timeStep();
        System.out.println("\n\t" + intersection.getLightStatus()  + " for " + roadNames[intersection.getLightIndex()] + ".");
        System.out.println("\tTimer = " + (intersection.getCountdownTimer() + 1));
        //either no more cars arriving or print cars
        if (timesUp) System.out.println("\n\tCars no longer arriving.");
        else {
          System.out.println("\n\tARRIVING CARS:");
          for (int i = 0; i < enqueueMessages.length; i ++){
            if (enqueueMessages[i] != null){
              System.out.println(enqueueMessages[i]);
            }
          }
        }

        System.out.println("\n\tPASSING CARS:");
        for(int i = 0; i < removed.length; i++){
          if (removed[i] != null) {
            int waitTime = passedTime - removed[i].getArrivalTime();
            totalWaitTime += waitTime;
            if (waitTime > longestWait) longestWait = waitTime;
            carsWaiting--;
            carsPassed++;
            String num = String.format("%03d", removed[i].getSerialId());
            System.out.println("\t\tCar[" + num + "] passes through. Wait time of " + waitTime + ".");
          }
        }
        System.out.println();
        intersection.display();
        System.out.println("\n\tStatistics:");
        System.out.printf("\n\t\t%-25s%1d%1s", "Cars currently waiting: ", carsWaiting, " cars");
        System.out.printf("\n\t\t%-25s%1d%1s", "Total cars passed: ", carsPassed, " cars");
        System.out.printf("\n\t\t%-25s%1d%1s", "Total wait time: ", totalWaitTime, " turns");
        System.out.printf("\n\t\t%-25s%1.2f%1s", "Average wait time: ", ((double)totalWaitTime / (double) carsPassed), " turns\n\n");
        System.out.println("################################################################################");
        passedTime++;
      }
      System.out.println("################################################################################");
      System.out.println("################################################################################");
      System.out.println("\nSIMULATION SUMMARY:");
      System.out.printf("\n\t%-22s%1d", "Total Time: ", passedTime - 1);
      System.out.printf("\n\t%-22s%1d", "Total vehicles: ", carsPassed);
      System.out.printf("\n\t%-22s%1d", "Longest wait time: ", longestWait);
      System.out.printf("\n\t%-22s%1d", "Total wait time: ", totalWaitTime);
      System.out.printf("\n\t%-22s%1.2f\n", "Average wait time: ", ((double) totalWaitTime / carsPassed));
    }
    catch(Exception ex){
      System.out.println(ex.getMessage());
      System.out.println("Please run the program again with valid inputs.");
      System.exit(0);
    }

  }

  /*
  * Static method that returns the String for the direction given an int
  *
  * @returns
  * String for direction
  */
  public static String getDirection(int value){
    String returned = "";
    if (value == 0) returned = "FORWARD";
    else if (value == 1) returned = "BACKWARD";
    return returned;
  }

  /*
  * Static method that returns the String for the direction given an int
  *
  * @returns
  * String for direction
  */
  public static String getLane(int value){
    String returned = "";
    if (value == 0) returned = "LEFT";
    else if (value == 1) returned = "MIDDLE";
    else if (value == 2) returned = "RIGHT";
    return returned;
  }

}
