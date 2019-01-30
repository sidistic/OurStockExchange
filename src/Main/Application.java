package Main;
import java.io.*;
import java.util.*;

//This is the class which calls the main function

public class Application {
	
	public static void main(String[] args) {
		
		PendingOrders Remaining = new PendingOrders(); //Object to handle orders
		PreviousTransactions Trans = new PreviousTransactions(); //Object to keep track of trades
		
		int ch = -1; //Choice: we use this variable to implement a menu
		String temp; //TO take input from BufferedReader 
		while(ch!=4)
		{
			System.out.println("1: New Transaction\n"
					+ "2: Show All Finished Transactions\n"
					+ "3: Show pending Orders\n"
					+ "4: Exit\n"
					+ "Enter your Choice :");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				temp = reader.readLine();
				ch = Integer.parseInt(temp);
				switch(ch)
				{
				case 1:
					
					/*Here we take input for the new Order of the client*/
					System.out.print("Enter Time: ");
					temp = reader.readLine();
					int time = Integer.parseInt(temp);
					System.out.print("Enter 1 for Buy or 2 for Sell: ");
					temp = reader.readLine();
					int bors = Integer.parseInt(temp);
					String type;
					if(bors == 1)
						type="buy";
					else
						type="sell";
					System.out.print("Enter Client: ");
					String from = reader.readLine();
					System.out.print("Enter name of Stock: ");
					String stock = reader.readLine();
					System.out.print("Enter Quantity: ");
					temp = reader.readLine();
					int qnt = Integer.parseInt(temp);
					System.out.print("Enter Price: ");
					temp = reader.readLine();
					int price = Integer.parseInt(temp);
					
					
					/* Here we call handle the order*/
					Order newOrder= new Order(time,type,from,stock,qnt,price);//Creates new Order
					Remaining.setIncomingOrder(newOrder); //Sends the Order to the Pending Orders to make transaction
					Trans.addToRecords(Remaining.makeTransaction()); //Adds new Trades made by the Remaining orders to the trades table
					break;
				case 2:
					Trans.printRecords();
					break;
				case 3:
					Remaining.printPending();
					break;
				}
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
}
