package nsu.ru.plodushcheva.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import nsu.ru.plodushcheva.pizzeria.Order;


/**
 * This class represents a worker that takes orders and adds them to the order queue.
 */
public class TakeOrders implements Worker {
    private final BlockingQueue<Order> orderQueue;
    private int id;
    private static boolean working;


    /**
     * Constructs a new TakeOrders worker with the specified order queue.
     *
     * @param orderQueue the blocking queue to add orders to
     */
    public TakeOrders(BlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
        this.id = 0;
        working = true;
    }


    /**
     * The method that executes the worker's job
     * of taking orders and adding them to the order queue.
     */
    @Override
    public void run() {
        while (working && !Thread.currentThread().isInterrupted()) {
            try {
                Order order = new Order(id++);
                TimeUnit.SECONDS.sleep(2);
                orderQueue.add(order);
                System.out.println("Order " + order.getOrderId() + " " +  order.getStatus());
            } catch (InterruptedException e) {                     //e.printStackTrace();
                System.out.println("Interrupted");
            }
        }
        System.out.println("We do not accept orders");

    }


    /**
     * The method that stops the worker from taking orders and adding them to the queue.
     */
    @Override
    public void stop() {
        working = false;
        Thread.currentThread().interrupt();

    }

}


