package nsu.ru.plodushcheva;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiThreadTest {

    @Test
    void exampleOneTest() throws Exception {
        List<Integer> arr = Arrays.asList(6, 8, 7, 13, 9, 4);
        MultiThread test = new MultiThread();
        boolean act = test.func(1, arr);
        assertTrue(act);
    }

    @Test
    void exampleTwoTest() throws Exception {
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
        MultiThread test = new MultiThread();
        boolean act = test.func(1, arr);
        assertFalse(act);
    }

}