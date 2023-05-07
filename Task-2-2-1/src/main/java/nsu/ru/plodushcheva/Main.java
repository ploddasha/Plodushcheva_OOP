package nsu.ru.plodushcheva;

import nsu.ru.plodushcheva.json.JsonParser;
import nsu.ru.plodushcheva.pizzeria.Pizzeria;

/**
 * The Main class is the entry point of the application.
 * It creates an instance of the Pizzeria class and starts the pizzeria simulation
 * by calling the "work" method with the specified time interval in milliseconds.
 */
public class Main {

    /**
     * The main method creates a Pizzeria instance with the data obtained by
     * parsing the info.json file using JsonParser, and calls the work method
     * on that instance to simulate the pizzeria's operation for the specified
     * time interval.
     *
     *  @param args the command line arguments (unused)
     */
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria(new JsonParser().getData());
        pizzeria.work();

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println("Stopping pizzeria...");
                pizzeria.stop();
                System.out.println("Stopped...");
                //System.exit(0);
                timer.cancel(); // stop the timer
            }
        }, 15000);

    }
}