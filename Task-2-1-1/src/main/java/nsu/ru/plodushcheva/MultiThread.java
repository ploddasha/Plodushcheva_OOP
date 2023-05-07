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
            if (arr.stream().anyMatch(n -> !Prime.isPrime(n))) {
                notOnlyPrimes = true;
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
     */
    public boolean nonPrimeFinder(int countOfThreads, List<Integer> arr) {
        //int numThreads = Runtime.getRuntime().availableProcessors();
        if (countOfThreads < 1) {
            throw new IllegalArgumentException("Wrong count of Threads");
        }

        int smallSize = arr.size() / countOfThreads;
        int largeSize = smallSize + 1;
        int largeNumber = arr.size() % countOfThreads;
        int smallNumber = countOfThreads - largeNumber;
        System.out.println(arr);
        System.out.println(countOfThreads);
        System.out.println(smallSize);
        System.out.println(largeSize);
        System.out.println(largeNumber);
        System.out.println(smallNumber);
        int index = 0;
        DifferentThread[] threads = new DifferentThread[countOfThreads];
        for (int i = 0; i < countOfThreads; i++) {
            int size = i < smallNumber ? smallSize : largeSize;
            threads[i] = new DifferentThread(arr.subList(index, index + size));
            System.out.println(arr.subList(index, index + size));
            threads[i].start();
            index += size;
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
