/**
* An abstract data type meant to be a representation
* of a slide (in a slideshow)
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #2 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 14nd, 2023
*/
public class Slide {

  public static final int MAX_BULLETS = 5;

  private String title;

  private String[] bullets;

  private double duration;

  /**
  * This is the constructor for a Slide.
  * It creates a Slide object with variables for a title, an empty array of bullets of size MAX_BULLETS, and duration of the slides
  */
  public Slide() {
    title = null;
    bullets = new String[MAX_BULLETS];
    duration = 0.0;
  }

  /**
  * This method returns the title of the Slide.
  *
  * @return
  * A string that is the title of the slide
  *
  */
  public String getTitle() {
    return title;
  }

  /**
  * This mutator method changes the title of the SLide
  *
  * @param1 newTitle
  * Desired title of the Slide
  *
  * Precondition:
  * newTitle is not null
  *
  * Postcondition:
  * This Slide now has title newTitle
  *
  * @throws IllegalArgumentException
  * if newTitle is null
  */
  public void setTitle(String newTitle) throws IllegalArgumentException {
    if (newTitle == null) throw new IllegalArgumentException("Title cannot be null");
    title = newTitle;
  }

  /**
  * This method returns the duration of the Slide
  *
  * @return
  * A double that represents the duration of the slide
  *
  */
  public double getDuration() {
    return duration;
  }

  /**
  * This mutator method changes the duration of the SLide
  *
  * @param1 newDuration
  * Desired title of the Slide
  *
  * Precondition:
  * newDUration is not null
  *
  * Postcondition:
  * This Slide now has duration newDuration
  *
  * @throws IllegalArgumentException
  * if newDuration is null
  */
  public void setDuration(double newDuration) throws IllegalArgumentException{
    if (newDuration <= 0) throw new IllegalArgumentException("Invalid duration: Entered duration has to be greater than 0");
    duration = newDuration;
  }

  /**
  * This method returns the number of the bullets in the Slide.
  *
  * @return
  * An int less than MAX_BULLETS of how many bullets are in the Slide.
  */
  public int getNumBullets(){
    int counter = 0;
    if (bullets != null){
      for (String b : bullets){
        if (b != null) counter++;
      }
    }
    return counter;
  }

  /**
  * This method returns the String interpretation of a single bullet given an index
  *
  * @param1 i
  * position of the desired bullet
  *
  * @return
  * A String that is the bullet of the SLide in the given position.
  *
  * @throws IllegalArgumentException
  * if i is greater than MAX_BULLETS
  *
  */
  public String getBullet(int i) throws IllegalArgumentException{
    if (i > MAX_BULLETS) throw new IllegalArgumentException("Given index is not in valid range");
    return bullets[i];
  }

  /**
  * This mutator method inserts a bullet into the slide
  * NOTE: does not replace the bullet currently in the position given,
  * rather it shifts every other element up one.
  *
  * @param1 bullet
  * Desired bullet in the Slide
  *
  * @param2 i
  * desired index to put the new bullet
  *
  * Precondition:
  * Slide object is instantiated
  *
  * Postcondition:
  * This Slide now a new bullet in position i
  *
  * @throws IllegalArgumentException
  * if the given i is less than 1 or greater than the bullet in the current highest index
  */
  public void setBullet(String bullet, int i) throws IllegalArgumentException{
    if (i > getNumBullets() + 1 || i < 1) throw new IllegalArgumentException("Entered index is not valid for setting a bullet");
    String[] copy = new String[MAX_BULLETS];
    if (bullets != null){
      System.arraycopy(bullets,0,copy, 0, MAX_BULLETS);
      this.bullets[i - 1] = bullet;
      for (int n = i; n < MAX_BULLETS; n++){
        bullets[n] = copy[n - 1];
      }
    }
    else bullets[i - 1] = bullet;
  }

  /**
  * This method returns the String representation of the bullets
  * within the slide (looks like a slideshow)
  *
  * Precondition:
  * THe slide object is instantiated
  *
  * @return
  * Returns the string representation of the Player object.
  *
  * @throws IllegalArgumentException
  * if the slide is null
  */
  public String bulletsToString() throws IllegalArgumentException{
    if (this == null){
      throw new IllegalArgumentException("This slideshow is empty");
    }
    else {
      String table = "==============================================" + "\n  " + title;
      table = table + ("\n==============================================");
      for (int i = 0; i< MAX_BULLETS; i++){
        if (bullets[i] != null){
          table = table + "\n" + (i + 1) + ". " + bullets[i];
        }
      }
      table = table + "\n==============================================";
      return table;
    }
  }

  /**
  * This mutator method removes a bullet from the slide
  *
  * @param1 index
  * index where the bullet is being removed
  *
  * Precondition:
  * index must be valid
  *
  * Postcondition:
  * The bullet in position i is removed from the Slide and bullets in higher positions are shifted downwards
  *
  * @throws IllegalArgumentException
  * if index is less than 1 or greater than the number of bullets in the Slide.
  */
  public void removeBullet(int index) throws IllegalArgumentException{
    if (index > getNumBullets() + 1 || index < 1) throw new IllegalArgumentException("Entered index is not valid for setting a bullet");
    String[] copy = new String[MAX_BULLETS];
    int originalNumBullets = getNumBullets();
    if (bullets != null){
      System.arraycopy(bullets, 0, copy, 0, MAX_BULLETS);
      for (int i = index; i < MAX_BULLETS; i++){
        bullets[i - 1] = copy[i];
      }
      this.bullets[originalNumBullets - 1] = null;
    }
  }

  /**
  * This method returns the String representation
  * title, duration, and number of bullets in a table.
  *
  * @return
  * Returns the string representation of the Slide object
  */
  public String toString(){
    return String.format("%-14s%-11.1f%-10d", title, duration, this.getNumBullets());
  }
}
