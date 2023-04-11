package nsu.ru.plodushcheva.pizzeria;

import nsu.ru.plodushcheva.Threads.*;
import nsu.ru.plodushcheva.json.PizzeriaData;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

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
            cookers.add( new Cooker(name, strength, orderQueue, stock));
            System.out.println("Cooker name: " + name + " Strength " + strength);
        }

        for (int i = 0; i < data.getNumCouriers(); i++) {
            String name = data.getCouriers().get(i).getName();
            int maxTrunkSize = data.getCouriers().get(i).getMaxTrunkSize();
            couriers.add(new Courier(name, maxTrunkSize, stock));
            System.out.println("Courier name: " + name + " TrunkSize " + maxTrunkSize);
        }

    }

    public void work(int time) {
        System.out.println("Welcome to the Pizzeria!");
        executor.execute(new TakeOrders(orderQueue));


        // Start the cookers
        for (Cooker cooker : cookers) {
            executor.execute(cooker);
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
        TakeOrders.stop();
        orderQueue.clear();
        Cooker.stop();
        Courier.stop();

        executor.shutdown(); // Остановка приема новых задач

        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate");
                }
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println(" Pizzeria is closed ");

    }
}
