/**
* An class meant to represent a database of near earth objects
* Extends ArrayList
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #7 for CSE 214, Spring 2023
*    Recitation 03
*
*    Apr 21th, 2023
*/
import big.data.*;
import java.util.*;
public class NeoDataBase extends ArrayList{
  public static final String API_KEY = "gjeaaZJdrCpIRs9lS7LArTJKJaqzqVcrVwIXq2wa";
  public static final String API_ROOT = "https://api.nasa.gov/neo/rest/v1/neo/browse?";


  /**
  * Constructor for the NeoDataBase
  *
  * Is the same as java.util.ArraYList
  */
  public NeoDataBase(){
    super();
  }

  /**
  * Method that builds the query URL given page number
  *
  * @param1 pageNumber
  * desired page number of data
  *
  * @returns
  * a string for the URL that will be used in addAll()
  */
  public String buildQueryURL(int pageNumber) throws IllegalArgumentException{
    if (pageNumber < 0 || pageNumber > 715) throw new IllegalArgumentException("Entered page number is not valid");
    return (API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY);
  }

  /**
  * Opens a connection to the data source
  * indicated by queryURL and adds
  * all NearEarthObjects found in the dataset.
  *
  * @param1 queryURL
  * URL used to locate the data source
  *
  * Preconditions:
  * queryURL is a non-null string representing a valid API request to the NASA NeoW service.
  *
  * Postconditions:
  * All NearEarthObject records returned have been added to the database,
  * or else a IllegalArgumentException has been thrown.
  *
  * @throws IllegalArgumentException
  * If queryURL is null or cound not be resolved by the server.
  */
  public void addAll(String queryURL) throws IllegalArgumentException{
    if(queryURL == null) throw new IllegalArgumentException("Entered url is not valid");
    DataSource ds = DataSource.connectJSON(queryURL);
    ds.load();

    NearEarthObject[] data = ds.fetchArray(
    "NearEarthObject",
    "near_earth_objects/neo_reference_id",
    "near_earth_objects/name",
    "near_earth_objects/absolute_magnitude_h",
    "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min",
    "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max",
    "near_earth_objects/is_potentially_hazardous_asteroid",
    "near_earth_objects/close_approach_data/epoch_date_close_approach",
    "near_earth_objects/close_approach_data/miss_distance/kilometers",
    "near_earth_objects/close_approach_data/orbiting_body"
    );
    for (int i = 0; i < data.length; i++){
      this.add(data[i]);
    }
  }

  /*
  * Sorts the database using the specified Comparator of NearEarthObjects.
  *
  * @param1 comp
  * comparator used to sort the near earth objects
  *
  * Preconditions:
  * comp is not null.
  *
  * Postconditions:
  * The database has been sorted based on
  * the order specified by the inidcated Comparator of NearEarthObjects.
  *
  * @throws IllegalArgumentException
  * if the comp is null
  */
  @Override
  public void sort(Comparator comp) throws IllegalArgumentException{
    if(comp == null) throw new IllegalArgumentException("Comparator must not be null");
    super.sort(comp);
  }

  /*
  * Displays the database in a neat, tabular form,
  * listing all member variables for each NearEarthObject.
  *
  * Preconditions:
  * This NeoDatabase is initialized and not null.
  *
  * Postconditions:
  * The table has been printed to the console but remains unchanged.
  */
  public void printTable(){
    if (this == null) throw new IllegalArgumentException("This database is not initialized");
    System.out.println("  ID   |           Name            | Mag. | Diameter | Danger | Close Date | Miss Dist | Orbits");
    System.out.println("================================================================================================");
    for (int i = 0; i < this.size(); i++){
      NearEarthObject temp = (NearEarthObject) this.get(i);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(temp.getClosestApproachDate());

      String date = String.format("%02d%1s%02d%1s%04d", calendar.get(Calendar.MONTH) + 1, "-", calendar.get(Calendar.DAY_OF_MONTH), "-", calendar.get(Calendar.YEAR));
      String revisedName = "";
      if (temp.getName().length() > 26)
      revisedName = temp.getName().substring(0,26);
      else revisedName = temp.getName();

      String output = String.format("%-7d%2s%-26s%2s%-2.1f%4s%-2.3f%5s%-5s%4s%-8s%3s%-8.0f%4s%-6s",
      temp.getReferenceID(), "  ", revisedName, "  ", temp.getAbsoluteMagnitude(), "    ",
      temp.getAverageDiameter(),"   ", temp.getIsDangerous(), "    ",
      date, "   ",temp.getMissDistance(), "    ",temp.getOrbitingBody()
      );
      System.out.print(output + "\n");
    }
  }



}
