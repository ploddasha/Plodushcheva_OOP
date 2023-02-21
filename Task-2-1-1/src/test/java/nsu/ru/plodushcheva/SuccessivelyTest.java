package nsu.ru.plodushcheva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SuccessivelyTest {
    @Test
    public void exampleOneTest() {
        Successively s = new Successively();
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        boolean ans = s.func(arr);
        Assertions.assertTrue(ans);

    }

    @Test
    public void exampleTwoTest() {
        Successively s = new Successively();
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
        Assertions.assertFalse(ans);
    }

}