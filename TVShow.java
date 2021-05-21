//--------------------------------------------------------------
//Assignment 4- part2
//Written by:
//Youngjae-Kim(40169282), Julian Colantuono(40109849)
//---------------------------------------------------------------
import java.util.Scanner;
/**
 * 
 * TVShow class implements Watchable interface
 *
 */
public class TVShow implements Watchable, Cloneable{

 String showID;
 String showName;
 double startTime;
 double endTime;
 /**
  * TVShow constructor with parameter which takes String, String, double, and double.
  */
 public TVShow(String showID, String showName, double startTime, double endTime) {
	 this.showID = showID;
	 this.showName = showName; 
	 this.startTime = startTime;
	 this.endTime = endTime;
 }
 /**
  * TVShow constructor with parameter which takes TVShow and String
  * @param TVShow object
  * @param String value
  */
public TVShow(TVShow a, String value) {
	showName = a.showName;
	startTime = a.startTime;
	endTime =a.endTime;
	showID = value;
}
public TVShow(TVShow a) {
	showName = a.showName;
	startTime = a.startTime;
	endTime =a.endTime;
	showID = a.showID;
}

public void setshowID(String showID) {
	this.showID = showID;
}
public void setshowName(String showName) {
	this.showName = showName;
}
public void setstartTime(double startTime) {
	this.startTime = startTime;
}
public void setendTime(double endTime) {
	this.endTime = endTime;
}
public String getshowID() {
	return showID;
}
public String getshowName() {
	return showName;
}
public double getstartTime() {
	return startTime;
}
public double getendTime() {
	return endTime;
}
/**
 * Clone method which returns the clone of TVShow object with the new ShowID.
 */
public TVShow clone(String newshowID) {
   return new TVShow(this, newshowID);
}
/**
 * Clone method which returns the clone of TVShow object
 */
public TVShow clone() {
	return new TVShow(this);
}
public String toString() {
	return "showID: " +showID + ", showName: "+ showName + " , startTime: "+ startTime + " , endTime: "+ endTime;
}
/**
 * equals method to compare an object with another Object.
 */
public boolean equals(Object obj) {
	if(obj == null)
		 return false;
	else if(getClass()!= obj.getClass())
		 return false;
	else {TVShow tv = (TVShow) obj;
	  return (showID.equals(tv.showID)&& (showName.equals(tv.showName))&&(startTime == tv.startTime)&&(endTime == tv.endTime));
		
	}
}
/**
 * method to check if the time is overlapped or same or different. 
 */
 public String isOnSameTime(TVShow s) {
	 if(s.startTime== startTime && s.endTime== endTime)
		 return "Same time";
	 else if((s.startTime== startTime&& s.endTime!= endTime)||(s.startTime!= startTime&& s.endTime == endTime))
		 return "Some Overlap";
	 else return "Different time";
 }
 
}
