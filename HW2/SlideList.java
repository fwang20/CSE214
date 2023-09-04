/**
* A class meant to represent a slideshow as a linked list
*
* @author
*    Frank Wang., USB ID 115037306
*
*    Homework #2 for CSE 214, Spring 2023
*    Recitation 03
*
*    Feb 14nd, 2023
*/
public class SlideList{

  private SlideListNode head;

  private SlideListNode tail;

  private SlideListNode cursor;

  private int sizeOfList;

  /**
  * This getter method returns the number of nodes in the SlideList
  *
  * @return the number of nodes in the SlideList.
  */
  public SlideList(){
    head = null;
    tail = null;
    cursor = null;
    sizeOfList = 0;
  }

  /**
  * This getter method returns the number of nodes in the SlideList
  *
  * @return the number of nodes in the SlideList.
  */
  public int size(){
    return sizeOfList;
  }

  /**
  * This getter method returns the total duration of all the slides in the SlideList
  *
  * @return the total duration of the SLideList in a double
  */
  public double duration(){
    SlideListNode nodePtr = head;
    double timeSum = 0.0;
    while (nodePtr != null) {
      timeSum += nodePtr.getData().getDuration();
      nodePtr = nodePtr.getNext();
    }
    return timeSum;
  }

  /**
  * This getter method returns the total number of bullets of all the slides in the SlideList
  *
  * @return the total number of bullets of the SLideList in an int
  */
  public int numBullets(){
    SlideListNode nodePtr = head;
    int sum = 0;
    while (nodePtr != null) {
      sum += nodePtr.getData().getNumBullets();
      nodePtr = nodePtr.getNext();
    }
    return sum;
  }

  /**
  * The reference of the Slide wrapped by the SlideListNode currently
  * referenced by cursor. If the cursor is null, then this method will return null as well
  *
  * @return the total duration of the SLideList in a double
  */
  public Slide getCursorSlide(){
    if (cursor == null) return null;
    else return cursor.getData();
  }

  /**
  * Returns the cursor to the start of the list.
  *
  * Postconditions:
  * If head is not null, the cursor now references the first SlideListNode in this list.
  * If head is null, the cursor is set to null as well (A.K.A empty set)
  */
  public void resetCursorToHead(){
    if (head == null) cursor = null;
    else cursor = head;
  }

  /**
  * Brings the cursor forward one slide
  *
  * Postconditions:
  * The cursor now points to the (previously) next SlideListNode
  *
  * @throws EndOfListException
  * if the cursor is already at the end of the list.
  */
  public void cursorForward() throws EndOfListException{
    if (cursor == tail || tail == null) throw new EndOfListException("End of list cannot move forward");
    else cursor = cursor.getNext();
  }

  /**
  * Moves the cursor to select the previous SlideListNode in the list.
  *
  * Postconditions:
  * The cursor now points to the previous SlideListNode
  *
  * @throws EndOfListException
  * if the cursor is already at the beginning of the list
  */
  public void cursorBackward() throws EndOfListException{
    if (cursor == head || head == null) throw new EndOfListException("End of list cannot move backward");
    else cursor = cursor.getPrev();
  }

  /**
  * Inserts the indicated Slide before the cursor.
  *
  * @param1 newSlide
  * desired NewSlide to be inserted before the cursor
  *
  * Precondition:
  * newSlide is not null
  *
  * Postconditions:
  * newSlide has been wrapped in a new SlideListNode object
  * If cursor was previously not null, the newly created SlideListNode
  * has been inserted into the list before the cursor.
  * If cursor was previously null, the newly created SlideListNode
  * has been set as the new head of the list (as well as the tail).
  * The cursor now references the newly created SlideListNode.
  *
  * @throws IllegalArgumentException
  * if newSlide is null
  */
  public void insertBeforeCursor (Slide newSlide) throws IllegalArgumentException{
    if (newSlide == null) throw new IllegalArgumentException("Inserted slide must not be null");
    SlideListNode newSlideNode = new SlideListNode(newSlide);
    sizeOfList++;
    if (cursor == null) {
      head = newSlideNode;
      tail = newSlideNode;
      cursor = newSlideNode;
    }
    else if(cursor == head){
      newSlideNode.setNext(cursor);
      cursor.setPrev(newSlideNode);
      head = newSlideNode;
      cursor = head;
      if (sizeOfList == 2)
      tail = cursor.getNext();
    }
    else{
      newSlideNode.setNext(cursor);
      newSlideNode.setPrev(cursor.getPrev());
      cursor.getPrev().setNext(newSlideNode);
      cursor.setPrev(newSlideNode);
      cursor = newSlideNode;
    }
  }

