//--------------------------------------------------------------
//Assignment 4- part2
//Written by:
//Youngjae-Kim(40169282), Julian Colantuono(40109849)
//---------------------------------------------------------------
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * This is ProcessWishlist Class which serves as a driver class. 
 *
 */
public class ProcessWishlist {
  
	public static void main(String[] args) {
		ShowList list1 = new ShowList();
		ShowList list2 = new ShowList();   //call two ShowList objects
       
		Scanner reader = null; 
		Scanner reader2 =null;            //call the scanner to read the file
		try {
			reader = new Scanner(new FileInputStream("TVGuide.txt"));  
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String [] info = line.split(" ");
				String ShowID= info[0];
				String Showname =info[1];
				line = reader.nextLine();
				String [] info2 = line.split(" ");
				double Start = Double.parseDouble(info2[1]);
				line = reader.nextLine();
				String [] info3 = line.split(" ");
				double End = Double.parseDouble(info3[1]);   //reading each line of the file and split it into different 
				 //values based on their types.
				
				ShowList.ShowNode temp = list1.findwithshowname(Showname);   
                if(temp==null)
                	list1.addToStart(new TVShow(ShowID, Showname, Start, End)); 
                else if(temp!=null)
                	if(!(temp.getshow().startTime== Start &&temp.getshow().endTime== End))
                		list1.addToStart(new TVShow(ShowID, Showname, Start, End)); 
				reader.nextLine();    //put all the TVShow objects into the list except the duplicated one.
				}
			
			//ask the user to input new information in the list.
			System.out.println("Do you want to input new information?(Press yes or no): ");
			Scanner Jay = new Scanner(System.in);
			String answer = Jay.next();
			if(answer.equalsIgnoreCase("yes"))
				{System.out.println("Input the index, showID, showname, starttime, endtime in order: ");
				 int newindex = Integer.parseInt(Jay.next());
				 String newShowId = Jay.next();
				 String newShowname = Jay.next();
				 double newStarttime = Double.parseDouble(Jay.next());
				 double newendtime = Double.parseDouble(Jay.next());
				 list1.insertAtIndex(new TVShow(newShowId, newShowname, newStarttime, newendtime), newindex );
				 System.out.println("Here is the new list.");  //print the new list after adding.
				 list1.outputList();
				 System.out.println("");
				 }
			//ask the user to delete some information in the list.
			System.out.println("Do you want to delete some information?(Press yes or no): ");
			Scanner Julian = new Scanner(System.in);
			String answer1 = Julian.next();
			if(answer1.equalsIgnoreCase("yes"))
				{System.out.println("Input the index: ");
				 int newindex1 = Integer.parseInt(Jay.next());
				 list1.deleteFromIndex(newindex1);
				 System.out.println("Here is the new list.");
				 list1.outputList(); //print the new list after adding
				 System.out.println("");
				 }
			
			
			//ask the user to input the name of the file which includes the wish list.
			Scanner ask = new Scanner(System.in);
			System.out.println("Please input the file that contains your wishlist: ");
			String filename = ask.nextLine();
			reader2 = new Scanner(new FileInputStream(filename));
			ArrayList <String> userwant = new ArrayList<String>();
			int indexofwatching;
			int indexofWishlist;    //in the file, there are two words which are Watching and Wishlist, so we divide ShowIDs into two sections.
			String sentence= reader2.nextLine(); 
			if(sentence !=null)
			do{
				userwant.add(sentence);
				sentence = reader2.nextLine();
			}while(reader2.hasNextLine());       //first, we put all the values into the arraylist.              
			
			indexofwatching = userwant.indexOf("Watching");
            indexofWishlist = userwant.indexOf("Wishlist"); // find the index of Watching and WishList.
            int countsizetocompare= 0;
            int dividecount =0;
            ArrayList<String> tocompare = new ArrayList<String>(); //Arraylist to compare time
			for(int i=indexofwatching+1; i<indexofWishlist;i++)
				  {dividecount++;                       //it counts the number of showIDs that user is watching.
				for(int j= indexofWishlist+1; j<userwant.size(); j++)
				{   countsizetocompare++;               //it counts the number of showIDs that user wants to watch but it is multiplied.
					TVShow watch = list1.find(userwant.get(i)).getshow();
					TVShow wish = list1.find(userwant.get(j)).getshow(); //to compare the times
					String sign = watch.isOnSameTime(wish);  //to check the sign that isOnSameTime method gives.
					if(sign.equals("Same time"))
					     tocompare.add(("User can't watch show "+ wish.showID + " as he//she will begin another show at the same time."));
					else if(sign.equals("Some Overlap"))
						 tocompare.add(("User can't watch show "+ wish.showID + " as he//she is not finished with a show he//she is watching."));
					else 
						tocompare.add(("User can watch show "+ wish.showID + " as he//she is not watching anything else during that time."));
				}}
		    for(int i= 0; i< countsizetocompare/dividecount; i++) //we divide countsizetocompare with dividecount because it adds the complete results multiple times to the file.  
		    	{if(tocompare.get(i).substring(5,9).equals(tocompare.get(i+countsizetocompare/dividecount).substring(5,9)))
		    		System.out.println(tocompare.get(i));  //we compared with can and can'.
		    	else if(tocompare.get(i).substring(5,9)!=tocompare.get(i+countsizetocompare/dividecount).substring(5,9))
		    		{if(tocompare.get(i).substring(5,9).equals("can'")) //if one of them has can' then we print this.
		    			System.out.println(tocompare.get(i));
		    		else System.out.println(tocompare.get(i+countsizetocompare/dividecount));}}
		    
		    //make user able to find the certain information of the TVshow.
			System.out.println("");
			Scanner askuser = new Scanner(System.in);
			System.out.println("Please enter the showIDs that you want to search(Write \'no\' to stop input): ");
			ArrayList <String> usershowID = new ArrayList<String>();
			do{usershowID.add(askuser.next());}while(!askuser.next().equals("no"));
			int count  =0; 
			for(int i= 0; i< usershowID.size() ; i++)
			    {   count ++;
				    ShowList.ShowNode obj= list1.findwithIteration(usershowID.get(i));
				    if(obj!=null)
				       {System.out.println("--->Good news! we found it!(Iteration "+count + "):   "+obj.getshow().toString());
				        System.out.println("");}
				       
				    else
				    	System.out.println("--->Oh! Too bad, we cannot find your program.(Iteration "+ count + ").");}
				System.out.println("");
		}
		catch(FileNotFoundException f) {
			System.out.println("File is not found.");
		}
		catch(NoSuchElementException n) {
			System.out.println("There is no such element.");
		}
		catch(NullPointerException e) {
			System.out.println("The object that you are trying to access is null.");
		}
		
		
	  //to check tvshow clone
	  TVShow example1 = new TVShow("JAY1004","Jay's_Life",22.00, 24.00);
	  TVShow example2 = new TVShow("Julian007", "Julian's_Life", 17.00, 19.00);
	  TVShow clonedOne = example1.clone("Jay007");
	  System.out.println("Cloned one of>> "+ example1+" >>is>> "+ ":  "+ clonedOne);
	  System.out.println("");
	  
	  //to check if it deleted the first node.
	  System.out.println("List2 which is a clone of list1 : ");
	  list2= new ShowList(list1);
	  list2.outputList();
	  System.out.println("");
	  System.out.println("If we delete the first node of list2, list should be like: ");
	  list2.deleteFromStart();
	  list2.outputList();
	  System.out.println("");
	 
	  //to check replaceatindex
	  int indexx= 3;
	  list2.replaceAtIndex(example2, indexx);
	  System.out.println("If we replace index "+ indexx + " to "+ example2 + " in list2");
	  list2.outputList();
	  System.out.println("");                       
	  
	  //to check showlist equals method
	  boolean equal = false; 
	  if(list1.equals(list2))
		  equal = true;
	  System.out.println("list1 and list2 are equal: "+ equal);
	}

}
