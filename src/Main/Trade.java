package Main;

/*This class is for the orders that have been completed*/
public class Trade {
	int b_time;
	int s_time;
	String buyer;
	String seller;
	String stock;
	int qnt;
	int price;
	
	Trade()
	{
		b_time = 0;
		s_time = 0;
		buyer = "_";
		seller = "_";
		stock = "_";
		qnt = 0;
		price = 0;
	}
	
	Trade(int nbt,int nst, String nb, String ns, String st,int nq, int np)
	{
		b_time = nbt;
		s_time = nst;
		buyer = nb;
		seller = ns;
		stock = st;
		qnt = nq;
		price = np;
	}
	/* For Junit so that assertArrayEquals works. prints true if the 2 given orders are equal*/
	@Override
	public boolean equals(Object obj){
		Trade newt = (Trade) obj;
		if(this.b_time == newt.b_time)
			if(this.s_time == newt.s_time)
				if(this.buyer.equals(newt.buyer))
					if(this.seller.equals(newt.seller))
						if(this.stock.equals(newt.stock))
							if(this.qnt == newt.qnt)
								if(this.price == newt.price)
									return true;
		return false;
	}
	
	/*For junit, so that each have diff hashcode*/
	@Override
	public int hashCode() {
		return b_time*s_time*qnt*price;
	}
}
