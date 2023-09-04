/**
* A class meant to represent self-storage by using a Hashtable
* Extends java.util.Hashtable
* Implements Serializable
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #6 for CSE 214, Spring 2023
*    Recitation 03
*
*    Apr 13nd, 2023
*/
import java.util.*;
import java.io.Serializable;
import java.util.Set;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class StorageTable extends Hashtable implements Serializable{
  private static int serialVersionUID = 0;

  /**
  * Constructor for the StorageTable
  *
  * Is the same as java.util.Hashtable
  */
  public StorageTable(){
    super();
  }

  /**
  * Saves the table to a file for future use
  *
  * @param1 storage
  * table to be stored;
  *
  * postconditions:
  * table is stored in a file for future use by the name of storage.obj
  */
  public static void saveTable(StorageTable storage) throws IOException{
    try{
      FileOutputStream file = new FileOutputStream("storage.obj");
      ObjectOutputStream outStream = new ObjectOutputStream(file);
      outStream.writeObject(storage);
      outStream.close();
    }
    catch(Exception ex){
      System.out.println(ex.getMessage());
    }
  }

  /*
  * Manually inserts a Storage object into the table using the specified key.
  *
  * @param1 storageId
  * the key for the storage in the StorageTable
  *
  * @param2 storage
  * the storage item being stored in the table
  *
  * preconditions:
  * storageId ≥ 0 and does not already exist in the table.
  * Storage ≠ null
  *
  * postconditions:
  * The Storage has been inserted into the table with the indicated key.
  *
  * @throws IllegalArgumentException
  * if the preconditions are not fulfilled
  */
  public void putStorage(int storageId, Storage storage) throws IllegalArgumentException{
    if(storageId < 0 || this.containsKey(storageId))
    throw new IllegalArgumentException("This id is not valid");
    if(storage == null)
    throw new IllegalArgumentException("The storage must not be null");
    this.put(storageId, storage);
  }

  /*
  * Retrieves the Storage from the table having the indicated storageID.
  *
  * @param1 storageID
  * key to find the storage object
  *
  * @returns
  * A Storage object with the given key, null otherwise.
  */
  public Storage getStorage(int storageID){
    return (Storage) this.get(storageID);
  }

  /*
  * removes the Storage from the table having the indicated storageID.
  *
  * @param1 storageID
  * key to find the storage object to remove
  *
  * @returns
  * The removed Storage object
  *
  * @throws IllegalArgumentException
  * if the storage does not have an object with this key
  */
  public Storage removeStorage(int storageID){
    if (!this.containsKey(storageID)) throw new IllegalArgumentException("No such box exists in the storage");
    return (Storage) this.remove(storageID);
  }

  /**
  * This method returns a String representation of the storage objects
  * with the given client in a table
  *
  * @returns
  * String representation of the storage items with the given client in a table
  */
  public String clientToString(String client){
    String returned = "Box#          Contents                       Owner";
    returned = returned + "\n----------------------------------------------------------------";
    Set<Integer> keys = this.keySet();
    for (Integer key: keys){
      Storage tempBox = this.getStorage(key);
      if (tempBox.getClient().equals(client)){
        String line = String.format("%-13d%-32s%-17s", tempBox.getId(), tempBox.getContents(), tempBox.getClient());
        returned = returned + "\n" + line;
      }
    }
    return returned;
  }

  /**
  * This method returns a String representation of the storage objects
  * in a table
  *
  * @returns
  * String representation of the storage items in a table
  */
  public String toString(){
    String returned = "Box#          Contents                       Owner";
    returned = returned + "\n----------------------------------------------------------------";
    Set<Integer> keys = this.keySet();
    for (Integer key: keys){
      Storage tempBox = this.getStorage(key);
      String line = String.format("%-13d%-32s%-17s", tempBox.getId(), tempBox.getContents(), tempBox.getClient());
      returned = returned + "\n" + line;
    }
    return returned;
  }
}
