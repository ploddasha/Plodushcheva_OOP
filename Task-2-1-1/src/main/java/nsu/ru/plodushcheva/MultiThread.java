package nsu.ru.plodushcheva;

import java.util.List;

/**
 * Checks the list for a non-complex number using threads.
 * Threads are created by an extension from the Thread class.
 */
public class MultiThread {
    private boolean notOnlyPrimes = false;

    /**
     * Class extends to the thread and overrides the run method.
     */
    public class DifferentThread extends Thread {

        private final List<Integer> arr;

        public DifferentThread(List<Integer> arr) {
            this.arr = arr;
        }

        @Override
        public void run() {
            for (Integer number : arr) {
                if (!Prime.isPrime(number)) {
                    notOnlyPrimes = true;
                    break;
                }
            }
        }
    }

    /**
     * Method for checking for the content of a composite number.
     * The list is divided into constituent parts according to the number of streams.
     *
     * @param countOfThreads desired number of threads
     * @param arr is checked for composite numbers
     * @return true if there are composite numbers
     * @throws Exception if the desired number of threads is less than 1
     */
    public boolean func(int countOfThreads, List<Integer> arr) throws Exception {
        //int numThreads = Runtime.getRuntime().availableProcessors();
        if (countOfThreads < 1) {
            throw new Exception("Wrong count of Threads");
        }

        int arrSize = arr.size() / countOfThreads;
        DifferentThread[] threads = new DifferentThread[countOfThreads];
        for (int i = 0; i < countOfThreads; i++) {
            threads[i] = new DifferentThread(arr.subList(i * arrSize, (i + 1) * arrSize));
            threads[i].start();
        }

        for (int i = 0; i < countOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return notOnlyPrimes;
    }


}
