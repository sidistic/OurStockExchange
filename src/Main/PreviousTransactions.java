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
		System.out.println("Time\tBuyer\tStock\tSeller\tQnt\tPrice\tTotalMoney");
		for(int i=0 ; i<Records.size();i++)
		{
			Trade temp = Records.get(i);
			System.out.println(temp.time +"\t"+temp.buyer
					+"\t" + temp.stock + "\t" + temp.seller
					+"\t" + temp.qnt + "\t" + temp.price
					+"\t" + temp.qnt*temp.price);
		}
	}
}
