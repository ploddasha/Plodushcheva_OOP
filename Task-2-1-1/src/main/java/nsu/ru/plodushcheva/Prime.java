package nsu.ru.plodushcheva;

public class Prime {

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
