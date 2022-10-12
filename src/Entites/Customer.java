package Entites;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class Customer {
	
	    private final String customerName;
	    private boolean isSit;
	    private boolean isServiceOpen;
	    private final Random rnd = new Random();

	    public Customer(String customerName, boolean isSit) {
	        this.customerName = customerName;
	        this.isSit = isSit;
	        this.isServiceOpen = false;
	    }

	    public boolean selectDesk(Desk customerDesk) {
	        if (customerDesk.isAvaliable()) {
	            System.out.println("Musteri_" + customerName + ", Masa_" + customerDesk.getDeskID() + "'e oturdu.");
	            return true;
	        }
	        return false;
	    }

	    public boolean callWaiter(Waiter waiter) {
	        if (waiter.isAvaliable()) {
	            System.out.println("Musteri_" + customerName + ", Garson_" + waiter.getWaiterID() + "'i cagirdi.");
	            return true;
	        }
	        return false;
	    }

	    public void eatWrap(PriorityBlockingQueue<Integer> wrap) throws InterruptedException {
	        System.out.println("\nMusteri_" + customerName + "'siparisi eline ulasti.");
	        while (!wrap.isEmpty())
	            System.out.print(wrap.poll() + " ");
	        
	        Thread.sleep(1000);
	    }
	    
	    
	    public int getWrapSize() {
	        return rnd.nextInt(40);
	    }

	    public String getCustomerID() {
	        return customerName;
	    }

	    public boolean isSit() {
	        return isSit;
	    }

	    public void setSit(boolean sit) {
	        isSit = sit;
	    }

	    public boolean isServiceOpen() {
	        return isServiceOpen;
	    }

	    public void setServiceOpen(boolean serviceOpen) {
	        isServiceOpen = serviceOpen;
	    }
	

}
