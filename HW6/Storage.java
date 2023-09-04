/**
* An object meant to represent a storage box
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #6 for CSE 214, Spring 2023
*    Recitation 03
*
*    Apr 13th, 2023
*/
import java.io.Serializable;
public class Storage implements Serializable{
  private static long serialVersionUID = 0;
  private int id;
  private String client;
  private String contents;

  /**
  * Default constructor for the storage class
  *
  * @param1 id
  * desired id of the storage
  *
  * @param2 client
  * desired client of the storage
  *
  * @param3 contents
  * desired contents of the storage
  */
  public Storage(int id, String client, String contents){
    this.id = id;
    this.client = client;
    this.contents = contents;
  }

  /**
  * This method returns the id of the storage
  *
  * @return
  * A int that is the id
  */
  public int getId(){
    return id;
  }

  /**
  * This method returns the client String of the storage
  *
  * @return
  * A String that is the client
  */
  public String getClient(){
    return client;
  }

  /**
  * This method returns the contents of the storage
  *
  * @return
  * A string that is the contents of the storage
  */
  public String getContents(){
    return contents;
  }

  /**
  * Setter method that sets the id of the box
  *
  * @param1 newId
  * desired id of the storage
  *
  * Postconditions:
  * the id of the storage is changed ot the desired
  */
  public void setId(int newId){
    id = newId;
  }

  /**
  * Setter method that sets the client
  *
  * @param1 newClient
  * desired client of the storage
  *
  * Postconditions:
  * the client of the storage is changed to  the desired
  */
  public void setClient(String newClient){
    client = newClient;
  }

  /**
  * Setter method that sets the contents of the box
  *
  * @param1 newContents
  * desired contents of the storage
  *
  * Postconditions:
  * the contents of the storage is changed ot the desired
  */
  public void setContents(String newContents){
    contents = newContents;
  }
}
