/**
* An enum for the strength of cargo
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #3 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 28, 2023
*/

public enum CargoStrength{
  FRAGILE(1),
  MODERATE(2),
  STURDY(3);
  public final int value;

  private CargoStrength(int value){
    this.value = value;
  }


}
