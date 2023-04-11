package nsu.ru.plodushcheva.Threads;

import nsu.ru.plodushcheva.pizzeria.Order;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TakeOrders implements Runnable{
    private final BlockingQueue<Order> orderQueue;
    private Random random;
    private int id;
    private static boolean working;


    public TakeOrders(BlockingQueue<Order> orderQueue){
        this.orderQueue = orderQueue;
        this.random = new Random();
        this.id = 0;
        working = true;
    }


    public static void stop(){
        working = false;
        System.out.println(" Pizzeria is closing ");

    }
    //нужно взаимодействие с очередью
    //возвращать и поварам

    @Override
    public void run() {
        System.out.println(" Pizzeria is open ");

        while (working) {
            try {
                //Order order = new Order(id++, random.nextInt() % 10);
                Order order = new Order(id++, 1);

                TimeUnit.SECONDS.sleep(2 + random.nextInt() % 4);
                orderQueue.add(order);
                System.out.println("Order " + order.getOrderId() + " " +  order.getStatus());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Orders done");

    }

}


