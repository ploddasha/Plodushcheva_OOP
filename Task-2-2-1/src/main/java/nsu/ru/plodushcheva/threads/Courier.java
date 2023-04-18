package nsu.ru.plodushcheva.threads;

import java.util.concurrent.TimeUnit;
import java.util.LinkedList;
import java.util.Queue;
import nsu.ru.plodushcheva.pizzeria.Order;
import nsu.ru.plodushcheva.pizzeria.Stock;


public class Courier implements Worker {
    private final String name;
    private final int maxTrunkSize;
    private final Stock stock;
    private final Queue<Order> pizzasInTrunk;
    private static boolean working;

    public Courier(String name, int maxTrunkSize, Stock stock) {
        this.name = name;
        this.maxTrunkSize = maxTrunkSize;
        this.stock = stock;
        this.pizzasInTrunk = new LinkedList<>();
        working = true;
    }

    @Override
    public void run() {
        while (working) {
            try {
                if (pizzasInTrunk.size() < maxTrunkSize) {
                    Order order = stock.takeOrder();
                    if (order != null) {
                        order.setStatus(Order.Status.DELIVERING);
                        System.out.println("Order " + order.getOrderId() +
                                " "  + order.getStatus() + " by courier " + name);
                        pizzasInTrunk.add(order);
                    } else {
                        Thread.sleep(1000);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (pizzasInTrunk.size() > 0) {
                try {
                    deliverPizzas();
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Courier " + name + " finished work");

    }

    private void deliverPizzas() throws InterruptedException {
        for (int i = 0; i < pizzasInTrunk.size(); i++) {
            TimeUnit.SECONDS.sleep(6);
            Order order = pizzasInTrunk.remove();
            order.setStatus(Order.Status.DELIVERED);
            System.out.println("Order " + order.getOrderId() + " "  +
                    order.getStatus() + " by courier " + name);

        }
    }

    @Override
    public void stop() {
        working = false;
        try {
            deliverPizzas();
        } catch (InterruptedException e) {
            System.out.println("Courier " + name +
                    " has been interrupted while delivering pizzas.");
        }
        Thread.currentThread().interrupt();
    }
}
