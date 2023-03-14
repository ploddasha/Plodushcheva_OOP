package nsu.ru.plodushcheva;

import nsu.ru.plodushcheva.Threads.Cooker;
import nsu.ru.plodushcheva.Threads.Courier;
import nsu.ru.plodushcheva.Threads.Order;
import nsu.ru.plodushcheva.Threads.Stock;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {

    private final List<Cooker> cookers = new ArrayList<>();
    private final List<Courier> couriers = new ArrayList<>();
    private BlockingQueue<Order> orderQueue;
    private ExecutorService executor;
    private Stock stock;

    public Pizzeria(PizzeriaData data) {
        if (data == null) {
            System.err.println("Failed to load pizzeria parameters from file");
            return;
        }
        this.executor = Executors.newCachedThreadPool();
        this.orderQueue = new LinkedBlockingQueue<>();
        stock = new Stock(data.getStockSize());

        for (int i = 0; i < data.getNumCookers(); i++) {
            String name = data.getCookers().get(i).getName();
            int strength = data.getCookers().get(i).getStrength();
            cookers.add( new Cooker(name, strength, stock));
            System.out.println("Cooker name: " + name + " Strength " + strength);
        }

        for (int i = 0; i < data.getNumCouriers(); i++) {
            String name = data.getCouriers().get(i).getName();
            int maxTrunkSize = data.getCouriers().get(i).getMaxTrunkSize();
            couriers.add(new Courier(name, maxTrunkSize, stock));
            System.out.println("Courier name: " + name + " TrunkSize " + maxTrunkSize);
        }

    }

    public void work() {
        System.out.println("Welcome to the Pizzeria!");
        executor.execute(new TakeOrders(orderQueue));

        executor.shutdown();

        // Start the cookers
        for (Cooker cooker : cookers) {
            new Thread(cooker).start();
        }

        // Start the couriers
        for (Courier courier : couriers) {
            new Thread(courier).start();
        }

        // Start the order dispatcher
        //new Thread(new OrderDispatcher()).start();
    }

    public void stop() {
        // Stop the bakers
        for (Cooker cooker : cookers) {
            cooker.stop();
        }

        // Stop the couriers
        for (Courier courier : couriers) {
            courier.stop();
        }

        // Stop the order dispatcher
        orderQueue.clear();
    }



}
