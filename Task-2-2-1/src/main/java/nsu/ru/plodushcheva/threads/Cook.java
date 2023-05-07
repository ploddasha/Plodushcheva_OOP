package nsu.ru.plodushcheva.threads;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import nsu.ru.plodushcheva.pizzeria.Order;
import nsu.ru.plodushcheva.pizzeria.Stock;


/**
 * A class representing a cook in a pizzeria.
 */
public class Cook implements Worker {
    private final String name;
    private final int strength;
    private final BlockingQueue<Order> orderQueue;
    private final Stock stock;
    private volatile boolean working;

    /**
     * Constructs a Cook with the given name, strength, order queue and stock.
     *
     * @param name        the name of the cook.
     * @param strength    the strength of the cook.
     * @param orderQueue  the blocking queue of orders.
     * @param stock       the stock of the pizzeria.
     */
    public Cook(String name, int strength, BlockingQueue<Order> orderQueue, Stock stock) {
        this.name = name;
        this.strength = strength;
        this.stock = stock;
        this.orderQueue = orderQueue;
        working = true;
    }

    /**
     * The run method that cooks pizzas and adds them to the stock.
     */
    @Override
    public void run() {

        while (working && !Thread.currentThread().isInterrupted()) {
            try {
                Order order = orderQueue.take();
                makePizza(Objects.requireNonNull(order));
                stock.addOrder(order);

            } catch (InterruptedException e) {
                System.err.println("Cook " + name + " was interrupted while waiting");
                working = false;
            }
        }
        System.out.println("Cook " + name + " finished work");

    }

    /**
     * Makes a pizza for the given order and sets the status of the order to "cooked".
     *
     * @param order  the order for which to make a pizza.
     * @throws InterruptedException  if the thread is interrupted while sleeping.
     */
    private void makePizza(Order order) throws InterruptedException {
        Thread.sleep(500 - strength);
        order.setStatus(Order.Status.COOKED);
        System.out.println("Order  " + order.getOrderId() + " "
                + order.getStatus() + " by cooker " + name);

    }

    /**
     * Stops the cook from working.
     */
    @Override
    public void stop() {
        working = false;
    }
}
