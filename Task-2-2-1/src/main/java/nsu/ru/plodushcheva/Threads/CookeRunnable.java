package nsu.ru.plodushcheva.Threads;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class CookeRunnable implements Runnable{
    private String name;
    private int strength;
    private BlockingQueue<Order> orderQueue;


    // private final int bakerId;
    private final Stock stock;
    private boolean canProduce;

    public CookeRunnable(String name, int strength, BlockingQueue orderQueue, Stock stock) {
        //this.cookerId = cookerId;
        this.name = name;
        this.strength = strength;
        this.stock = stock;
        this.canProduce = true;
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //взять заказ из очереди TakeOrders.
                //TimeUnit.SECONDS.sleep(experience);
                Order order = orderQueue.take();
                //Order order = stock.takeOrder();
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
