package nsu.ru.plodushcheva;

import nsu.ru.plodushcheva.Threads.Order;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class TakeOrders implements Runnable{
    private final BlockingQueue<Order> orderQueue;
    private Random random;
    private int id;


    public TakeOrders(BlockingQueue<Order> orderQueue){
        this.orderQueue = orderQueue;
        this.random = new Random();
        this.id = 0;
    }
    //нужно взаимодействие с очередью
    //возвращать и поварам

    @Override
    public void run() {
        System.out.println(" Pizzeria is open ");

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1 + random.nextInt() % 4);
                Order order = new Order(id++, random.nextInt() % 10);
                orderQueue.add(order);
                System.out.println("id: " + order.getOrderId() + " pizza: " + " IN QUEUE");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


