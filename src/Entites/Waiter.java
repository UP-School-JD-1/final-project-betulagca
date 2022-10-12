package Entites;

public class Waiter {
	
	private final String waiterID;
    private boolean isAvaliable;
	private Object lock;

    public Waiter(String waiterID, boolean isAvaliable) {
        this.waiterID = waiterID;
        this.isAvaliable = isAvaliable;
    }

    public String getWaiterID() {
        return waiterID;
    }

    public Waiter changeState() {
        this.setAvaliable(!this.isAvaliable);

        return this;
    }

    public boolean isAvaliable() {
        return isAvaliable;
    }

    private void setAvaliable(boolean avaliable) {
        isAvaliable = avaliable;
    }
    
    public void run() {
		
		lock = null;
		
		synchronized (lock) {
			try {
				// sleep!
				Thread.sleep(1000);
				System.out.println("About to notify the waiter..");
				lock.notify();
				System.out.println("Done notifying the waiter.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}
