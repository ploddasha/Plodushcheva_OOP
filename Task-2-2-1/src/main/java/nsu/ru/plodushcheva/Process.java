package nsu.ru.plodushcheva;

import nsu.ru.plodushcheva.Threads.Order;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class TakeOrders implements Runnable{
    private BlockingQueue<Order> orderQueue;
    private Random random;


    public TakeOrders(BlockingQueue<Order> orderQueue){
        this.orderQueue = orderQueue;
        this.random = new Random();
    }

    @Override
    public void run() {
        System.out.println(" Pizzeria is open ");

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1 + random.nextInt() % 4);
                Order order = new Order();
                orderQueue.add(order);
                System.out.println("id: " + order.getOrderId() + " || pizza: " + " IN QUEUE");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


