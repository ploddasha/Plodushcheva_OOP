package nsu.ru.plodushcheva.Threads;

import java.util.Random;

public class Cooker extends Thread{
    private String name;
    private int strength;

   // private final int bakerId;
    private final Stock stock;
    private boolean canProduce;

    public Cooker(String name, int strength, Stock stock) {
        //this.cookerId = cookerId;
        this.name = name;
        this.strength = strength;
        this.stock = stock;
        this.canProduce = true;
    }

    public void run() {
        while (canProduce) {
            try {
                Order order = stock.takeOrder();
                if (order != null) {
                    Order pizza = makePizza(order);
                    if (stock.addPizza(pizza)) {
                        System.out.println("Pizza for order " + order.getOrderId() + " cooked by cooker " + name);
                    } else {
                        System.out.println("Pizza for order " + order.getOrderId() + " waiting for free stock space");
                        Thread.sleep(1000);
                    }
                } else {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Order makePizza(Order order) throws InterruptedException {
        Thread.sleep(500 + new Random().nextInt(1500));
        return new Order(order.getOrderId(), 2);
    }

    public void stopCooking() {
        canProduce = false;
    }
}
