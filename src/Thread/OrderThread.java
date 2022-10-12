package Thread;

import java.util.concurrent.PriorityBlockingQueue;

import Entites.Cheff;
import Entites.Desk;
import Entites.Order;
import Entites.Waiter;

public class OrderThread extends Thread {

	    private static final Cheff[] cheffs = {
	            new Cheff("Kaan", true),
	            new Cheff("Ahmet", true),
	            new Cheff("Begum", true)
	    };
	    private final int wrapSize;
	    private final Order order; // siparis
	    private final Waiter[] waiters; // garson listesi
	    private final Waiter hasOrderWaiter; // Siporisi gonderen garson
	    int orderID;
	    private Order waitedOrder; // siparis
	    private Cheff orderCheff;
	    private PriorityBlockingQueue<Integer> wrap;


	    public OrderThread(int wrapSize, Desk customerOrderDesk, Waiter[] waiters, Waiter waiter) {
	        this.wrapSize = wrapSize;
	        order = new Order(orderID++, customerOrderDesk);
	        this.waiters = waiters;
	        this.hasOrderWaiter = waiter;
	    }

	    private void setOrderWrapSize() {
	        order.setWrapSize(wrapSize);
	    }

	    private void searchAvaliableCheffAndSendOrder() throws InterruptedException {

	        synchronized (cheffs) {
	            int i = 0;
	            while (!order.isHandOfCheff()) {
	                if (i > cheffs.length - 1) {
	                    cheffs.wait();
	                    i = 0;
	                }

	                if (cheffs[i].isAvaliable()) {
	                    cheffs[i].setOrder(order);
	                    waitedOrder = order;
	                    cheffs[i].setAvaliable(false);
	                    cheffs[i].setWaiters(waiters);
	                    order.setHandOfCheff(true);
	                    orderCheff = cheffs[i];
	                    System.out.println("Garson_" + hasOrderWaiter.getWaiterID() + ", diger musteriler ile ilgilenmek uzere gezinmeye basladi.");
	                    hasOrderWaiter.changeState();

	                }
	                i++;

	            }

	        }
	    }

	    private void waitOrder() throws InterruptedException {
	        synchronized (waitedOrder) {
	            while (!waitedOrder.isReady()) {
	                wrap = orderCheff.prepareOrder();
	                if (waitedOrder.isReady()) {
	                    searchWaiterAndSendOrderToCustomer(wrap);
	                }
	            }

	        }
	    }

	    private void searchWaiterAndSendOrderToCustomer(PriorityBlockingQueue<Integer> wrap) throws InterruptedException {
	        synchronized (waiters) {
	            int j = 0;

	            while (!waiters[j].isAvaliable()) {
	                j++;
	                if (j > waiters.length - 1) j = 0;

	                if (waiters[j].isAvaliable()) {
	                    order.getDesk().getCustomer().eatWrap(wrap);
	                }
	            }
	        }
	    }


	    @Override
	    public void run() {
	        try {
	            setOrderWrapSize();
	            searchAvaliableCheffAndSendOrder();
	            waitOrder();
	        } catch (InterruptedException interruptedException) {
	            interruptedException.printStackTrace();
	        }
	    }

}
