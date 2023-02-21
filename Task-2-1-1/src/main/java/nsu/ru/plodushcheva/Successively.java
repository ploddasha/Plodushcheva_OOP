package nsu.ru.plodushcheva;

import java.util.List;
import java.util.concurrent.*;

public class Successively {
    public boolean func(List<Integer> arr){

        for (Integer number : arr) {
            if (!Prime.isPrime(number)) {
                return true;
            }
        }
        return false;
    }
}
