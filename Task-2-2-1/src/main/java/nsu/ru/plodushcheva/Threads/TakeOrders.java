package nsu.ru.plodushcheva.Threads;

import nsu.ru.plodushcheva.pizzeria.Order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TakeOrders implements Worker{
    private final BlockingQueue<Order> orderQueue;
    private int id;
    private static boolean working;


    public TakeOrders(BlockingQueue<Order> orderQueue){
        this.orderQueue = orderQueue;
        this.id = 0;
        working = true;
    }



    @Override
    public void run() {
        while (working && !Thread.currentThread().isInterrupted()) {
            try {
                Order order = new Order(id++);
                TimeUnit.SECONDS.sleep(2);
                orderQueue.add(order);
                System.out.println("Order " + order.getOrderId() + " " +  order.getStatus());
            } catch (InterruptedException e) {
                    //e.printStackTrace();
                System.out.println("Interrupted");
            }
        }
        System.out.println("We do not accept orders");

    }


    @Override
    public void stop(){
        working = false;
        Thread.currentThread().interrupt();

    }

}


