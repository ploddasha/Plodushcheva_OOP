package nsu.ru.plodushcheva.Threads;

import java.util.Queue;

public class Stock{
    private final int capacity;
    private Queue<Order> orders;

    public Stock(int capacity) {
        this.capacity = capacity;
    }

    //по capacity?
    public Order takePizza(int capacity) {
        return new Order(1, 2);
    }
    // на складе готовая продукция, не заказы на приготовление!
    public Order takeOrder() {
        return new Order(1, 2);
    }

    public int getCurrentOrderId() {
        return 2;
    }

    public boolean addPizza(Order order) {
        return true;
    }
}
