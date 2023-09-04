/**
* An object meant to represent a Near Earth Object in space
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #7 for CSE 214, Spring 2023
*    Recitation 03
*
*    Apr 21th, 2023
*/
import java.util.Date;
public class NearEarthObject implements Comparable{
  private int referenceID;

  private String name;

  private double absoluteMagnitude;

  private double averageDiameter;

  private boolean isDangerous;

  private Date closestApproachDate;

  private double missDistance;

  private String orbitingBody;

  /**
  * Default constructor for the Near Earth Object
  *
  * @param1 referenceID
  * desired id of the object
  *
  * @param2 name
  * desired name of the object
  *
  * @param3 absoluteMagnitude
  * desired absolute magnitude of the object
  *
  * @param4 minDiameter
  * desired minimum diameter
  *
  * @param5 maxDiamater
  * desired max diamater
  *
  * @param6 isDangerous
  * boolean on whether or not the object is dangerous
  *
  * @param7 closestApproachDate
  * Date of its closest approach (in lepoch time)
  *
  * @param8 missDistance
  * desired missed distance of the closest approach
  *
  * @param9 orbitingBody
  * desired orbiting body of the object
  *
  * Postconditions
  * new near earth object created
  */
  public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter, double maxDiameter, boolean isDangerous, long closestDateTimestamp, double missDistance, String orbitingBody){
    this.referenceID = referenceID;
    this.name = name;
    this.absoluteMagnitude = absoluteMagnitude;
    this.averageDiameter = (minDiameter + maxDiameter) / 2;
    this.isDangerous = isDangerous;
    Date newClosestApproachDate = new Date(closestDateTimestamp);
    this.closestApproachDate = newClosestApproachDate;
    this.missDistance = missDistance;
    this.orbitingBody = orbitingBody;
  }

  /**
  * This method returns the reference id of the object
  *
  * @return
  * A int that is the reference id
  */
  public int getReferenceID(){
    return referenceID;
  }

  /**
  * This method sets the reference id of the object
  *
  * @param1 referenceID
  * desired id of the object
  *
  * Postconditions: referenceid is changed
  */
  public void setReferenceID(int referenceID){
    this.referenceID = referenceID;
  }

  /**
  * This method returns the name of the near earth object
  *
  * @return
  * A string that is the name of the object
  */
  public String getName(){
    return name;
  }

  /**
  * This method sets the name of the object
  *
  * @param1 name
  * desired name of the near earth object
  *
  * Postconditions:
  * name is changed
  */
  public void setName(String name){
    this.name = name;
  }


  /**
  * This method returns the absolute magnitude of the near earth object
  *
  * @returns
  * A double that is the absolute magnitude of the object
  */
  public double getAbsoluteMagnitude(){
    return absoluteMagnitude;
  }

  /**
  * This method sets the absolute magnitude of the object
  *
  * @param1 absoluteMagnitude
  * desired absolute magnitude of the oibjec
  *
  * Postconditions:
  * absolute magnitude is changed
  */
  public void setAbsoluteMagnitude(double absoluteMagnitude){
    this.absoluteMagnitude = absoluteMagnitude;
  }

  /**
  * This method returns the average diameter of the near earth object
  *
  * @returns
  * A double that is the average diameter of the object
  */
  public double getAverageDiameter(){
    return averageDiameter;
  }

  /**
  * This method sets the average diameter of the object
  *
  * @param1 averageDiameter
  * desired averageDIameter of the object
  *
  * Postconditions:
  * average diameter is changed
  */
  public void setAverageDiameter(double averageDiameter){
    this.averageDiameter = averageDiameter;
  }

  /**
  * This method returns the bolean value of isDangerous of the near earth object
  *
  * @returns
  * A boolean that is the isDangerous field of the object
  */
  public boolean getIsDangerous(){
    return isDangerous;
  }

  /**
  * This method sets the bolean value of the isDangerous field of the object
  *
  * @param1 getIsDangerous
  * desired  boolean value of IsDangerous of the oibjec
  *
  * Postconditions:
  * isDangerous is changed
  */
  public void setIsDangerous(boolean isDangerous){
    this.isDangerous = isDangerous;
  }

  /**
  * This method returns the closest Approach date of the near earth object
  *
  * @returns
  * A Date object that is the closest approach date of the object
  */
  public Date getClosestApproachDate(){
    return closestApproachDate;
  }

  /**
  * This method sets the closest approach date of the object
  *
  * @param1 closestApproachDate
  * desired closest approach date of the oibjec
  *
  * Postconditions:
  * closest approach date is changed
  */
  public void setClosestApproachDate(Date closestApproachDate){
    this.closestApproachDate = closestApproachDate;
  }

  /**
  * This method returns the miss distance of the near earth object
  *
  * @returns
  * A double that is the miss distance of the object
  */
  public double getMissDistance(){
    return missDistance;
  }

  /**
  * This method sets the miss distance of the object
  *
  * @param1 missDistance
  * desired miss distance  of the oibjec
  *
  * Postconditions:
  * miss distance is changed
  */
  public void setMissDistance(double missDistance){
    this.missDistance = missDistance;
  }

  /**
  * This method returns the orbiting body of the near earth object
  *
  * @returns
  * A string that is the orbiting body of the object
  */
  public String getOrbitingBody(){
    return orbitingBody;
  }

  /**
  * This method sets the orbiting body of the object
  *
  * @param1 absoluteMagnitude
  * desired orbiting body of the object
  *
  * Postconditions:
  * orbiting body is changed
  */
  public void setOrbitingBody(String orbitingBody){
    this.orbitingBody = orbitingBody;
  }

  /**
  * Default compareTo for this object, compares the referenceID
  *
  * @param1 o
  * object to be compared to this one
  *
  * @returns
  * 1 if the reference id of this one is greater, -1 if its less, and 0 if they are equal
  *
  */
  public int compareTo(Object o) {
    NearEarthObject otherObj = (NearEarthObject)o;
    if (this.averageDiameter == otherObj.averageDiameter)
    return 0;
    else if (this.averageDiameter > otherObj.averageDiameter)
    return 1;
    else
    return -1;
  }
}
