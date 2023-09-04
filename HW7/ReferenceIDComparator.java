/**
* An class that implements the comparator interface to compare
* near earth objects by their reference id
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #7 for CSE 214, Spring 2023
*    Recitation 03
*
*    Apr 21th, 2023
*/
public class ReferenceIDComparator implements java.util.Comparator<NearEarthObject>{

  /*
  * Method that compares two near earth objects by
  * their reference id
  *
  * @param1 o1
  * first object to compare
  *
  * @param2 o2
  * second object to compare
  *
  * @returns
  * 1 if the reference id of o1 is greater than o2,
  * -1 if its it's less, and 0 if they're equal
  */
  public int compare(NearEarthObject o1, NearEarthObject o2) {
    if (o1.getReferenceID() > o2.getReferenceID()) return 1;
    else if (o1.getReferenceID() < o2.getReferenceID()) return -1;
    else return 0;
  }
}
