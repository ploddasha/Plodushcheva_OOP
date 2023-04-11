package nsu.ru.plodushcheva.Threads;

import nsu.ru.plodushcheva.pizzeria.Order;
import nsu.ru.plodushcheva.pizzeria.Stock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Courier implements Runnable{
    private final String name;

    //private final int courierId;
    private final int maxTrunkSize;
    private final Stock stock;
    private final Queue<Order> pizzasInTrunk; //<Pizza>
    private static boolean working;

    public Courier(String name, int maxTrunkSize, Stock stock) {
        //this.courierId = courierId;
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
                if (pizzasInTrunk.size()<maxTrunkSize) {
                    Order order = stock.takeOrder();
                    if (order != null) {
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
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("couriers done");
        System.out.println(" Pizzeria is closed ");


    }

    private void deliverPizzas() throws InterruptedException {
        //int deliveredPizzas = new Random().nextInt(pizzasInTrunk.size()) + 1;
        for (int i = 0; i < pizzasInTrunk.size(); i++) {
            TimeUnit.SECONDS.sleep(6 );
            Order order = pizzasInTrunk.remove();

            System.out.println("Order " + order.getOrderId() + " delivered by courier " + name);

        }
    }
    public static void stop() {
        working = false;
    }
}
