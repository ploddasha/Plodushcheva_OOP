package nsu.ru.plodushcheva;

import java.util.List;

/**
 * Composite number finding using parallelStream() on a list.
 */
public class ParallelsStream {
    public boolean nonPrimeFinder(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch(n -> !Prime.isPrime(n));
    }
}
