package nsu.ru.plodushcheva.Threads;

public interface Worker extends Runnable {
    @Override
    void run();

    void stop();
}
