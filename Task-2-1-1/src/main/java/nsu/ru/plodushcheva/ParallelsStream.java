package nsu.ru.plodushcheva;

import java.util.List;

public class ParallelsStream {
    public boolean func(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch(n -> !Prime.isPrime(n));
    }
}
