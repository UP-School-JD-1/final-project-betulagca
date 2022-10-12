package Entites;

public class Desk {
	
	private boolean isAvaliable;
    private Customer customer;
    private final int deskName;

    public Desk(int deskName, boolean isAvaliable) {
        this.deskName = deskName;
		this.isAvaliable = isAvaliable;
    }

    public Desk changeState() {
        this.setAvaliable(!this.isAvaliable);
        return this;
    }

    public boolean isAvaliable() {
        return isAvaliable;
    }

    private void setAvaliable(boolean avaliable) {
        isAvaliable = avaliable;
    }

    public int getDeskID() {
		return deskName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
