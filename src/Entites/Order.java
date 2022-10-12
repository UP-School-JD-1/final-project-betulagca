package Entites;


public class Order {
	
	 private final int orderID;
	    private final Desk desk;
	    private boolean isReady;
	    private boolean isHandOfCheff;
	    private int wrapSize;

	    public Order(int orderID, Desk desk) {
	        this.orderID = orderID;
	        this.desk = desk;
	        isReady = false;
	        isHandOfCheff = false;
	        wrapSize = 0;
	   
	    }

		public int getOrderID() {
	        return orderID;
	    }

	    public Desk getDesk() {
	        return desk;
	    }

	    public boolean isReady() {
	        return isReady;
	    }

	    public void setReady(boolean ready) {
	        isReady = ready;
	    }

	    public boolean isHandOfCheff() {
	        return isHandOfCheff;
	    }

	    public void setHandOfCheff(boolean handOfCheff) {
	        isHandOfCheff = handOfCheff;
	    }

	    public int getWrapSize() {
	        return wrapSize;
	    }

	    public void setWrapSize(int wrapSize) {
	        this.wrapSize = wrapSize;
	        System.out.println("Masa_" + desk.getDeskID() + "'den" + " " + desk.getCustomer().getCustomerID() + "'in "
	                + wrapSize + " birimlik durum siparisi olusturuldu.");
	    }
   
 	   
}
