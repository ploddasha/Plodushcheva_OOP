package nsu.ru.plodushcheva.pizzeria;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Stock{
    private final int capacity;
    // можно ли очереди задать определенный размер?
    // и как происходит добавление если уже нельзя добавить
    private BlockingQueue<Order> orders;

    public Stock(int capacity) {
        this.capacity = capacity;
        orders = new LinkedBlockingQueue<>();
    }

    //по capacity?
    public Order takePizza(int capacity) {
        return new Order(1, 2);
    }
    // на складе готовая продукция, не заказы на приготовление!
    public Order takeOrder() throws InterruptedException {
        return orders.take();
    }

    public int getCurrentOrderId() {
        return 2;
    }
    public boolean canAdd(Order order) {
        return order.getPizzaCount() < (capacity - orders.size());
    }

    public void addOrder(Order order) {
        if (canAdd(order)) {
            orders.add(order);
            order.setStatus(Order.Status.STORED);
            System.out.println("Order  " + order.getOrderId() + " " + order.getStatus() );

        }
    }
}
