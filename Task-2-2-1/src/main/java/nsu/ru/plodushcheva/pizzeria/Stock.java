package nsu.ru.plodushcheva.pizzeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * A class representing the stock of a pizzeria.
 * The stock is responsible for storing orders that are ready
 * to be delivered by the couriers.
 */
public class Stock {
    private final int capacity;

    private final BlockingQueue<Order> orders;

    public Stock(int capacity) {
        this.capacity = capacity;
        orders = new ArrayBlockingQueue<>(capacity);
    }


    /**
     * Gets the ID of the most recent order that was added to the stock.
     *
     * @return the ID of the most recent order that was added to the stock
     */
    public Order takeOrder() throws InterruptedException {
        return orders.take();
    }

    /**
     * Determines if the specified order can be added to the stock.
     *
     * @return true if the order can be added, false otherwise
     */
    public boolean canAdd() {
        return 1 <= (capacity - orders.size());
    }

    /**
     * Adds the specified order to the stock.
     * If the stock is full, the method blocks until space becomes available.
     *
     * @param order the order to be added
     */
    public void addOrder(Order order) {
        if (canAdd()) {
            orders.add(order);
            order.setStatus(Order.Status.STORED);
            System.out.println("Order  " + order.getOrderId() + " " + order.getStatus());

        }
    }

    /**
     * Adds the specified order to the stock.
     * If the stock is full, the method blocks until space becomes available.
     *
     * @param order the order to be added
     * @return true if added successful
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public boolean addOrder2(Order order) throws InterruptedException {
        boolean added = orders.offer(order, 10, TimeUnit.SECONDS);
        if (added) {
            order.setStatus(Order.Status.STORED);
            System.out.println("Order " + order.getOrderId() + " " + order.getStatus());
        } else {
            System.out.println("Error: Order "
                    + order.getOrderId() + " couldn't be added to stock.");
        }
        return added;
    }

}