  /**
  * Inserts the indicated Slide after the tail.
  *
  * @param1 newSlide
  * desired NewSlide to be inserted before the cursor
  *
  * Precondition:
  * newSlide is not null
  *
  * Postconditions:
  * newSlide has been wrapped in a new SlideListNode object
  * If tail was previously not null, the newly created SlideListNode
  * has been inserted into the list after the tail.
  * If tail was previously null, the newly created SlideListNode
  * has been set as the new head of the list (as well as the tail).
  * The tail now references the newly created SlideListNode
  *
  * @throws IllegalArgumentException
  * if newSlide is null
  */
  public void appendToTail(Slide newSlide) throws IllegalArgumentException{
    if (newSlide == null) throw new IllegalArgumentException("Inserted slide must not be null");
    SlideListNode newSlideNode = new SlideListNode(newSlide);
    sizeOfList++;
    if (tail == null){
      head = newSlideNode;
      tail = newSlideNode;
      cursor = newSlideNode;
      sizeOfList++;
    }
    else {
      tail.setNext(newSlideNode);
      newSlideNode.setPrev(tail);
      tail = newSlideNode;
    }
  }

  /**
  * Removes the SlideListNode referenced by cursor and returns the Slide inside.
  *
  * Precondition:
  * cursor is not null
  *
  * Postconditions:
  * The SlideListNode referenced by cursor has been removed from the list.
  * All other SlideListNodes in the list exist in the same order as before.
  * The cursor now references the previous SlideListNode (or the head,
  * if the cursor previously referenced the head of the list).
  *
  * @returns
  * the reference to the Slide that was removed with the SlideNode
  *
  * @throws EndOfListException
  * if cursor is null
  */
  public Slide removeCursor() throws EndOfListException{
    if (cursor == null) throw new EndOfListException("Empty slideshow cannot be removed");
    Slide removedSlide = cursor.getData();
    sizeOfList--;
    if (sizeOfList == 1){
      head = null;
      tail = null;
      cursor = null;
    }
    else if (cursor == tail){
      tail = cursor.getPrev();
      cursor.getPrev().setNext(null);
      cursor = tail;
    }
    else if (cursor == head){
      head = cursor.getNext();
      cursor.getNext().setPrev(null);
      cursor = head;
    }
    else {
      cursor.getPrev().setNext(cursor.getNext());
      cursor.getNext().setPrev(cursor.getPrev());
      cursor = cursor.getPrev();
    }
    return removedSlide;
  }

  /**
 * This method returns the String representation of
 * the SlideList, with titles, duration, and number of bullets.
 *
 * @return
 * Returns the string representation of the SlideList object
 */
  public String toString(){
    SlideListNode nodePtr = head;
    int counter = 1;
    String table = "==============================================" + "\n  Slide    Title         Duration   Bullets   \n";
    table = table + ("----------------------------------------------");
    while (nodePtr != null) {
      if (nodePtr != cursor){
        table = table + String.format("\n%4d%s%s", counter, "       ", nodePtr.getData().toString());
        counter++;
        nodePtr = nodePtr.getNext();
      }
      else {
        table = table + String.format("\n%s%2d%s%s","->" ,counter, "       ", nodePtr.getData().toString());
        counter++;
        nodePtr = nodePtr.getNext();
      }
    }
    String durationRounded = String.format("%.1f", duration());
    table = table + "\n==============================================";
    table = table + "\nTotal: " + (counter - 1) + " slide(s), " + durationRounded + " minute(s), " + numBullets() + " bullet(s)";
    table = table + "\n==============================================";
    return table;
  }
}
