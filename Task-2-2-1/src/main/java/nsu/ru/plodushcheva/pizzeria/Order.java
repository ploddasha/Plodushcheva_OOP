package nsu.ru.plodushcheva.pizzeria;

import java.util.Random;

public class Order {
    private final int orderId;
    private Status status;
    public enum Status {
        CREATED,
        COOKED,
        STORED,
        DELIVERING,
        DELIVERED
    }

    public Order(int orderId) {
        this.orderId = orderId;
        this.status = Status.CREATED;
    }

    public int getOrderId() {
        return orderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
