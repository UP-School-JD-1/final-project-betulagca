package Thread;

import Entites.Customer;
import Entites.Desk;
import Entites.Waiter;

public class CustomerThread extends Thread {
    Customer customer;
    Desk[] desks;
    Desk tempCustomerSitDesk;
    Waiter[] waiters;
    Waiter hasOrderWaiter;

    public CustomerThread(Customer customer, Desk[] desks, Waiter[] waiters) {
        this.customer = customer;
        this.desks = desks;
        this.waiters = waiters;
    }


    private void searchWaiter() throws InterruptedException {
        synchronized (waiters) {
            int j = 0;
            while (!customer.isServiceOpen()) {

                if (j > waiters.length - 1) {
                    waiters.wait();
                    j = 0;
                }

                if (customer.callWaiter(waiters[j])) {
                    waiters[j].changeState();
                    customer.setServiceOpen(true);
                    hasOrderWaiter = waiters[j];

                  
                    waiters[1].changeState();
                    waiters.notify();
                }
                j++;
            }
        }
    }

    private void searchDesk() throws InterruptedException {
        synchronized (desks) {
            int j = 0;
            while (!customer.isSit()) {

                if (j > desks.length - 1) {
                    desks.wait();
                    j = 0;
                }

                if (customer.selectDesk(desks[j])) {
                    desks[j].changeState();
                    customer.setSit(true);
                    desks[j].setCustomer(customer);
                    tempCustomerSitDesk = desks[j];

                    
                    desks[0].changeState();
                    desks.notify();
                }
                j++;
            }
        }
    }


    private void createOrder() throws InterruptedException {
        OrderThread orderThread = new OrderThread(customer.getWrapSize(), tempCustomerSitDesk, waiters, hasOrderWaiter);
        synchronized (orderThread) {
            orderThread.start();
            orderThread.join();
        }
    }


    @Override
    public void run() {
        System.out.println(this.getName() + " is starting");
        
		try {
            searchDesk();
            searchWaiter();
            createOrder();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println(this.getName() + " is finished");
  
   }
}
    