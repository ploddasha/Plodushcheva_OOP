package nsu.ru.plodushcheva;

/**
 * Class to check whether the number is prime or composite.
 */
public class Prime {

    /**
     * Method for checking for prime numbers.
     *
     * @param n number to check
     * @return true if  n is prime
     */
    public static boolean isPrime(Integer n) {
        if (n <= 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
