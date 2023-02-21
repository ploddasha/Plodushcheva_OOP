package nsu.ru.plodushcheva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ParallelsStreamTest {
    @Test
    public void exampleOneTest() {
        ParallelsStream s = new ParallelsStream();
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        boolean ans = s.func(arr);
        assertTrue(ans);

    }

    @Test
    public void exampleTwoTest() {
        ParallelsStream s = new ParallelsStream();
        List<Integer> arr = new ArrayList<>();
        arr.add(6997901);
        arr.add(6997927);
        arr.add(6997937);
        arr.add(6997967);
        arr.add(6998009);
        arr.add(6998029);
        arr.add(6998039);
        arr.add(6998051);
        arr.add(6998053);

        boolean ans = s.func(arr);
        assertFalse(ans);
    }

}