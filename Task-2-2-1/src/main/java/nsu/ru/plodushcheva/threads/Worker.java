package nsu.ru.plodushcheva.threads;

/**
 * The interface represents a thread worker that can perform work and be stopped.
 */
public interface Worker extends Runnable {
    @Override
    void run();

    void stop();
}
