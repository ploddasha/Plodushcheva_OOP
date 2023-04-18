package nsu.ru.plodushcheva.pizzeria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class StockTest {

    @Test
    public void testOrderStatusInStock() {
        Stock stock = new Stock(5);
        Order order = new Order(1);
        stock.addOrder(order);
        Assertions.assertEquals(Order.Status.STORED, order.getStatus());
    }

    @Test
    public void testAddOrder2() throws InterruptedException {
        Stock stock = new Stock(1);
        Order order1 = new Order(1);
        Order order2 = new Order(2);
        stock.addOrder2(order1);
        boolean added = stock.addOrder2(order2);
        Assertions.assertFalse(added);
    }

    @Test
    public void testTakeOrder() throws InterruptedException {
        Stock stock = new Stock(2);
        Order order = new Order(1);
        stock.addOrder(order);
        Order takenOrder = stock.takeOrder();
        Assertions.assertEquals(order, takenOrder);
    }

    @Test
    public void testCanAdd() {
        Stock stock = new Stock(2);
        Order order1 = new Order(1);
        Order order2 = new Order(2);
        new Order(3);
        boolean canAdd1 = stock.canAdd();
        stock.addOrder(order1);
        boolean canAdd2 = stock.canAdd();
        stock.addOrder(order2);
        boolean canAdd3 = stock.canAdd();
        Assertions.assertTrue(canAdd1);
        Assertions.assertTrue(canAdd2);
        Assertions.assertFalse(canAdd3);
    }

}