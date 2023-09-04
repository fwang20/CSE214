/**
 * The node class meant to be used in a Linked List of Slides
 *
 * @author
 *    Frank Wang., USB ID 115037306
 *
 *    Homework #2 for CSE 214, Spring 2023
 *    Recitation 03
 *
 *    Feb 14nd, 2023
 */
public class SlideListNode{

  private Slide data;

  private SlideListNode next;

  private SlideListNode prev;

  /**
  * This is the constructor for a SlideListNode, which is a node in the SLideList
  * It creates a SlideList with a given slide.
  *
  * @param1 initData
  * SLide to store in the SlideListNode
  */
  public SlideListNode(Slide initData){
    if (initData == null) throw new IllegalArgumentException("Initial data must not be null");
    data = initData;
    next = null;
    prev = null;
  }

  /**
  * This method returns a reference to the Slide of this SlideListNode.
  *
  * @return
  * A reference to the Slide that this ListNode has a reference to
  *
  */
  public Slide getData(){
    return data;
  }

  /**
  * This mutator method changes the Slide that this node references to
  *
  * @param1 newData
  * Desired new Slide
  *
  * Precondition:
  * newData is not null
  *
  * Postcondition:
  * This SlideListNode now has a new Slide that it references to
  *
  * @throws IllegalArgumentException
  * if newData is null
  */
  public void setData(Slide newData){
    if (newData == null) throw new IllegalArgumentException("New slide data must not be null");
    this.data = newData;
  }

  /**
  * This method returns a reference to the next link of this SlideListNode
  *
  * @return
  * A reference to the next Slide
  */
  public SlideListNode getNext(){
    return next;
  }

  /**
  * This mutator method changes the next SlideListNode that this node references to
  *
  * @param1 newData
  * Desired new SlideListNode that will come after this node.
  *
  * Postcondition:
  * This SlideListNode now has a new SlideListNode coming after it.
  */
  public void setNext(SlideListNode newNext){
    next = newNext;
  }

  /**
  * This method returns a reference to the previous link of this SlideListNode
  *
  * @return
  * A reference to the previous Slide
  */
  public SlideListNode getPrev(){
    return prev;
  }

  /**
  * This mutator method changes the previous SlideListNode that this node references to
  *
  * @param1 newData
  * Desired new SlideListNode that will come before this node.
  *
  * Postcondition:
  * This SlideListNode now has a new SlideListNode before it.
  */
  public void setPrev(SlideListNode newPrev){
    prev = newPrev;
  }
}
