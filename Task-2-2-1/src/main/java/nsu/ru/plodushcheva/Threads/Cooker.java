package nsu.ru.plodushcheva.Threads;

import nsu.ru.plodushcheva.pizzeria.Order;
import nsu.ru.plodushcheva.pizzeria.Stock;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Cooker implements Runnable{
    private String name;
    private int strength;
    private BlockingQueue<Order> orderQueue;


    // private final int bakerId;
    private final Stock stock;
    private static boolean working;

    public Cooker(String name, int strength, BlockingQueue<Order> orderQueue, Stock stock) {
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
                //TimeUnit.SECONDS.sleep(experience);
                Order order = orderQueue.take();
                //Order order = stock.takeOrder();
                //Order pizza = makePizza(order);
                Thread.sleep(5 - strength);
                order.setStatus(Order.Status.COOKED);
                System.out.println("Order  " + order.getOrderId() + " "  + order.getStatus() + " by cooker " + name);

                if (stock.canAdd(order)) {
                    stock.addOrder(order);
                } else {
                    System.out.println("Pizza for order " + order.getOrderId() + " waiting for free space in stock");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("cookers done");

    }

    private Order makePizza(Order order) throws InterruptedException {
        Thread.sleep(500 + new Random().nextInt(1500));
        return new Order(order.getOrderId(), 2);
    }

    public static void stop() {
        working = false;
    }
}
/*
package nsu.ru.plodushcheva.Threads;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        int pizzaCount = order.getPizzaCount();
        TimeUnit.SECONDS.sleep(10 - strength);
        Thread.sleep(500 + new Random().nextInt(1500));
        return new Order(order.getOrderId(), 1);
    }

    public void stopCooking() {
        canProduce = false;
    }
}

 */