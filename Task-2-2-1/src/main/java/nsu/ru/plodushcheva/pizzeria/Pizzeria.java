package nsu.ru.plodushcheva.pizzeria;

import nsu.ru.plodushcheva.Threads.*;
import nsu.ru.plodushcheva.json.PizzeriaData;

import java.util.*;
import java.util.concurrent.*;

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
        Stock stock = new Stock(data.stockSize());

        for (int i = 0; i < data.getNumCookers(); i++) {
            String name = data.cookers().get(i).getName();
            int strength = data.cookers().get(i).getStrength();
            cooks.add( new Cook(name, strength, orderQueue, stock));
            System.out.println("Cooker name: " + name + " Strength " + strength);
        }

        for (int i = 0; i < data.getNumCouriers(); i++) {
            String name = data.couriers().get(i).getName();
            int maxTrunkSize = data.couriers().get(i).getMaxTrunkSize();
            couriers.add(new Courier(name, maxTrunkSize, stock));
            System.out.println("Courier name: " + name + " TrunkSize " + maxTrunkSize);
        }

    }

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
