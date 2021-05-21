//--------------------------------------------------------------
//Assignment 4- part2
//Written by:
//Youngjae-Kim(40169282), Julian Colantuono(40109849)
//---------------------------------------------------------------
import java.util.NoSuchElementException;
/**
 * Class which includes the inner class
 */
public class ShowList implements Cloneable {
  /**
   * Inner class
   *
   */
	public class ShowNode{
	    private TVShow show;
	    private ShowNode next;
	    /**
	     * default constructor
	     */
	     public ShowNode() {
		   show = null;
		   next = null;
	   }
	     /**
	      * Constructor with parameters.
	      * @param TVShow object
	      * @param ShowNode object
	      */
	     public ShowNode(TVShow show, ShowNode next) {
	        this.show = show;
	        this.next = next;
	     }
	     /**
	      * Copy constructor which takes the ShowNode object.
	      */
	     public ShowNode(ShowNode a) {
	    	 show = new TVShow(a.show.clone());   // this is the way of making deep copy. 
	    	 next = new ShowNode(show, a.next.clone());
	    	 }
	     /**
	      * clone method that returns cloned ShowNode object.
	      */
	     public ShowNode clone() {
	    	 return new ShowNode(this);
	     }
	    private void setshow (TVShow show) {
	    	this.show = show;
	    }
	    private void setnext (ShowNode next) {
	    	this.next = next;
	    }
	    public TVShow getshow() {
	    	return show.clone();
	    }
	    public ShowNode getnext() {
	    	return next;
	    }
	  
	 }
	/**
	 * Instance variable
	 */
	private ShowNode head;
	/**
	 * Instance variable
	 */
	private int size;
	
 /**
  * default constructor of ShowList class.
  */
	public ShowList() {
		head= null;
		size= 0;
	}
	/**
	 * constructor which takes one parameter.
	 * @param ShowList object
	 */
	public ShowList(ShowList obj) {
	   if(obj==null)
		   throw new NullPointerException();
	   if(obj.head== null)
		   head = null;
	   else 
		   head = copyOf(obj.head);
		this.size = obj.size;
	}
	/**
	 * copyOf method which copies all the nodes inside the list.
	 * @param otherhead 
	 * @return newHead
	 */
	private ShowNode copyOf(ShowNode otherhead) {
		ShowNode position = otherhead;
		ShowNode newHead;
		ShowNode end = null;
		newHead = new ShowNode(position.show.clone(),null);  //creates the first node
		end =newHead;
		position = position.next;
		
		while(position!=null)
		{
			end.next= new ShowNode(position.show.clone(),null);
			end= end.next;
			position = position.next;
		}
		return newHead;
	}
	/**
	 * size method which returns the size of the list.
	 */
	public int size() {
		int count =0;
		ShowNode position = head;
		 while(position!= null) {
			 count++;
			 position = position.next;
		 }
		 return count;
	}
	/**
	 * method to add to the list from the start.
	 * @param TVShow Object
	 */
	public void addToStart(TVShow obj) {
		head= new ShowNode(obj,head);
	} 
	/**
	 * method to insert the object in to the list at the certain index.
	 * @param TVShow object
	 * @param int index
	 */
	public void insertAtIndex(TVShow obj, int index) {
	  try {
		  ShowNode fakeHead = null;
		  fakeHead= head;
		  ShowNode previous = null;
          
		  
		  if(index<0 || index>size()-1)
			  throw new NoSuchElementException();
		  else {
			  for(int i=0; i< index;i++)
					 { previous= fakeHead;
				       fakeHead = fakeHead.next; 
					  } 

			   fakeHead = new ShowNode(obj, fakeHead);
			   previous.next = fakeHead;

		       }
	
		 }
	  catch(NoSuchElementException e) {
		  System.out.println("Oh! no such element. System down.");
		  System.exit(0);
	    }
	}
	/**
	 * method to delete the value in the certain index
	 * @param int index
	 */
	public void deleteFromIndex(int index) {
		ShowNode position = head;
		try { 
		     if(index<0 || index>size()-1)
				  throw new NoSuchElementException();
		     else{for(int i=0; i< index-1;i++)
			          position =position.next;
		          position.next = position.next.next;}
		     }
		catch(NoSuchElementException n) {
			 System.out.println("Oh! no such element. System down.");
			 System.exit(0);
		}
		
	}
	/**
	 * method to return boolean to check if it deleted the first value
	 * @return boolean 
	 */
	public boolean deleteFromStart() {
		if(head!=null)
		    {head= head.next;
		    return true;}
		else return false;
	
	}
	/**
	 * method to replace some value to another value at the certain index.
	 * @param TVShow object
	 * @param int index 
	 */
	public void replaceAtIndex(TVShow obj, int index) {
		try { 
		     if(index<0 || index>size()-1)
				  throw new NoSuchElementException();
		     else{deleteFromIndex(index);
		          insertAtIndex(obj, index);}
		     }
		catch(NoSuchElementException n) {
			 System.out.println("Oh! no such element. System down.");
			 System.exit(0);
		}
	}
	/**
	 * method to find a node in the list with some string value which is showID.
	 * @param String target
	 * @return ShowNode
	 */
	public ShowNode find(String target) {
		ShowNode position = head; 
		String itemAtPosition;
		while(position!=null)
		{
		  itemAtPosition= position.show.showID;
		  if(itemAtPosition.equals(target))
			 {
			  return position;}
		  position= position.next;
		}
		return null;
	}
	/**
	 * method to find a node in the list with some string value which is showName. 
	 * @param String target
	 * @return ShowNode
	 */
	public ShowNode findwithshowname(String target) {
		ShowNode position = head; 
		String itemAtPosition;
		while(position!=null)
		{
		  itemAtPosition= position.show.showName;
		  if(itemAtPosition.equals(target))
			 {
			  return position;}
		  position= position.next;
		}
		return null;
	}
	/**
	 * method to find a node in the list with ShowID and show iteration.
	 * @param String target
	 * @return ShowNode
	 */
	public ShowNode findwithIteration(String target) {
		int countIteration=0;
		ShowNode position = head; 
		String itemAtPosition;
		while(position!=null)
		{ countIteration++;
		  itemAtPosition= position.show.showID;
		  if(itemAtPosition.equals(target))
			 {System.out.println("We found this at Index "+ (countIteration-1));
			  return position;}
		  position= position.next;
		}
		return null;
	}
	/**
	 * method to check if the showlist contains a certain showID.
	 * @param String showID
	 * @return boolean
	 */
	public boolean contains(String showID) {
	  return (find(showID)!=null);
	}
	/**
	 * method to check if the two lists are the same
	 * @param ShowList obj
	 * @return boolean 
	 */
	public boolean equals(ShowList obj) {
	  if(obj == null)
		 return false;
	 else return (head.equals(obj.head)&&size()==obj.size());
	 
	} 
	/**
	 * method to print all the values in the list.
	 */
	public void outputList() {
		ShowNode position = head; 
		while(position!= null)
		{
			System.out.println(position.getshow().showID + " "+ position.getshow().showName+ 
					" "+ position.getshow().startTime + " "+ position.getshow().endTime);
			position = position.next;
			
		}
	}
	
}
