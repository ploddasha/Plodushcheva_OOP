package nsu.ru.plodushcheva.Threads;

import java.util.Queue;

public class Stock{
    private final int capacity;
    private Queue<Order> orders;

    public Stock(int capacity) {
        this.capacity = capacity;
    }

    public Order takePizza(int capacity) {
        Order order = new Order(1, 2);
        return order;
    }

    public Order takeOrder() {
        Order order = new Order(1, 2);
        return order;
    }

    public int getCurrentOrderId() {
        return 2;
    }

    public boolean addPizza(Order order) {
        boolean rez = true;
        return rez;
    }
}
