package nsu.ru.plodushcheva.Threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Courier extends Thread{
    private final String name;

    //private final int courierId;
    private final int maxTrunkSize;
    private final Stock stock;
    private final Queue<Order> pizzasInTrunk; //<Pizza>
    private boolean orderComplete;

    public Courier(String name, int maxTrunkSize, Stock stock) {
        //this.courierId = courierId;
        this.name = name;
        this.maxTrunkSize = maxTrunkSize;
        this.stock = stock;
        this.pizzasInTrunk = new LinkedList<>();
        this.orderComplete = false;
    }

    public void run() {
        while (!orderComplete) {
            try {
                Order pizza = stock.takePizza(maxTrunkSize - pizzasInTrunk.size());
                if (pizza != null) {
                    pizzasInTrunk.add(pizza);
                } else {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (pizzasInTrunk.size() > 0) {
                deliverPizzas();
            }
        }
    }

    private void deliverPizzas() {
        int deliveredPizzas = new Random().nextInt(pizzasInTrunk.size()) + 1;
        for (int i = 0; i < deliveredPizzas; i++) {
            pizzasInTrunk.remove();
        }
        if (pizzasInTrunk.size() == 0) {
            orderComplete = true;
            System.out.println("Order " + stock.getCurrentOrderId() + " delivered by courier " + name);
        }
    }
}
