package nsu.ru.plodushcheva.Threads;

import java.util.Random;

public class Order extends Thread{
    private final int orderId;
    private final int pizzaSize;

    public Order(int orderId, int pizzaSize) {
        this.orderId = orderId;
        this.pizzaSize = pizzaSize;
    }
    public Order() {
        this.orderId = new Random().nextInt();
        this.pizzaSize = 2;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getPizzaSize() {
        return pizzaSize;
    }
}
