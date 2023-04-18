package nsu.ru.plodushcheva.threads;

public interface Worker extends Runnable {
    @Override
    void run();

    void stop();
}
