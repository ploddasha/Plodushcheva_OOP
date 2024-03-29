package nsu.ru.plodushcheva.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import nsu.ru.plodushcheva.pizzeria.Order;
import nsu.ru.plodushcheva.pizzeria.Stock;


/**
 * The Courier class represents a courier who delivers pizzas from the stock to the customers.
 */
public class Courier implements Worker {
    private final String name;
    private final int maxTrunkSize;
    private final Stock stock;
    private final BlockingQueue<Order> pizzasInTrunk;
    private volatile boolean working;

    /**
     * Constructs a Courier object with the given name, maximum trunk size, and stock.
     *
     * @param name the name of the courier
     * @param maxTrunkSize the maximum size of the courier's trunk
     * @param stock the stock from which the courier will take orders
     */
    public Courier(String name, int maxTrunkSize, Stock stock) {
        this.name = name;
        this.maxTrunkSize = maxTrunkSize;
        this.stock = stock;
        this.pizzasInTrunk = new ArrayBlockingQueue<>(maxTrunkSize);
        working = true;
    }

    /**
     * The run method of the Courier thread.
     */
    @Override
    public void run() {
        while (working && !Thread.currentThread().isInterrupted()) {
            try {
                for (int i = 0; i < maxTrunkSize; i++) {
                    if (pizzasInTrunk.size() < maxTrunkSize) {
                        Order order = stock.takeOrder();
                        order.setStatus(Order.Status.DELIVERING);
                        System.out.println("Order " + order.getOrderId()
                                + " "  + order.getStatus() + " by courier " + name);
                        pizzasInTrunk.add(order);
                    } else {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Courier " + name + " was interrupted");
            }
            if (pizzasInTrunk.size() > 0) {
                try {
                    deliverPizzas();
                } catch (InterruptedException e) {
                    System.err.println("Courier " + name
                            + " was interrupted during delivery");
                }
            }

        }
        System.out.println("Courier " + name + " finished work");
    }

    /**
     * Delivers the pizzas from the courier's trunk to the customers.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    private void deliverPizzas() throws InterruptedException {
        for (int i = 0; i < pizzasInTrunk.size(); i++) {
            TimeUnit.SECONDS.sleep(6);
            Order order = pizzasInTrunk.remove();
            order.setStatus(Order.Status.DELIVERED);
            System.out.println("Order " + order.getOrderId() + " "
                    + order.getStatus() + " by courier " + name);

        }
    }

    /**
     * Stops the Courier thread.
     */
    @Override
    public void stop() {
        working = false;
    }
}
