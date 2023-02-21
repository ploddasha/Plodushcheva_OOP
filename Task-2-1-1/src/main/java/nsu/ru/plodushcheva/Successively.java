package nsu.ru.plodushcheva;

import java.util.List;

/**
 * The usual calculation on finding a non-complex number in a list.
 * One standard thread.
 */
public class Successively {
    /**
     * function checks for composite numbers.
     *
     * @param arr is checked for composite numbers
     * @return true if there are composite numbers
     */
    public boolean func(List<Integer> arr) {

        for (Integer number : arr) {
            if (!Prime.isPrime(number)) {
                return true;
            }
        }
        return false;
    }
}
