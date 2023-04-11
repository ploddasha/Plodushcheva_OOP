package nsu.ru.plodushcheva.pizzeria;

import jdk.jshell.Snippet;

import java.util.Random;

public class Order {
    private final int orderId;
    private final int pizzaCount;
    private Status status;
    public enum Status {
        CREATED,
        COOKED,
        STORED,
        DELIVERING,
        DELIVERED
    }

    public Order(int orderId, int pizzaCount) {
        this.orderId = orderId;
        this.pizzaCount = pizzaCount;
        this.status = Status.CREATED;
    }
    public Order() {
        this.orderId = new Random().nextInt();
        this.pizzaCount = 2;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getPizzaCount() {
        return pizzaCount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
