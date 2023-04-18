package nsu.ru.plodushcheva.pizzeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Stock {
    private final int capacity;
    // можно ли очереди задать определенный размер?
    // и как происходит добавление если уже нельзя добавить
    private BlockingQueue<Order> orders;

    public Stock(int capacity) {
        this.capacity = capacity;
        orders = new ArrayBlockingQueue<>(capacity);
    }

    //по capacity?
    public Order takePizza(int capacity) {
        return new Order(1);
    }
    // на складе готовая продукция, не заказы на приготовление!
    public Order takeOrder() throws InterruptedException {
        return orders.take();
    }

    public int getCurrentOrderId() {
        return 2;
    }
    public boolean canAdd(Order order) {
        return 1 < (capacity - orders.size());
    }

    public void addOrder(Order order) {
        if (canAdd(order)) {
            orders.add(order);
            order.setStatus(Order.Status.STORED);
            System.out.println("Order  " + order.getOrderId() + " " + order.getStatus());

        }
    }

    public boolean addOrder2(Order order) throws InterruptedException {
        boolean added = orders.offer(order, 10, TimeUnit.SECONDS);
        if (added) {
            order.setStatus(Order.Status.STORED);
            System.out.println("Order " + order.getOrderId() + " " + order.getStatus());
        } else {
            System.out.println("Error: Order " +
                    order.getOrderId() + " couldn't be added to stock.");
        }
        return added;
    }

}
