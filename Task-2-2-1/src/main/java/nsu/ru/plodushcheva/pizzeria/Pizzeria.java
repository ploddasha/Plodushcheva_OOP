package nsu.ru.plodushcheva.pizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import nsu.ru.plodushcheva.json.PizzeriaData;
import nsu.ru.plodushcheva.threads.Cook;
import nsu.ru.plodushcheva.threads.Courier;
import nsu.ru.plodushcheva.threads.TakeOrders;


/**
 * The Pizzeria class represents a pizzeria where cooks and couriers work to fulfill orders.
 * It uses a blocking queue
 * to store incoming orders and
 * an executor service to manage threads for the cooks and couriers.
 * It can be opened and closed for a specified period of time,
 * and also has a method to stop all ongoing tasks and shutdown
 * the executor service.
 */
public class Pizzeria {

    private final List<Cook> cooks = new ArrayList<>();
    private final List<Courier> couriers = new ArrayList<>();
    private BlockingQueue<Order> orderQueue;
    private ExecutorService executor;
    private TakeOrders takeOrders;

    public Pizzeria(PizzeriaData data) {
        if (data == null) {
            System.err.println("Failed to load pizzeria parameters from file");
            return;
        }
        this.executor = Executors.newCachedThreadPool();
        this.orderQueue = new LinkedBlockingQueue<>();
        Stock stock = new Stock(data.getStockSize());

        for (int i = 0; i < data.getNumCooks(); i++) {
            String name = data.getCooks().get(i).getName();
            int strength = data.getCooks().get(i).getStrength();
            cooks.add( new Cook(name, strength, orderQueue, stock));
            System.out.println("Cooker name: " + name + " Strength " + strength);
        }

        for (int i = 0; i < data.getNumCouriers(); i++) {
            String name = data.getCouriers().get(i).getName();
            int maxTrunkSize = data.getCouriers().get(i).getMaxTrunkSize();
            couriers.add(new Courier(name, maxTrunkSize, stock));
            System.out.println("Courier name: " + name + " TrunkSize " + maxTrunkSize);
        }

    }

    /**
     * Opens the pizzeria for the specified period of time, during which orders are taken and fulfilled.
     *
     * @param time The duration of time, in milliseconds, that the pizzeria should remain open.
     */
    public void work(int time) {
        System.out.println(" Pizzeria is open ");

        takeOrders = new TakeOrders(orderQueue);
        executor.execute(takeOrders);


        // Start the cookers
        for (Cook cook : cooks) {
            executor.execute(cook);
        }

        // Start the couriers
        for (Courier courier : couriers) {
            executor.execute(courier);
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stop();
            }
        }, time);


    }

    /**
     * Stops all ongoing tasks and shuts down the executor service.
     */
    public void stop() {
        System.out.println("Pizzeria is closing");
        executor.shutdown();
        takeOrders.stop();
        cooks.forEach(Cook::stop);
        couriers.forEach(Courier::stop);
        orderQueue.clear();


        try {
            Thread.sleep(30000);
            System.out.println("here");
            executor.shutdownNow();

        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }

        //executor.shutdownNow();

        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                System.out.println("here2");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting for tasks to complete");
        }

        if (executor.isTerminated()) {
            System.out.println(" Pizzeria is closed !");
        } else {
            System.out.println("Some tasks are still running");
        }


    }
}