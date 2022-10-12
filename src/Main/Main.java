package Main;

import Entites.Customer;
import Entites.Desk;
import Entites.Waiter;
import Thread.CustomerThread;

public class Main {
	
	 public static long start;
	 
	 public static void main(String[] args) throws InterruptedException {
	      
		     		 
		 
	        Desk[] desks = {
	                new Desk(1, false),
	                new Desk(2, false),
	                new Desk(3, true)};
	        
	 
	        Waiter[] waiters = {
	                new Waiter("Gizem", false),
	                new Waiter("Buse", false),
	                new Waiter("Erkan", true)};


	        Customer customer1 = new Customer("Ali", false);
	        Customer customer2 = new Customer("Eren", false);

	        
	        
	        CustomerThread thread = new CustomerThread(customer1, desks, waiters);
	        CustomerThread thread2 = new CustomerThread(customer2, desks, waiters);


	        thread.start();
	        thread2.start();
      
	        thread.join();
	        thread2.join();

	}

            public static String getTime() {
	       
			long currTime = (System.currentTimeMillis()-start)/1000;
	        String hrs, mins;
	        if (currTime < 60) {
	            hrs = "00";
	            mins = String.format("%02d", currTime);
	        }
	        else {
	            hrs = String.format("%02d", currTime / 60);
	            mins = String.format("%02d", currTime - ((currTime / 60) * 60));
	        }
	        return hrs+":"+mins;
	    }
	 
	 
}
