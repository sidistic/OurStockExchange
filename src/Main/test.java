package Main;
import Main.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test {
	
	PreviousTransactions pt = new PreviousTransactions();
	PendingOrders po = new PendingOrders();
	Order[] cases = new Order[6];
	Trade[] trans_exp = new Trade[4];
	

	@Test
	void test() {
		// These are the test inputs
		cases[0] = new Order(2,"sell","c1","s1",20,230);
		cases[1] = new Order(3,"sell","c5","s1",20,220);
		cases[2] = new Order(4,"buy","c2","s1",25,250);
		cases[3] = new Order(5,"buy","c3","s1",20,260);
		cases[4] = new Order(6,"buy","c4","s2",20,260);
		cases[5] = new Order(6,"sell","c4","s2",25,260);

		for(int i=0;i<6;i++)//Loop to make do each order
		{
			po.setIncomingOrder(cases[i]);
			pt.addToRecords((po.makeTransaction()));
		}
		
		//Expected Output
		trans_exp[0] = new Trade(4, 2, "c2", "c1", "s1", 20, 230);		//Adding expected trades
		trans_exp[1] = new Trade(4, 3, "c2", "c5", "s1", 5, 220);
		trans_exp[2] = new Trade(5, 3, "c3", "c5", "s1", 15, 220);
		trans_exp[3] = new Trade(6, 6, "c4", "c4", "s2", 20, 260);
		
		
		//For Junit
		Object[] tocheck = pt.Records.toArray();
		assertArrayEquals(trans_exp,tocheck);

		
	}

}
