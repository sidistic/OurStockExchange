package Main;
import java.util.*;

/* This class holds all the Previous Trades made by the application.*/

public class PreviousTransactions {
	ArrayList<Trade> Records = new ArrayList<Trade>();
	
	public void addToRecords(ArrayList<Trade> newTransactions)// To add new records into the list
	{
		Records.addAll(newTransactions);
	}
	
	
	//This function prints the records in tabular format
	public void printRecords()
	{
		System.out.println("B_Time\tS_time\tBuyer\tSeller\tStock\tQnt\tPrice");
		for(int i=0 ; i<Records.size();i++)
		{
			Trade temp = Records.get(i);
			System.out.println(temp.b_time +"\t"+ temp.s_time +"\t" +temp.buyer
					+"\t" + temp.seller + "\t" + temp.stock
					+"\t" + temp.qnt + "\t" + temp.price);
		}
	}
}
