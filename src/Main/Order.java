package Main;


/*This class is for the orders placed by the clients*/

public class Order {
	int time;
	String type;
	String from;
	String stock;
	int qnt;
	int price;
	
	Order(int t,String ty, String fr, String st, int q, int pr)//constructor
	{
		time = t;
		type=ty;
		from = fr;
		stock = st;
		qnt = q;
		price = pr;
	}
	
	
	/* CheckpossibleTrade accepts one Order class type as parameter and checks if the 
	 * the two orders can be used to make transactions i.e one is selling the given stock at a price lesser
	 * than the one bid by the buyer and they are requesting the same stock,etc. It return true if all
	 * such conditions are met, else returns false*/
	public boolean checkPossibleTrade(Order incoming)
	{
		if(this.stock.equals(incoming.stock))
		{
			if(this.type.equals("sell") && incoming.type.equals("buy"))
			{
				if(this.price < incoming.price)
					return true;
			}
			else if(this.type.equals("buy") && incoming.type.equals("sell"))
			{
				if(incoming.price < this.price)
					return true;
			}
		}
		return false;
	}
}
