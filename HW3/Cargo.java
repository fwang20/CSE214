/**
* An abstract data type meant to be a representation
* of a slide (in a slideshow)
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #3 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 28nd, 2023
*/
public class Cargo{
  private String name;

  private double weight;

  private CargoStrength strength;

  /**
  * This is the constructor for a Cargo.
  * It creates a Cargo object with variables for a name, weight, and strength of the Cargo
  */
  public Cargo(String initName, double initWeight, CargoStrength initStrength) throws IllegalArgumentException{
    if (initName == null || initWeight <= 0) throw new IllegalArgumentException("Invalid constructor parameters");
    name = initName;
    weight = initWeight;
    strength = initStrength;
  }

  /**
  * This method returns the name of the Cargo
  *
  * @return
  * A string that is the name of the Cargo
  *
  */
  public String getName(){
    return name;
  }

  /**
  * This method returns the weight of the Cargo
  *
  * @return
  * A double that is the weight of the Cargo
  *
  */
  public double getWeight(){
    return weight;
  }

  /**
  * This method returns the CargoStrength of the Cargo
  *
  * @return
  * A CargoStrength enum that is the strength of the cargo
  *
  */
  public CargoStrength getStrength(){
    return strength;
  }

}
