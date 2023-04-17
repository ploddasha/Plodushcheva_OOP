package nsu.ru.plodushcheva.Threads;

import nsu.ru.plodushcheva.pizzeria.Order;
import nsu.ru.plodushcheva.pizzeria.Stock;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Cook implements Worker{
    private final String name;
    private final int strength;
    private final BlockingQueue<Order> orderQueue;


    // private final int bakerId;
    private final Stock stock;
    private volatile boolean working;

    public Cook(String name, int strength, BlockingQueue<Order> orderQueue, Stock stock) {
        //this.cookerId = cookerId;
        this.name = name;
        this.strength = strength;
        this.stock = stock;
        this.orderQueue = orderQueue;
        working = true;
    }

    @Override
    public void run() {

        while (working) {
            try {
                Order order = orderQueue.take();
                makePizza(order);
                stock.addOrder(order);
            } catch (InterruptedException e) {
                System.out.println("ttttttt");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Cook " + name + " finished work");

    }

    private void makePizza(Order order) throws InterruptedException {
        Thread.sleep(500 - strength);
        order.setStatus(Order.Status.COOKED);
        System.out.println("Order  " + order.getOrderId() + " "  + order.getStatus() + " by cooker " + name);

    }

    @Override
    public void stop() {
        working = false;
        Thread.currentThread().interrupt();

    }
}
