package nsu.ru.plodushcheva.pizzeria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    public void testOrderId() {
        Order order = new Order(1);
        int orderId = order.getOrderId();
        Assertions.assertEquals(1, orderId);
    }

    @Test
    public void testOrderCreatedStatus() {
        Order order = new Order(1);
        order.setStatus(Order.Status.CREATED);
        Order.Status status = order.getStatus();
        Assertions.assertEquals(Order.Status.CREATED, status);
    }

    @Test
    public void testOrderCookedStatus() {
        Order order = new Order(1);
        order.setStatus(Order.Status.COOKED);
        Order.Status status = order.getStatus();
        Assertions.assertEquals(Order.Status.COOKED, status);
    }

    @Test
    public void testOrderStoredStatus() {
        Order order = new Order(1);
        order.setStatus(Order.Status.STORED);
        Order.Status status = order.getStatus();
        Assertions.assertEquals(Order.Status.STORED, status);
    }

    @Test
    public void testOrderDeliveringStatus() {
        Order order = new Order(1);
        order.setStatus(Order.Status.DELIVERING);
        Order.Status status = order.getStatus();
        Assertions.assertEquals(Order.Status.DELIVERING, status);
    }

    @Test
    public void testOrderDeliveredStatus() {
        Order order = new Order(1);
        order.setStatus(Order.Status.DELIVERED);
        Order.Status status = order.getStatus();
        Assertions.assertEquals(Order.Status.DELIVERED, status);
    }


}