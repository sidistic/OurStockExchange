package Main;
import java.util.*;

/*This class holds all the pending orders in the system and makes transactions*/

public class PendingOrders {
	ArrayList<Order> Pending = new ArrayList<Order>(); //List of all the pending orders
	Order IncomingOrder; //Variable to hold the new order sent in by the client
	boolean receivedOrder = false; //to see whether we have received the Order
	
	/*SetIncomingOrder sets up the Incoming order and makes appropriate changes to other variable to indicate this*/
	public void setIncomingOrder(Order NEW)
	{
		IncomingOrder = NEW;
		receivedOrder = true;
	}
	
	
	/* getPendingOrderForTrans returns us the index of the pending order in the list which can
	 * be used for a transaction.
	 * If no such order is found it returns -1. It does this by iteratively checking the list
	 * and checking against each of the pending orders till one which satisfies the condition is
	 * reached. */
	public int getPendingOrdersForTrans()
	{
		if(!receivedOrder)//if no order was received then the function stops here
		{
			return -1;
		}
		else
		{
			for(int i=0; i<Pending.size();i++)
			{
				if(Pending.get(i).checkPossibleTrade(IncomingOrder))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	
	/* makeTransaction makes the changes in the pending orders list i.e add new if quantity still pending
	 * or remove finished order. It returns a list of Trades which were made by this function. */
	public ArrayList<Trade> makeTransaction()
	{
		ArrayList<Trade> TradesMade = new ArrayList<Trade>(); //New trades.
		if(receivedOrder)
		{
			/*This is a loop so that even though one trade is made
			 * it goes through the entire list to check if other trades
			 * can be made too. the cond is qnt>0 because if qnt=0 then
			 * we cannnot make any trade */
			while(IncomingOrder.qnt >0)
			{
				int ord_no = this.getPendingOrdersForTrans(); //This will give the index of a viable order that this pairs with
				if(ord_no!=-1)
				{
					Order pendingOrder = Pending.get(ord_no);
					if(pendingOrder.qnt <= IncomingOrder.qnt)
					{
						IncomingOrder.qnt = IncomingOrder.qnt - pendingOrder.qnt;
						Trade new_trans = new Trade();
						new_trans.qnt = pendingOrder.qnt;
						new_trans.stock = IncomingOrder.stock;
						if(IncomingOrder.type.equals("sell"))
						{
							new_trans.s_time = IncomingOrder.time;
							new_trans.b_time = pendingOrder.time;
							new_trans.seller=IncomingOrder.from;
							new_trans.buyer =pendingOrder.from;
							new_trans.price = IncomingOrder.price;
						}
						if(IncomingOrder.type.equals("buy"))
						{
							new_trans.b_time = IncomingOrder.time;
							new_trans.s_time = pendingOrder.time;
							new_trans.seller=pendingOrder.from;
							new_trans.buyer =IncomingOrder.from;
							new_trans.price =pendingOrder.price;
						}
						TradesMade.add(new_trans); //Adds into the new transaction list which will later be returned
						Pending.remove(pendingOrder); //Since the quantity of the order will now be 0
					}
					else
					{
						Trade new_trans = new Trade();
						new_trans.qnt = IncomingOrder.qnt;
						new_trans.stock = IncomingOrder.stock;
						if(IncomingOrder.type.equals("sell"))
						{
							new_trans.s_time = IncomingOrder.time;
							new_trans.b_time = pendingOrder.time;
							new_trans.seller=IncomingOrder.from;
							new_trans.buyer =pendingOrder.from;
							new_trans.price = IncomingOrder.price;
						}
						if(IncomingOrder.type.equals("buy"))
						{
							new_trans.b_time = IncomingOrder.time;
							new_trans.s_time = pendingOrder.time;
							new_trans.seller=pendingOrder.from;
							new_trans.buyer =IncomingOrder.from;
							new_trans.price =pendingOrder.price;
						}
						TradesMade.add(new_trans);
						pendingOrder.qnt = pendingOrder.qnt - IncomingOrder.qnt;
						IncomingOrder.qnt = 0; // So we can quit the loop
						Pending.set(ord_no, pendingOrder); //Make the changes in the list
					}
				}
				else
					break;
			}
			if(IncomingOrder.qnt>0)
			{
				Pending.add(IncomingOrder); // If the order wasn't able to complete
			}
			else if(IncomingOrder.qnt==0)
			{
				receivedOrder=false;
			}
		}
		return TradesMade;
	}

	public void printPending()
	{
		System.out.println("Time\tType\tFrom\tStock\tQnt\tPrice");
		for(int i=0;i<Pending.size();i++)
		{
			Order temp = Pending.get(i);
			System.out.println(temp.time +"\t" + temp.type
					+ "\t" + temp.from + "\t" + temp.stock + "\t"
					+ temp.qnt + "\t" + temp.price);
		}
	}
}
