package nsu.ru.plodushcheva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultiThread {
    private boolean notOnlyPrimes = false;

    public class DifferentThread extends Thread{

        private final List<Integer> arr;
        public DifferentThread(List<Integer> arr){
            this.arr = arr;
        }

        @Override
        public void run(){
            for (Integer number : arr) {
                if (!Prime.isPrime(number)) {
                    System.out.println("Hi from the thread!");
                    notOnlyPrimes = true;
                    break;
                }
            }
        }
    }

    public boolean func(int countOfThreads, List<Integer> arr) throws Exception {
        int numThreads = Runtime.getRuntime().availableProcessors();
        if (countOfThreads < 1){
            throw new Exception();
        }
        if(countOfThreads > numThreads){
            countOfThreads = numThreads;
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
